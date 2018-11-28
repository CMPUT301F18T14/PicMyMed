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

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.R;
import com.example.QRCode.GeneratorActivity;

/**
 * MainActivity extends AppCompatActivity to handle the main activity of the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    /**
     * Method initializes the main activity
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating a SharedPreferences database to store logged status
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

                // Upon 1st time logged in the sharedPreferences stores the logged in status
                sharedPreferences.edit().putBoolean("login", true).apply();
                // Upon 1st time logged in the sharedPreferences stores the username
                sharedPreferences.edit().putString("userID", username).apply();

                if (PicMyMedController.checkLogin(username) == 1) {
                    if(PicMyMedApplication.getLoggedInUser().isPatient()){
                        Intent problemIntent = new Intent(MainActivity.this, ProblemActivity.class);
                        startActivity(problemIntent);
                    }
                    else {
                        //toastMessage("Careprovider activity to be implemented.");
                        Intent patientIntent = new Intent(MainActivity.this, CareProviderActivity.class);
                        startActivity(patientIntent);
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
                //signupPopUpWindow();

                Intent problemIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(problemIntent);
                //finish();
            }
        });
    }

    /**
     * Method starts pop up window to allow user to sign up as patient or caregiver.
     * Popup window has been disabled for better functionality of user signing up
     */
    public void signupPopUpWindow() {
        final Dialog signupPopUp = new Dialog(MainActivity.this);
        signupPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        signupPopUp.setContentView(R.layout.signup_activity);
        signupPopUp.setTitle("Sign Up");

        Button patient = (Button) signupPopUp.findViewById(R.id.patientButton);
        Button careProvider = (Button) signupPopUp.findViewById(R.id.careProviderButton);

        patient.setEnabled(true);
        careProvider.setEnabled(true);

        patient.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles users selecting they are a patient
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                signupPopUp.cancel();
                Toast.makeText(MainActivity.this, "User is a patient", Toast.LENGTH_LONG).show();
                usernamePopUpWindow();

                //setContentView(R.layout.addproblem_activity);
            }
        });

        careProvider.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user selecting they are a care provider
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                signupPopUp.cancel();
                Toast.makeText(getApplicationContext(), "User is a care provider", Toast.LENGTH_LONG).show();
                String username = usernamePopUpWindow();
                Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();
                setContentView(R.layout.newcareprovider_activity);
                TextView setUserName = (TextView)findViewById(R.id.careProviderName);
                String welcome = "Welcome " + username ;
                setUserName.setText(welcome);
            }
        });

        signupPopUp.show();

    }

    /**
     * Method creates popup window for the user to select a username
     *
     * @return String username
     */
    public String usernamePopUpWindow() {
        final Dialog usernamePopUp = new Dialog(MainActivity.this);
        usernamePopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        usernamePopUp.setContentView(R.layout.newusername_activity);
        usernamePopUp.setTitle("Pick a Username");

        Button submit = (Button) usernamePopUp.findViewById(R.id.signUpButton);

        submit.setEnabled(true);
        EditText enteredUsername = (EditText) usernamePopUp.findViewById(R.id.enteredUID);
        String username = enteredUsername.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking submit button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                usernamePopUp.cancel();
            }
        });
        usernamePopUp.show();
        return username;

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
