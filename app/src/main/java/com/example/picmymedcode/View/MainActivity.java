/*
 * MainActivity
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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.preference.PreferenceManager;

import com.example.QRCode.ScannerActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * MainActivity extends AppCompatActivity to handle the main activity of the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;
    private final static int CAMERA_PERMISSION_REQUEST = 200;
    private final static String barcodeID = "barcode";
    private final Handler handler = new Handler();
    private final int delay = 5000; //milliseconds

    private Button loginBtn;
    private EditText enteredUsername;
    private Button qrBtn;
    private Button signUpBtn;
    private Barcode barcode;
    private String randomUserID;
    private User user;
    private SharedPreferences settings;


    /**
     * Method initializes the main activity
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(loginHandlerClickListener);

        qrBtn = (Button) findViewById(R.id.qrButton);
        qrBtn.setOnClickListener(scanQRCodeOnClickListener);

        signUpBtn = (Button) findViewById(R.id.signUpButton);
        signUpBtn.setOnClickListener(signUpActivityOnClickListener);

        enteredUsername = (EditText) findViewById(R.id.enteredUID);

       /* SharedPreferences prefs = getSharedPreferences("username",
                MODE_PRIVATE);
        String username = prefs.getString("username", null);
        if (username != null && !username.isEmpty() && PicMyMedApplication.isNetworkAvailable(MainActivity.this)) {
            Log.i("DEBUG SHARED", "Trying t use hsared preferences");
            loginHandler(username);
        }
*/

    }

   /* protected void onStart(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("username",
                MODE_PRIVATE);
        String username = prefs.getString("username", null);
        loginHandler(username);

    }*/

    private DebouncedOnClickListener loginHandlerClickListener = new DebouncedOnClickListener(2000) {
        /**
         * Method handles user clicking the sign up button
         *
         * @param v View
         */
        @Override
        public void onDebouncedClick(View v) {
            loginHandler(enteredUsername.getText().toString());
        }

    };
    private void loginHandler(String username) {

        if (PicMyMedApplication.isNetworkAvailable(MainActivity.this)) {
            if (updateLocal() == 1) { toastMessage("Synchornized old data");}
            if (PicMyMedController.checkValidUser(username) == 1) {
                user = PicMyMedController.getUser(username);
                if (user != null) {
                    if (PicMyMedController.checkAuthorizedDevice(user) != 1) {
                        authorizeDeviceDialog();
                    } else {
                        initializeApp(Boolean.FALSE);
                    }
                } else {
                    toastMessage("Error retrieving profile.");
                }
            } else {
                toastMessage("The username does not exist!");
            }
        } else {
            toastMessage("You must be connected to the internet to login.");
        }
    }

    public void authorizeDeviceDialog() {
        AlertDialog.Builder authorizationDialog = new AlertDialog.Builder(this);
        authorizationDialog.setTitle("Device authorization required!")
                .setCancelable(false)
                .setMessage("You are accessing your profile from a new device. Would you like to authorize this device?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanQRCode();
                        toastMessage("Attempting to authorize ...");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toastMessage("Device authorization unsuccessful");
                    }
                });
        authorizationDialog.show();
    }

    private DebouncedOnClickListener signUpActivityOnClickListener = new DebouncedOnClickListener(2000) {
        /**
         * Method handles user clicking the sign up button
         *
         * @param v View
         */
        @Override
        public void onDebouncedClick(View v) {
            signUpActivity();
        }

    };
    private void signUpActivity() {
        Intent problemIntent = new Intent(MainActivity.this, UserProfileTypeActivity.class);
        startActivity(problemIntent);
    }

    private DebouncedOnClickListener scanQRCodeOnClickListener = new DebouncedOnClickListener(2000) {
        /**
         * Method handles user logging in via code
         *
         * @param v View
         */
        @Override
        public void onDebouncedClick(View v) {
            scanQRCode();
        }

    };
    private void scanQRCode() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
        } else {
            Intent scannerIntent = new Intent(MainActivity.this, ScannerActivity.class);
            startActivityForResult(scannerIntent, REQUEST_CODE);
        }
    }

    private int updateLocal() {
        if (PicMyMedApplication.loadUserData(MainActivity.this)) {
            User localUser = PicMyMedApplication.getLocalUser();
            if (localUser.getRequiresSync() == Boolean.TRUE) {
                PicMyMedController.updateUser(localUser, MainActivity.this);
                Log.i("DEBUG", "saving old data");

                return 1;
            }
        } else {
            Log.i("DEBUG","COULD NOT UPDATE");
        }
        return 0;
    }

    private int initializeApp(Boolean authorizeUser) {
        try {
            if (user != null) {
                //SharedPreferences.Editor editor = getSharedPreferences("username",MODE_PRIVATE).edit();
               // editor.putString("username", user.getUsername());
                //editor.apply();
                if (authorizeUser) {
                    PicMyMedController.addAuthorizedDevice(user, MainActivity.this);
                }
                PicMyMedApplication.setLoggedInUser(user);
                PicMyMedController.updateLastDeviceUsed(user, MainActivity.this);
                handler.postDelayed(new Runnable(){
                    public void run(){
                        Log.i("handler Debug", "run()");
                        if(PicMyMedApplication.getLoggedInUser() != null) {
                            PicMyMedController.updateUser(PicMyMedApplication.getLoggedInUser(), MainActivity.this);
                            Toast.makeText(MainActivity.this, "trying to update", Toast.LENGTH_SHORT).show();
                        }
                        handler.postDelayed(this, delay);
                    }
                }, delay);
                if (user.isPatient()) {
                    Intent problemIntent = new Intent(MainActivity.this, PatientActivity.class);
                    startActivity(problemIntent);
                    finish();
                } else {
                    Intent patientIntent = new Intent(MainActivity.this, CareProviderActivity.class);
                    startActivity(patientIntent);
                    finish();
                }
            }
        } catch(Exception e) {
            toastMessage("Error logging in. Please try again.");
        }
        return 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getParcelableExtra(barcodeID) != null) {

            barcode = data.getParcelableExtra(barcodeID);
            randomUserID = barcode.displayValue;
            if (PicMyMedApplication.isNetworkAvailable(MainActivity.this)) {
                if (updateLocal() == 1) {
                    toastMessage("Synchronizing previous data ...");
                }

                if (user == null) {
                    String username = PicMyMedController.getUsernameByID(randomUserID);
                    user = PicMyMedController.getUser(username);
                }
                if (user.getRandomUserID().equals(randomUserID)) {
                    toastMessage("Logged in successfully!");
                    initializeApp(Boolean.TRUE);
                } else {
                    toastMessage("Login was unsuccessful!");
                }
            } else {
                toastMessage("You must be connected to the internet to login.");
            }
        }
    }
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
                    Intent scannerIntent = new Intent(MainActivity.this, ScannerActivity.class);
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
     * Method creates toast message to display on device
     *
     * @param message   String
     */
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
