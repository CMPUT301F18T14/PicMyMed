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

import com.example.QRCode.ScannerActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.R;
import com.example.QRCode.GeneratorActivity;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * MainActivity extends AppCompatActivity to handle the main activity of the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private final static int REQUEST_CODE = 100;
    private final static int PERMISSION_REQUEST = 200;
    private Barcode barcode;

    /**
     * Method initializes the main activity
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*// Creating a SharedPreferences database to store logged status
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);


        // Checking if the logged in status is true
        if(sharedPreferences.getBoolean("login", false)) {

            // Checking if the username can be used to create login profiles after checking elasticSearch database
            if (PicMyMedController.checkLogin(sharedPreferences.getString("userID", "Not Found")) == 1) {
                // If the user is a patient or a careprovider to run respective activity
                if(PicMyMedApplication.getLoggedInUser().isPatient()){
                    Intent problemIntentDirect = new Intent(MainActivity.this, ProblemActivity.class);
                    startActivity(problemIntentDirect);
                }
                else {
                    Intent patientIntent = new Intent(MainActivity.this, CareProviderActivity.class);
                    startActivity(patientIntent);
                }

            } else {
                Toast.makeText(MainActivity.this, "Invalid username",
                        Toast.LENGTH_LONG).show();
            }

        }
       */

        Button loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking the login button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                EditText enteredUsername = (EditText) findViewById(R.id.enteredUID);
                String username = enteredUsername.getText().toString();
/*
                // Upon 1st time logged in the sharedPreferences stores the logged in status
                sharedPreferences.edit().putBoolean("login", true).apply();
                // Upon 1st time logged in the sharedPreferences stores the username
                sharedPreferences.edit().putString("userID", username).apply();
*/

                if (PicMyMedController.checkLogin(username) == 1) {
                    if (PicMyMedController.checkAuthorizedDevice() != 1) {
                        authorizeDeviceDialog();
                    } else {
                        login();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        Button QRBtn = (Button) findViewById(R.id.qrButton);
        QRBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles the user clicking the sign up button
             *
             * @param v View
             */
            public void onClick(View v) {
                //signupPopUpWindow();
                Intent qrIntent = new Intent(MainActivity.this, GeneratorActivity.class);
                startActivity(qrIntent);
                //finish();
            }
        });


        Button signupBtn = (Button) findViewById(R.id.signUpButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles the user clicking the sign up button
             *
             * @param v View
             */
            public void onClick(View v) {

                Intent problemIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(problemIntent);
                //finish();
            }
        });
    }
    public void login() {
        if (PicMyMedApplication.getLoggedInUser().isPatient()) {
            Intent problemIntent = new Intent(MainActivity.this, ProblemActivity.class);
            startActivity(problemIntent);
        } else {
            //toastMessage("Careprovider activity to be implemented.");
            Intent patientIntent = new Intent(MainActivity.this, CareProviderActivity.class);
            startActivity(patientIntent);
        }
    }
    public void authorizeDeviceDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Device authorization required!")
                .setCancelable(false)
                .setMessage("You are accessing your profile from a new device. Would you like to authorize this device?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, PERMISSION_REQUEST);
                        }
                        Intent scannerIntent = new Intent(MainActivity.this, ScannerActivity.class);
                        startActivityForResult(scannerIntent, REQUEST_CODE);
                        Toast.makeText(MainActivity.this, "Attempting to authorize ...",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Device authorization unsuccessful",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                barcode = data.getParcelableExtra("barcode");
                if (barcode != null) {
                    Log.d("DEBUG", barcode.displayValue);
                    Log.d("DEBUG",PicMyMedApplication.getLoggedInUser().getRandomUserID());
                    if (PicMyMedApplication.getLoggedInUser().getRandomUserID().equals(barcode.displayValue)) {
                        Toast.makeText(getApplicationContext(), "Authorization successful", Toast.LENGTH_LONG).show();
                        PicMyMedController.addAuthorizedDevice(Boolean.TRUE);
                        login();
                    } else {
                        Toast.makeText(getApplicationContext(), "Authorization unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }

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
