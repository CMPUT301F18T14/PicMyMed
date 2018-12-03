/*
 * AddRecordActivity
 *
 * 1.2
 *
 * Copyright (C) 2018 CMPUT301F18T14. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.picmymedcode.View;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.picmymedphotohandler.PhotoIntentActivity;
import com.example.picmymedcode.BuildConfig;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.BodyLocation;
import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.Geolocation;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedmaphandler.View.DrawMapActivity;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * AddRecordActivity extends AppCompatActivity to create an activity for the user to
 * add a record to a problem.
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class AddRecordActivity extends AppCompatActivity{
    //RecordList recordList = new RecordList();
    public static final String TAG = "AddRecordActivity: ";
    public ArrayList<Problem> arrayListProblem;
    private static final String FILENAME = "file.sav";
    private static final int LAT_LNG_REQUEST_CODE = 786;
    private static final int CAMERA_REQUEST_CODE = 787;
    private static final int BODY_LOCATION_CODE = 788;
    private static final int REQUEST_PERMISSIONS_LOCATION_SETTINGS_REQUEST_CODE = 33;
    private static final int REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE = 35;
    private static long MIN_UPDATE_INTERVAL = 30 * 1000; // 1 minute is the minimum Android recommends, but we use 30 second
    private TextView locationNameTextView;
    private Button geoLocationButton;
    private Geolocation geolocation;
    private LocationRequest locationRequest;
    private Photo photo;
    private BodyLocation bodyLocation;
    int position;
    private ArrayList<Photo> placeHolderPhotoList;
    private FusedLocationProviderClient mFusedLocationClient;
    Location currentLocation;
    private Patient user;
    private int problemIndex;

    /**
     * Method initializes the add record activity
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {

        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        arrayListProblem = user.getProblemList();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord_activity);

        problemIndex = getIntent().getIntExtra("key", 0);
        placeHolderPhotoList = new ArrayList<Photo>();
        locationNameTextView = (TextView) findViewById(R.id.location_text);
        final EditText recordTitleEditText = findViewById(R.id.record_title_edit_text);
        final EditText recordDescriptionEditText = findViewById(R.id.record_description_edit_text);


        if (user.getBodyLocationPhotoList().size()==0) {

            //Uri imageUri = Uri.parse("android.resource://"+BuildConfig.APPLICATION_ID+"/drawable/default_bodyloc.png" );
            String imageUri =  "drawable://"  + R.drawable.default_bodyloc;

            Bitmap bitmap = decodeImageFromFiles(imageUri.toString(), 200, 200 );
            BodyLocationPhoto bodyLocationPhoto = new BodyLocationPhoto(imageUri.toString());
            bodyLocationPhoto.setLabel("Default Photo");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50 , byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
            bodyLocationPhoto.setBase64EncodedString(base64Image);
            //user.getBodyLocationPhotoList().add(bodyLocationPhoto);
            PicMyMedController.addBodyLocationPhoto(bodyLocationPhoto, AddRecordActivity.this);
        }

        geoLocationButton = (Button) findViewById(R.id.record_geo_button);
        geoLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(AddRecordActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    Log.d(TAG, "PermissionWasNotGiven.");
//                    ActivityCompat.requestPermissions(AddRecordActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
//                } else {
//                    Log.d(TAG, "PermissionWasThere.");
//                    Intent mapIntent = new Intent(AddRecordActivity.this, DrawMapActivity.class);
//                    mapIntent.putExtra("callingActivity", "AddRecordActivity");
//                    startActivityForResult(mapIntent, LAT_LNG_REQUEST_CODE);
//                }

                checkForLocationRequestSettings();
                if(currentLocation != null) {
                    sendingMapIntent();
                }
            }
        });


        Button cameraPhoto = (Button) findViewById(R.id.record_camera_button);
        cameraPhoto.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles if camera button is pressed
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                if (placeHolderPhotoList.size()<10) {
                    Intent photoIntent = new Intent(AddRecordActivity.this, PhotoIntentActivity.class);
                    if (photo != null) {
                        photoIntent.putExtra("problemIndex", problemIndex);
                        photoIntent.putExtra("base64ForConsistency", photo.getBase64EncodedString());
                    }
                    startActivityForResult(photoIntent, CAMERA_REQUEST_CODE);

                }
                else {
                    Toast.makeText(AddRecordActivity.this, "You cannot upload more than 10 photos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button bodyLocationButton = (Button) findViewById(R.id.bodyLocation);
        bodyLocationButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles if body location button is pressed
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent selectBodyLocationIntent = new Intent(AddRecordActivity.this, SelectBodyLocationActivity.class);
                startActivityForResult(selectBodyLocationIntent, BODY_LOCATION_CODE);
            }
        });

        Button recordSaveButton = findViewById(R.id.record_save_button);
        recordSaveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking the save record button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.getDefault());
                final String date = mSimpleDateFormat.format(new Date());
                Record record = new Record (recordTitleEditText.getText().toString(), date);
                //Date timeStamp = Calendar.getInstance().getTime();
                //record.setDate(timeStamp);
                record.setDescription(recordDescriptionEditText.getText().toString());
                if(geolocation!=null) {
                    record.setLocation(geolocation);
                }
//                if (photo != null){
//                    record.addToPhotoList(photo);
//                    System.out.println("I'm printing the phoro");
//                    System.out.println(photo.getBase64EncodedString().length());
//                }
                if (placeHolderPhotoList.size() != 0) {
                    record.setPhotoList(placeHolderPhotoList);
                }
                if (bodyLocation != null) {
                    record.setBodyLocation(bodyLocation);
                    Log.d("Saved", "saved bodylocation to record");
                }
                position = getIntent().getIntExtra("key",0);
                Problem problem = arrayListProblem.get(position);
                PicMyMedController.addRecord(problem, record, AddRecordActivity.this);
                //saveInFile();
                onBackPressed();//go back to previous activity
            }
        });

    }

    /**
     * Handles when activity is returned too
     */
    @Override
    public void onResume(){
        super.onResume();
        final TextView photoCounts = findViewById(R.id.photoCount);
        photoCounts.setText("You can add "+(10-placeHolderPhotoList.size())+ " more photos");
        locationRequest = null;
        geoLocationButton.setEnabled(true);
    }
    /**
     * Method starts add record activity
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        arrayListProblem = user.getProblemList();
        //loadFromFile();
    }

    /**
     * Method loads saved data from file
     * Used prior to implementation of elastic search
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListProblem = new TypeToken<ArrayList<Problem>>() {
            }.getType();
            arrayListProblem = gson.fromJson(reader, typeListProblem);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case LOCATION_PERMISSION_REQUEST_CODE: {
//                Log.d(TAG, "PermissionWasSuccessful.");
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.d(TAG, "PermisionWasInTheArray.");
//                    // permission was granted, yay! Do the
//                    // lat_lng related task you need to do.
//                    Intent mapIntent = new Intent(AddRecordActivity.this, DrawMapActivity.class);
//                    mapIntent.putExtra("callingActivity", "AddRecordActivity");
//                    startActivityForResult(mapIntent, LAT_LNG_REQUEST_CODE);
//                } else {
//                    toastMessage("Cannot get location if you don't give location permissions, you bum bum!");
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request.
//        }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == LAT_LNG_REQUEST_CODE && data != null) {
            double latitude = data.getDoubleExtra("latitude", 0);
            double longtitude = data.getDoubleExtra("longitude", 0);
            if (latitude != 0 && longtitude != 0) {
                geolocation = new Geolocation(latitude, longtitude);
                try {
                    geolocation.setLocationName(this.getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                locationNameTextView.setText(geolocation.getLocationName());
            }
        }

        if (requestCode == CAMERA_REQUEST_CODE) {
            try {
                photo = (Photo) data.getSerializableExtra("photoObject");
                if (photo != null) {
                    placeHolderPhotoList.add(photo);
                }
                Log.d(TAG, "successfully fetched photo");
            } catch (Exception e) {
                Log.d(TAG, "fetching photo failed!");
            }
        }

        if (requestCode == BODY_LOCATION_CODE) {
            try {
                int index = data.getIntExtra("bodyLocationPhotoIndex", 0);
                float xCoordinate = data.getFloatExtra("x", 0);
                float yCoordinate = data.getFloatExtra("y", 0);
                BodyLocationPhoto bodyLocationPhoto = user.getBodyLocationPhotoList().get(index);
                bodyLocation = new BodyLocation(bodyLocationPhoto.getPhotoID(), xCoordinate, yCoordinate);
                Log.d(TAG, "successfully created bodylocation");
            } catch (Exception e) {
                Log.d(TAG, "creating bodylocation failed");
            }
        }


        if (requestCode == REQUEST_PERMISSIONS_LOCATION_SETTINGS_REQUEST_CODE) {
            try {
                callCurrentLocation();
                if(currentLocation != null) {
                    sendingMapIntent();
                }
            } catch (Exception e) {
                Log.d(TAG, "fetching map failed!");
            }
        }

    }
    /**
     * Method creates toast message to display on device
     *
     * @param message   String
     */
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Check for location settings.
    public void checkForLocationRequestSettings() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(MIN_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        try {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.addLocationRequest(locationRequest);
            SettingsClient settingsClient = LocationServices.getSettingsClient(AddRecordActivity.this);

            settingsClient.checkLocationSettings(builder.build())
                    .addOnSuccessListener(AddRecordActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            //Setting is success...
                            callCurrentLocation();
                            Toast.makeText(AddRecordActivity.this, "Enabled the Location successfully. Now you can press the buttons..", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(AddRecordActivity.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                                    try {
                                        // Show the dialog by calling startResolutionForResult(), and check the
                                        // result in onActivityResult().
                                        ResolvableApiException rae = (ResolvableApiException) e;
                                        rae.startResolutionForResult(AddRecordActivity.this, REQUEST_PERMISSIONS_LOCATION_SETTINGS_REQUEST_CODE);
                                    } catch (IntentSender.SendIntentException sie) {
                                        sie.printStackTrace();
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    Toast.makeText(AddRecordActivity.this, "Setting change is not available.Try in another device.", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void callCurrentLocation() {
        Log.d(TAG, "callCurrentLocation: Begins!");
        FusedLocationProviderClient mFusedLocationClient = new FusedLocationProviderClient(this);

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                // ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                // int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.d(TAG, "callCurrentLocation: Permission was not granted.");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE);
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

//                    try {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    locationRequest = null;


                }
            }, Looper.myLooper());

        } catch (Exception ex) {
            Log.d(TAG, "callCurrentLocation: Exception caught.");
            ex.printStackTrace();
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCurrentLocation();
                if(currentLocation != null) {
                    sendingMapIntent();
                }
            }
        }
    }

    private void sendingMapIntent(){
        Intent mapIntent = new Intent(getApplicationContext(), DrawMapActivity.class);
        mapIntent.putExtra("callingActivity", "AddRecordActivity");
        mapIntent.putExtra("Latitude", currentLocation.getLatitude());
        mapIntent.putExtra("Longitude", currentLocation.getLongitude());
        startActivityForResult(mapIntent, LAT_LNG_REQUEST_CODE);
    }

    public Bitmap decodeImageFromFiles(String ImageFilePath, int imageViewWidth, int imageViewHeight){
        BitmapFactory.Options scalingOptions = new BitmapFactory.Options();

        // Do not load the Bitmap into memory
        scalingOptions.inJustDecodeBounds = true;

        // Passing the scalingOptions to decode the original file
        BitmapFactory.decodeFile(ImageFilePath, scalingOptions);

        // Determine how much to scale down the image
        int scaleFactor = 1;
        int originalImageWidth = scalingOptions.outWidth;
        int originalImageHeight = scalingOptions.outHeight;
        while (originalImageWidth / scaleFactor / 2 >= imageViewWidth
                && originalImageHeight / scaleFactor / 2 >= imageViewHeight) {

            scaleFactor *= 2;
        }

        // Decode with the scaling factor
        scalingOptions.inSampleSize = scaleFactor;

        // Loading onto memory in order to save the bitmap
        scalingOptions.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(ImageFilePath, scalingOptions);


        return bitmap;
    }

}
