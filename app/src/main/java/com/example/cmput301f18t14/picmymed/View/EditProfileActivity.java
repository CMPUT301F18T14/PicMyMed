/*
 * EditProfileActivity
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
package com.example.cmput301f18t14.picmymed.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cmput301f18t14.picmymed.Controller.PicMyMedApplication;
import com.example.cmput301f18t14.picmymed.Controller.PicMyMedController;
import com.example.cmput301f18t14.picmymed.Model.Patient;
import com.example.cmput301f18t14.picmymed.R;

/**
 * EditProfileActivity extends AppCompatActivity and
 * handles a user editing their phone number and email in their profile
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class EditProfileActivity extends AppCompatActivity {

    /**
     * Method sets the EditProfileActivity state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_activity);

        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();

        final EditText showPhoneNumber = (EditText)findViewById(R.id.enteredPhone);
        showPhoneNumber.setText(user.getPhoneNumber());

        final EditText showEmail = (EditText)findViewById(R.id.enteredEmail);
        showEmail.setText(user.getEmail());


        Button editProfileButton = findViewById(R.id.updateButton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = showEmail.getText().toString();
                String phone = showPhoneNumber.getText().toString();
                PicMyMedController.updatePatientProfile(email, phone);
                onBackPressed();

            }
        });
    }

}
