/*
 * AddRecordActivity
 *
 * 1.1
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
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.picmymedphotohandler.PhotoIntentActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Geolocation;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedmaphandler.View.DrawMapActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * AddRecordActivity extends AppCompatActivity to create an activity for the user to
 * add a record to a problem
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class AddRecordActivity extends AppCompatActivity{
    //RecordList recordList = new RecordList();
    public static final String TAG = "AddRecordActivity: ";
    public ArrayList<Problem> arrayListProblem;
    private static final String FILENAME = "file.sav";
    private static final int LAT_LNG_REQUEST_CODE = 786;
    private static final int CAMERA_REQUEST_CODE = 787;
    private TextView locationNameTextView;
    private Geolocation geolocation;
    private Photo photo;
    int position;
    private ArrayList<Photo> placeHolderPhotoList;

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
        placeHolderPhotoList = new ArrayList<Photo>();
        locationNameTextView = (TextView) findViewById(R.id.location_text);
        final EditText recordTitleEditText = findViewById(R.id.record_title_edit_text);
        final EditText recordDescriptionEditText = findViewById(R.id.record_description_edit_text);

        Button geoLocationButton = (Button) findViewById(R.id.record_geo_button);
        geoLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddRecordActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LAT_LNG_REQUEST_CODE);
                } else {
                    Intent mapIntent = new Intent(AddRecordActivity.this, DrawMapActivity.class);
                    mapIntent.putExtra("callingActivity", "AddRecordActivity");
                    startActivityForResult(mapIntent, LAT_LNG_REQUEST_CODE);
                }
            }
        });

        Button cameraPhoto = (Button) findViewById(R.id.record_camera_button);
        cameraPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(AddRecordActivity.this,PhotoIntentActivity.class);
                startActivityForResult(photoIntent, CAMERA_REQUEST_CODE);
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
                Record record = new Record (recordTitleEditText.getText().toString(), Calendar.getInstance().getTime());
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
                position = getIntent().getIntExtra("key",0);
                Problem problem = arrayListProblem.get(position);
                PicMyMedController.addRecord(problem, record);
                //saveInFile();
                onBackPressed();//go back to previous activity
            }
        });

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

    /**
     * Method saves data to file
     * Used prior to implementation of elastic search
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(arrayListProblem,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LAT_LNG_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // lat_lng related task you need to do.
                    Intent mapIntent = new Intent(AddRecordActivity.this, DrawMapActivity.class);
                    mapIntent.putExtra("callingActivity", "AddRecordActivity");
                    startActivityForResult(mapIntent, LAT_LNG_REQUEST_CODE);
                } else {
                    toastMessage("Cannot get location if you don't give location permissions, you bum bum!");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == LAT_LNG_REQUEST_CODE) {
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
                Log.d(TAG, "seccessfuly fetched photo");
            } catch (Exception e) {
                Log.d(TAG, "fetching photo failed!");
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
}
