/*
 * ProfileActivity
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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;

/**
 * ProfileActivity extends AppCompatActivity to show a users phone and email and edit it
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class ProfileActivity extends AppCompatActivity {
    Patient user = (Patient)PicMyMedApplication.getLoggedInUser();

    /**
     * Method initializes the activity
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);


        TextView showUsername = (TextView)findViewById(R.id.username);
        showUsername.setText(user.getUsername());

        TextView showPhoneNumber = (TextView)findViewById(R.id.phoneNumber);
        showPhoneNumber.setText(user.getPhoneNumber());

        TextView showEmail = (TextView)findViewById(R.id.email);
        showEmail.setText(user.getEmail());

        Button editProfileButton = findViewById(R.id.editProfile_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent problemIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(problemIntent);
            }
        });
    }

    /**
     * Method starts the activity by getting the user's username, phone number and email
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        TextView showUsername = (TextView)findViewById(R.id.username);
        showUsername.setText(user.getUsername());

        TextView showPhoneNumber = (TextView)findViewById(R.id.phoneNumber);
        showPhoneNumber.setText(user.getPhoneNumber());

        TextView showEmail = (TextView)findViewById(R.id.email);
        showEmail.setText(user.getEmail());


    }
}
