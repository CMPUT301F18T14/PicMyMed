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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.picmymedphotohandler.PhotoIntentActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Geolocation;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.R;
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
import java.util.ArrayList;

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
    public ArrayList<Problem> arrayListProblem;
    private static final String FILENAME = "file.sav";
    private static final int LAT_LNG_REQUEST_CODE = 786;
    private TextView locationNameTextView;
    private Geolocation geolocation;
    int position;

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
        locationNameTextView = (TextView) findViewById(R.id.location_text);
        final EditText recordTitleEditText = findViewById(R.id.record_title_edit_text);
        final EditText recordDescriptionEditText = findViewById(R.id.record_description_edit_text);

        Button geoLocationButton = (Button) findViewById(R.id.record_geo_button);
        geoLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(AddRecordActivity.this,DrawMapActivity.class);
                startActivityForResult(mapIntent, LAT_LNG_REQUEST_CODE);
            }
        });

        Button cameraPhoto = (Button) findViewById(R.id.record_camera_button);
        cameraPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(AddRecordActivity.this,PhotoIntentActivity.class);
                startActivity(photoIntent);
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
                Record record = new Record (recordTitleEditText.getText().toString());
                record.setDescription(recordDescriptionEditText.getText().toString());
                if(geolocation!=null) {
                    record.setLocation(geolocation);
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
    }
}
