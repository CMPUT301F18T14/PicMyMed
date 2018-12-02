/*
 * SignUpActivity
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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;

/**
 * SignUpActivity extends AppCompatActivity to create an activity for a new user to sign up
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class SignUpActivity extends AppCompatActivity {

    private static final Integer MIN_USER_ID_LENGTH = 8;
    private static final String patientType = "patient";
    private static final String careProviderType = "careprovider";

    TextView staticSignUpText;
    EditText enteredUsername;
    EditText enteredEmail;
    EditText enteredPhone;
    Button signUpBtn;
    String userType;


    /**
     * Method initializes SignUpActivity
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_actiivity);

        Intent previousSignUpIntent = getIntent();
        userType = previousSignUpIntent.getStringExtra("userType");   // get user type
        staticSignUpText = (TextView) findViewById(R.id.staticSignUpText);    // for testing
        enteredUsername = (EditText) findViewById(R.id.enteredUID);
        enteredEmail = (EditText) findViewById(R.id.enteredEmail);
        enteredPhone = (EditText) findViewById(R.id.enteredPhone);
        signUpBtn = (Button) findViewById(R.id.signUpButton);
        signUpBtn.setOnClickListener(signUpOnClickListener);

    }
    /**
     * OnClickListener for signUpButton separated
     */
    private DebouncedOnClickListener signUpOnClickListener = new DebouncedOnClickListener(2000) {
        /**
         * Method handles user clicking the sign up button
         *
         * @param v View
         */
        @Override
        public void onDebouncedClick(View v) {
            signUpLogic();
        }
    };

    /**
     * Handles the logic for creating a user
     * Verifies that fields are filled out and username does not exist
     * Before creating a new user
     */
    private void signUpLogic() {

            String username = enteredUsername.getText().toString().toLowerCase();
            String email = enteredEmail.getText().toString();
            String phoneNumber = enteredPhone.getText().toString();

            User user = null;

            if (username.length() < MIN_USER_ID_LENGTH) {
                toastMessage(String.format("Username should be at least %s characters!", String.valueOf(MIN_USER_ID_LENGTH)));
            } else if (username.contains(" ")) {
                toastMessage("Username cannot contain spaces!");

            } else if (email.length() == 0) {
               toastMessage("Email cannot be empty!");

            } else if (phoneNumber.length() == 0) {
                toastMessage("Phone number cannot be empty!");

            } else if (!phoneNumber.matches("^[+]?[0-9]{10,13}$")) {
                toastMessage("Phone number is invalid!");

            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                toastMessage("Invalid email address!");

            } else if (!userType.equals(patientType) & !userType.equals(careProviderType)) {
                toastMessage("Unable to determine user profile type!");

            } else {

                try {

                    if (userType.equals(patientType)) {
                        user = new Patient(username, email, phoneNumber);
                    } else if (userType.equals(careProviderType)) {
                        user = new CareProvider(username, email, phoneNumber);
                    }

                    if (PicMyMedController.checkValidUser(username) != 0 && user != null) {
                        toastMessage("Error: Username already exists, please try another one.");
                    } else {
                        PicMyMedController.createUser(user);
                        toastMessage("Account successfully created. Please login.");
                        Intent logInScreenIntent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(logInScreenIntent);
                        finish();
                    }

                } catch (Exception e) {
                    toastMessage(e.getMessage());
                }
            }
        }




    /**
     * Method shows toast message if user could create account or if user name exists
     *
     * @param message String
     */
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}