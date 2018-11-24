/*
 * newUsernameActivity
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
package com.example.cmput301f18t14.picmymed.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmput301f18t14.picmymed.Controller.PicMyMedController;
import com.example.cmput301f18t14.picmymed.Model.User;
import com.example.cmput301f18t14.picmymed.R;
import com.example.cmput301f18t14.picmymed.Model.CareProvider;
import com.example.cmput301f18t14.picmymed.Model.Patient;

/**
 * newUsernameActivity extends AppCompatActivity to create an activity for a new user to sign up
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class newUsernameActivity extends AppCompatActivity {
    /**
     * Method initializes newUsernameActivity
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newusername_activity);

        Intent newuserIntent = getIntent();
        final String userType = newuserIntent.getStringExtra("userType");   // get user type

        setContentView(R.layout.newusername_activity);
        if (userType.equals("patient")) {

            EditText email = findViewById(R.id.enteredEmail);
            EditText phone = findViewById(R.id.enteredPhone);

            email.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);

        }
        TextView textView = (TextView) findViewById(R.id.textView2);    // for testing
        textView.setText(userType);

        Button signUpBtn = (Button) findViewById(R.id.signUpButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking the sign up button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                EditText enteredUsername = (EditText) findViewById(R.id.enteredUID);
                String username = enteredUsername.getText().toString();


                // This was previously done to pass user to problem activity, but we decided to redirect to login page
                /*Intent problemIntent = new Intent(newUsernameActivity.this, ProblemActivity.class);
                startActivity(problemIntent);*/
                Intent logInScreenIntent = new Intent(newUsernameActivity.this, MainActivity.class);
                User user = null;
                try {
                    if (userType.equals("patient")) {
                        EditText enteredEmail = (EditText) findViewById(R.id.enteredEmail);
                        EditText enteredPhone = (EditText) findViewById(R.id.enteredPhone);

                        user = new Patient(username, enteredEmail.getText().toString(), enteredPhone.getText().toString());
                    } else if (userType.equals("careprovider")) {
                        user = new CareProvider(username);
                    }
                } catch (Exception e) {
                    toastMessage(e.getMessage());
                }
                if (user != null && PicMyMedController.createUser(user) != 1) {
                    toastMessage("Error: Username already exists, please try another one.");
                } else {
                    toastMessage("Account successfully created. Please login.");
                    startActivity(logInScreenIntent);
                    finish();
                }

            }
        });

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