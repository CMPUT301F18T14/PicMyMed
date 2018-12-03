/*
 * EditProfileActivity
 *
 * 1.2
 *
 * November 16, 2018
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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;

/**
 * EditProfileActivity extends AppCompatActivity and
 * handles a user editing their phone number and email in their profile
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class EditProfileActivity extends AppCompatActivity {

    private User user;
    EditText showPhoneNumber;
    EditText showEmail;
    Button editProfileButton;

    /**
     * Method sets the EditProfileActivity state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_activity);

        user = PicMyMedApplication.getLoggedInUser();

        editProfileButton = findViewById(R.id.updateButton);
        showPhoneNumber = (EditText)findViewById(R.id.enteredPhone);
        showEmail = (EditText)findViewById(R.id.enteredEmail);

        if (user != null) {
            showPhoneNumber.setText(user.getPhoneNumber());
            showEmail.setText(user.getEmail());
        }



        editProfileButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on the edit profile button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                String email = showEmail.getText().toString();
                String phone = showPhoneNumber.getText().toString();

                if (email.length() == 0) {
                    toastMessage("Email cannot be empty!");

                } else if (phone.length() == 0) {
                    toastMessage("Phone number cannot be empty!");

                } else if (!phone.matches("^[+]?[0-9]{10,13}$")) {
                    toastMessage("Phone number is invalid!");

                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    toastMessage("Invalid email address!");
                } else {
                    if (PicMyMedController.updateUserProfile(user, email, phone, EditProfileActivity.this) == 1 ) {
                        toastMessage("Successfully updated user profile.");
                        onBackPressed();
                    } else {
                        toastMessage("Unable to update user profile.");
                    }
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
