/*
 * CareProviderProblemActivity
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
package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.R;

/**
 * CareProviderProblemActivity extends AppCompatActivity
 * and handles displaying a care provider's patient's problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class CareProviderProblemActivity extends AppCompatActivity {

    TextView patientName;
    TextView patientPhoneNumber;
    TextView patientEmail;

    int position;

    /**
     * Creates the CareProviderProblemActivity state
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproviderproblem_activity);

        Intent intentData = getIntent();
        position = intentData.getIntExtra("position",0);

        patientName = (TextView) findViewById(R.id.PatientUsername);
        patientPhoneNumber = (TextView) findViewById(R.id.PatientPhoneNumber);
        patientEmail = (TextView) findViewById(R.id.PatientEmail);


        CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();

        /* fails due to using dummy variables in previous activity
        Patient patient = user.getPatientList().getPatient(position);

        patientName.setText(patient.getUsername());
        patientPhoneNumber.setText(patient.getPhoneNumber());
        patientEmail.setText(patient.getEmail());*/


    }
}
