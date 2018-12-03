/*
 * CareProviderAddPatientActivity
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.QRCode.ScannerActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;

/**
 * CareProviderAddPatientActivity extends AppCompatActivity and implements SearchView to
 * add a patient
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class CareProviderAddPatientActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private final static int REQUEST_CODE = 100;
    private final static int CAMERA_PERMISSION_REQUEST = 200;
    private final static String barcodeID = "barcode";
    private ListView mListView;
    private PatientListViewAdapter mAdapter;
    private SearchView searchView;
    public static ArrayList<String> patientName;
    private User user;


    /**
     * Method sets the state
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientsearch_activity);

        user = PicMyMedApplication.getLoggedInUser();

        if (user == null ) {
            sessionExpired();
        }

        //Load all data to array
        patientName = PicMyMedController.getAllPatientUsernames();
        mListView = findViewById(R.id.patient_listview);

        //passing array into PatientListViewAdapter
        mAdapter = new PatientListViewAdapter(this);

        //binds the adapter to PatientListViewAdapter
        mListView.setAdapter(mAdapter);

        //Locate the edittext
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            /**
             * Method handles the user clicking on a patient in the list
             *
             * @param parent    AdapterView<?></?>
             * @param view      View
             * @param position  int
             * @param id        long
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (user != null) {
                    String name = user.getUsername();
                    addPatient(patientName.get(position));
                    onBackPressed();//go back to previous activity
                } else {
                    sessionExpired();
                }



            }

        });


        ImageView imageView = findViewById(R.id.careprovider_qr);
        imageView.setOnClickListener(new View.OnClickListener(){
            /**
             * Method handles the user clicking in the qr image
             *
             * @param v View
             */
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CareProviderAddPatientActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
                } else {
                    Intent scannerIntent = new Intent(CareProviderAddPatientActivity.this, ScannerActivity.class);
                    startActivityForResult(scannerIntent, REQUEST_CODE);
                }

            }
        });


    }

    /**
     * Method handles the user entering a text search query
     *
     * @param query String
     * @return      boolean
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Method handles the user changing their query
     *
     * @param newText   String
     * @return          boolean
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        mAdapter.filter(text);
        return false;
    }

    /**
     * Method handles the activity result
     *
     * @param requestCode   int
     * @param resultCode    int
     * @param data          Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data.getParcelableExtra(barcodeID) != null) {
            Barcode barcode = data.getParcelableExtra(barcodeID);
            String patientUserID = barcode.displayValue;
            if (user != null) {
                Log.d("DEBUG CPAPA", "The randomUserID is: " + patientUserID);
                String username = PicMyMedController.getUsernameByID(patientUserID);
                if (username != null & !username.isEmpty()) {
                    addPatient(username);
                } else {
                    toastMessage("Username was not found!");
                }
            } else {
                sessionExpired();
            }
        }
    }

    /**
     * Method checks the users persmissions
     *
     * @param requestCode   int
     * @param permissions   String
     * @param grantResults  int
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // camera related task you need to do.
                    Intent scannerIntent = new Intent(CareProviderAddPatientActivity.this, ScannerActivity.class);
                    startActivityForResult(scannerIntent, REQUEST_CODE);
                } else {
                    toastMessage("Cannot scan QR Code if you don't give camera permissions, you bum bum!");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    /**
     * Method adds patient to a careprovider
     *
     * @param patientUsername   String
     */
    public void addPatient(String patientUsername) {
        if (PicMyMedController.addPatientToCareProvider(patientUsername, CareProviderAddPatientActivity.this) != 1) {
            toastMessage(patientUsername + " has already been added!");
        } else {
            toastMessage(patientUsername + " has been added!");
        }
    }

    /**
     * Method checks if the session has expired
     */
    public void sessionExpired() {
        Intent mainActivityIntent = new Intent(CareProviderAddPatientActivity.this, MainActivity.class);
        toastMessage("User session expire. Please login again.");
        startActivity(mainActivityIntent);
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
