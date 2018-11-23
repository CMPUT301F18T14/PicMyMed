/*
 * CareProviderActivity
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
package com.example.cmput301f18t14.PicMyMed.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmput301f18t14.PicMyMed.Controller.PicMyMedApplication;
import com.example.cmput301f18t14.PicMyMed.Model.CareProvider;
import com.example.cmput301f18t14.PicMyMed.Model.Patient;
import com.example.cmput301f18t14.PicMyMed.R;

import java.util.ArrayList;

/**
 * CareProviderActivity extends AppCompatActivity tp
 * handle a care provider logging into the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class CareProviderActivity extends AppCompatActivity {

    ListView patientListView;
    ArrayList<Patient> patientList;

    /**
     * Method sets the CareProviderActivity state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcareprovider_activity);

        //creates the welcome care provider text at the top
        CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();
        TextView careProviderName = (TextView) findViewById(R.id.careProviderName);
        String welcomeText = getResources().getString(R.string.careProviderWelcomeAndName)
                + " " + user.getUsername();
        careProviderName.setText(welcomeText);


        patientListView = (ListView) findViewById(R.id.PatientList);
        patientList = new ArrayList<Patient>();
        //fake temp data
        Patient patient1 = new Patient("123","123@a.ca","7801112222");
        Patient patient2 = new Patient("bomba","123@a.ca","7801112222");
        Patient patient3 = new Patient("k1tt3n","123@a.ca","7801112222");
        patientList.add(patient1);
        patientList.add(patient2);
        patientList.add(patient3);

        ArrayList<String> arrayPatientList = new ArrayList<>();
        //populate the array list with patient fake temp data
        for (int i = 0; i < patientList.size();i++){
            arrayPatientList.add(i,patientList.get(i).getUsername());
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.patientlist_layout, arrayPatientList);
        patientListView.setAdapter(arrayAdapter);

        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CareProviderActivity.this,CareProviderProblemActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        //search for new patient button
        Button addPatientButton = findViewById(R.id.addPatientButton);
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.patientsearch_layout);
                EditText enteredPatient = (EditText) findViewById(R.id.enteredPatientID);
                String patientToAdd = enteredPatient.getText().toString();
                Toast.makeText(getApplicationContext(), patientToAdd,
                        Toast.LENGTH_LONG).show();
                //Need to implement try catch to take username and search if it's available

            }
        });

    }


}