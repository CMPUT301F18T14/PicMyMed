/*
 * UserProfileTypeActivity
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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.picmymedcode.R;

/**
 * UserProfileTypeActivity extends AppCompatActivity to create an activity for the user to
 * sign up
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class UserProfileTypeActivity extends AppCompatActivity {

    private final String patientType = "patient";
    private final String careProviderType = "careprovider";
    Intent signUpActivity;
    /**
     * Method initializes UserProfileTypeActivity
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofiletype_activity);

        signUpActivity = new Intent(UserProfileTypeActivity.this, SignUpActivity.class);
        Button patientBtn = (Button) findViewById(R.id.patientButton);
        patientBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on patient button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                signUpActivity.putExtra("userType", patientType);
                startActivity(signUpActivity);
                finish();

            }
        });

        Button careProviderBtn = (Button) findViewById(R.id.careProviderButton);
        careProviderBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on careProvider button
             *
             * @param v View
             */
            public void onClick(View v) {
                signUpActivity.putExtra("userType", careProviderType);
                startActivity(signUpActivity);
                finish();
            }
        });
    }
}