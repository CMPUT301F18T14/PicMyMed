package com.example.picmymedmaphandler.View;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmput301f18t14.picmymed.R;
import com.example.picmymedmaphandler.Model.LongitudeLatitude;
import com.example.picmymedmaphandler.Model.PlaceInformation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DrawMapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private final static String TAG = "DrawMapActivity: ";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private final static String DEVICE_LOCATION_TITLE = "Current Location";
    private final static float MAP_ZOOM_LEVEL = 10f; // 15: Able to View Streets
    private final static LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(0, 0), new LatLng(0, 0));//new LatLngBounds(new LatLng(85, -180), new LatLng(-85, 180)); // Maximum bound for google Map
    private LongitudeLatitude longitudeLatitude = null;
    private LatLng mLatLng = null;
    private GoogleMap mGoogleMap;
    private AutoCompleteTextView searchText;
    private ImageView mGps;
    private ImageView mAdd;
    private PlaceAutocompleteAdapter placeAutocompleteAdapter;
    private GoogleApiClient googleApiClient;
    private PlaceInformation mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_map);

        if (isServicesOK()) {
            initialTaskInActivity();
        }

    }

    private void initialTaskInActivity() {
        searchText = (AutoCompleteTextView) findViewById(R.id.input_search);

        mGps = (ImageView) findViewById(R.id.icon_gps);

        mAdd = (ImageView) findViewById(R.id.icon_add);

        longitudeLatitude = new LongitudeLatitude(DrawMapActivity.this);

        // Delays the drawing of Map
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Handles null pointer exception when LongitudeLatitude returns null LatLng
                try {
                    // Getting the current device location
                    gettingCurrentLatLon();
                    // Drawing the map
                    initMap();
                    // Searching Location
                    initSearch();
                } catch (NullPointerException e) {
                    //onStart();
                    Toast.makeText(DrawMapActivity.this,
                            "Location is not synced. Turn on the GPS, and try again.",
                            Toast.LENGTH_SHORT).show();
                    // Creating intent, and calling the activity again
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        }, 5000 /* 5 sec */ );
    }

    private void initSearch(){
        Log.d(TAG, "init: initializing");

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        // Checking which item was selected from the list
        searchText.setOnItemClickListener(autocompleteClickListener);

        // Showing the suggested location in relative bound of the geolocation
        placeAutocompleteAdapter =  new PlaceAutocompleteAdapter(this,Places.getGeoDataClient(this),LAT_LNG_BOUNDS,null);

        // Setting the adapter on the search field
        searchText.setAdapter(placeAutocompleteAdapter);

        // Action Listener for the search location field
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER){

                    // Start method for searching
                    geoLocate();

                }

                return false;
            }
        });

        // Hiding KeyBoard upon selection
        hideSoftKeyBoard();

        // Action Listener for GPS button
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked GPS icon");
                gettingCurrentLatLon();
                movingMapCamera(mLatLng, MAP_ZOOM_LEVEL);
                drawMarker(mLatLng, DEVICE_LOCATION_TITLE);
            }
        });

        // Action Listener for GPS button
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked Add icon");
                // There should be more code
                Intent backToAddRecordActivity = new Intent();
                backToAddRecordActivity.putExtra("latitude", mLatLng.latitude);
                backToAddRecordActivity.putExtra("longitude", mLatLng.longitude);
                setResult(RESULT_OK, backToAddRecordActivity);
                finish();

            }
        });
    }

    /**
     * This method Locate the field camera at the exact selected place
     */
    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = searchText.getText().toString();

        Geocoder geocoder = new Geocoder(DrawMapActivity.this);
        List<Address> list = new ArrayList<>();

        try {
            list = geocoder.getFromLocationName(searchString, 1);

        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0){
            Address address = list.get(0);      // Only one address in the list

            Log.d(TAG, "geoLocate: found a location: " + address.toString());

            Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            mLatLng = new LatLng(address.getLatitude(), address.getLongitude() );

            movingMapCamera(mLatLng, MAP_ZOOM_LEVEL);

            drawMarker(mLatLng, address.getAddressLine(0));
        }
    }

    // Initializes the GoogleMap Object and draws in on top of map fragment
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Toast.makeText(DrawMapActivity.this, "Map is ready.", Toast.LENGTH_SHORT).show();
                // Initializing google map
                mGoogleMap = googleMap;

                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);

                // Moving camera to the specific location
                movingMapCamera(mLatLng, MAP_ZOOM_LEVEL);

                drawMarker(mLatLng, DEVICE_LOCATION_TITLE);

            }
        });
    }

    /**
     * This function directs the Map Camera and focuses it to the specific location
     *
     * @param latLng        The latitude longitude object
     * @param zoom          The level of zooming
     */
    private void movingMapCamera(LatLng latLng, float zoom) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    /**
     * This function draws a marker on the given location
     *
     * @param latLng        The latitude longitude object
     */
    private void drawMarker(LatLng latLng, String title) {
//        MarkerOptions options = new MarkerOptions();
//        options.position(latLng);
//        mGoogleMap.addMarker(options);
        mGoogleMap.clear();

        Marker markerName = mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title));
    }

    /**
     * This function draws multiple markers for a given set of locations.
     *
     * @param latLngs       An array list of LatLng objects
     */
    private void drawMultipleMarker(ArrayList<LatLng> latLngs) {
        MarkerOptions options = new MarkerOptions();
        for (LatLng latlng : latLngs) {
            options.position(latlng);
            mGoogleMap.addMarker(options);
        }
    }

    /**
     * This function retrieves the latitude and longitude from LatitudeLongitude objects
     */
    private void gettingCurrentLatLon() {
        longitudeLatitude.callCurrentLocation();
        mLatLng = longitudeLatitude.getLatLon();
        System.out.println("The Location is at " + mLatLng.latitude +" and " + mLatLng.longitude);

    }

    /**
     * This method hides the keyboard
     */
    private void hideSoftKeyBoard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    // Checking the version of google play services
    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DrawMapActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            // Everything is fine; User can make map request
            Log.d(TAG, "isServicesOK: GooglePlayServices is working.");
            return true;
        }

        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // An error occurred but can be resolved
            Log.d(TAG, "isServicesOK: an error occurred but can be fixed.");

            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(DrawMapActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }

        else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

