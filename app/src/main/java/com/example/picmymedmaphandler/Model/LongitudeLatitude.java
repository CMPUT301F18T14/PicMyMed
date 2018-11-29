package com.example.picmymedmaphandler.Model;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LongitudeLatitude {

    private static final String TAG = "LongitudeLatitude: ";

    private static final int REQUEST_PERMISSIONS_LOCATION_SETTINGS_REQUEST_CODE = 33;
    private static final int REQUEST_PERMISSIONS_LAST_LOCATION_REQUEST_CODE = 34;
    private static final int REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE = 35;

    private FusedLocationProviderClient mFusedLocationClient;

    protected static long MIN_UPDATE_INTERVAL = 30 * 1000; // 1 minute is the minimum Android recommends, but we use 30 seconds

    protected Location mLastLocation;

    LocationRequest locationRequest;
    Location lastLocation = null;
    Location currentLocation = null;

    private LatLng latLng = null;

    Activity activity;

    public LongitudeLatitude(Activity activity) {
        this.activity = activity;
        checkForLocationRequest();
        checkForLocationSettings();
        callCurrentLocation();
    }

    private void checkForLocationRequest(){
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(MIN_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    //Check for location settings.
    private void checkForLocationSettings() {

        try {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.addLocationRequest(locationRequest);
            SettingsClient settingsClient = LocationServices.getSettingsClient(activity);

            settingsClient.checkLocationSettings(builder.build())
                    .addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            //Setting is success...
                            Toast.makeText(activity,
                                    "Enabled the Location successfully. Now you can press the buttons..",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(activity, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                                    try {
                                        // Show the dialog by calling startResolutionForResult(), and check the
                                        // result in onActivityResult().
                                        ResolvableApiException rae = (ResolvableApiException) e;
                                        rae.startResolutionForResult(activity, REQUEST_PERMISSIONS_LOCATION_SETTINGS_REQUEST_CODE);
                                    } catch (IntentSender.SendIntentException sie) {
                                        sie.printStackTrace();
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    Toast.makeText(activity, "Setting change is not available.Try in another device.", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void startLocationPermissionRequest(int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, requestCode);
    }

    private void requestPermissions(final int requestCode) {
        boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            //showSnackbar("Permission is must to find the location", "Ok",
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request permission
                    startLocationPermissionRequest(requestCode);
                }
            };

        } else {
            startLocationPermissionRequest(requestCode);
        }
    }

//    @Override
//    public void onRequestPermissionsResul(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//
//        if (requestCode == REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                callCurrentLocation(null);
//            }
//        }
//    }

    public void callCurrentLocation() {
        Log.d(TAG, "callCurrentLocation: Begins!");
        mFusedLocationClient = new FusedLocationProviderClient(activity);

        try {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                // ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                // int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.d(TAG, "callCurrentLocation: Permission was not granted.");
                requestPermissions(REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE);
                return;
            }

            Log.d(TAG, "callCurrentLocation: Permission is granted!");
            // Updating location
            mFusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    Log.d(TAG, "callCurrentLocation: Inside on location result!");

                    currentLocation = (Location) locationResult.getLastLocation();

                    Log.d(TAG, "callCurrentLocation: Current location: Latitude = " + currentLocation.getLatitude()
                            + ", Longitude = " + currentLocation.getLongitude());

                    mFusedLocationClient = null;

                    latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                }
            }, Looper.myLooper());

        } catch (Exception ex) {
            Log.d(TAG, "callCurrentLocation: Exception caught.");
            ex.printStackTrace();
        }
    }

    public LatLng getLatLon(){
        Log.d(TAG, "getLatLon(): Current location: Latitude = " + latLng.latitude
                + ", Longitude = " + latLng.longitude);
        return latLng;
    }

}
