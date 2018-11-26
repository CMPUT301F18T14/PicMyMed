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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Controller.Utility;
import com.example.picmymedcode.R;


/**
 * MainActivity extends AppCompatActivity to handle the main activity of the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class MainActivity extends AppCompatActivity {

    Button loginBtn;

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
                    Utility.toastMessage(getApplicationContext(), "Invalid username");
                }

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

            }
        });
    }

}
