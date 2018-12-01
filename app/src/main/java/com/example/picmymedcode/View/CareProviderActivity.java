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
package com.example.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;

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



    ArrayAdapter<String> arrayAdapter;
    private RecyclerView mRecyclerView;
    private CareProviderAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    private View.OnClickListener mListener;
    public ArrayList<String> patientArrayList;

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
        try {
            String welcomeText = getResources().getString(R.string.careProviderWelcomeAndName)
                    + " " + user.getUsername();
            careProviderName.setText(welcomeText);
        } catch (Exception e) {
            Log.d("DEBUG CPA", e.getMessage());
            String welcomeText = "Unknown";
        }
        manageRecyclerview();


        //search for new patient button
        Button addPatientButton = findViewById(R.id.addPatientButton);
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareProviderActivity.this,CareProvierAddPatientActivity.class);
                startActivity(intent);

            }
        });

        ImageView careproviderProfile = (ImageView) findViewById(R.id.view_profile);
        careproviderProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent profileIntent = new Intent(CareProviderActivity.this, ProfileActivity.class);
                startActivity(profileIntent);

            }
        });

    }


    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();
        mRecyclerView = findViewById(R.id.PatientList_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new CareProviderAdapter(CareProviderActivity.this, patientArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();
        //patientList = user.getPatientList();
        // arrayAdapter = new ArrayAdapter<String>(this,R.layout.patientlist_layout, patientList);
        //patientListView.setAdapter(arrayAdapter);
        // arrayAdapter.notifyDataSetChanged();


        super.onStart();
        CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();
        patientArrayList = user.getPatientList();
        //loadFromFile();
        mAdapter = new CareProviderAdapter(CareProviderActivity.this, patientArrayList);
        mRecyclerView.setAdapter(mAdapter);


        //loadFromFile();
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }


}