//    private String getPlaceName(LatLng latLng) throws IOException {
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//        String city = addresses.get(0).getLocality();
//        String state = addresses.get(0).getAdminArea();
//        String country = addresses.get(0).getCountryName();
//        String postalCode = addresses.get(0).getPostalCode();
//        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
//        return address;
//    }

    /*
        --------------------------- google places API autocomplete suggestions handlers -----------------
    */

    // Getting the information of the selected places
    private AdapterView.OnItemClickListener autocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            hideSoftKeyBoard();

            // Get the item
            final AutocompletePrediction item = placeAutocompleteAdapter.getItem(i);

            // Extract the placeId from the item
            final String placeId = item.getPlaceId();

            // Converting GoogleApiClient to GeoDataApi
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(googleApiClient, placeId);

            // Sending the callback
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    // Displays the result of the place object
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            /* Checking if the places were successfully retrieved */
            if(!places.getStatus().isSuccess()){
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                // If not, free up PlaceBuffer to save memory
                places.release();
                return;
            }

            // Only one place is choosen from the list, so index is 0
            final Place place = places.get(0);

            // Trying to extract place information
            try {
                mPlace = new PlaceInformation(place.getName(), place.getAddress(), place.getId(), place.getLatLng());
                Log.d(TAG, "onResult: place: " + mPlace.toString());
            } catch (NullPointerException e) {
                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage() );
            }

            // Retrieving the latitude and longitude
            //mLatLng = new LatLng(place.getViewport().getCenter().latitude, place.getViewport().getCenter().longitude);
            mLatLng = place.getLatLng();

            // Moving the camera to the specific LatLng
            movingMapCamera(mLatLng, MAP_ZOOM_LEVEL);

            // Drawing marker to the specific LatLng
            drawMarker(mLatLng, mPlace.getName().toString());

            // Free up PlaceBuffer to save memory
            places.release();
        }
    };

}
