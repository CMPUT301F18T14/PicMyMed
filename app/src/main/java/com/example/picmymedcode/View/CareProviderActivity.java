/*
 * CareProviderActivity
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;

import java.util.ArrayList;

/**
 * CareProviderActivity extends AppCompatActivity to
 * handle a care provider logging into the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class CareProviderActivity extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;
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

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.careproviderToolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //creates the welcome care provider text at the top
        CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();
        TextView careProviderName = (TextView) findViewById(R.id.careProviderName);

        getSupportActionBar().setTitle("Welcome "+ user.getUsername());
/*        try {
            String welcomeText = getResources().getString(R.string.careProviderWelcomeAndName)
                    + " " + user.getUsername();
            careProviderName.setText(welcomeText);
        } catch (Exception e) {
            Log.d("DEBUG CPA", e.getMessage());
            String welcomeText = "Unknown";
        }
        */
        manageRecyclerview();


        //search for new patient button
        Button addPatientButton = findViewById(R.id.addPatientButton);
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareProviderActivity.this,CareProviderAddPatientActivity.class);
                startActivity(intent);

            }
        });

    }

    /**
     * Method creates toolbar menu
     *
     * @param menu  Menu
     * @return      menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.careprovider_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method handles toolbar menu items being selected
     *
     * @param item  MenuItem
     * @return      itemSelected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewProfile:
                Intent profileIntent = new Intent(CareProviderActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.logout:
                PicMyMedApplication.logoutDialog(CareProviderActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * method shows view of patients for care providers
     */
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

    /**
     * Method started when activity starts
     */
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

    /**
     * Method handles user pressing back
     */
    @Override
    public void onBackPressed() {
        PicMyMedApplication.logoutDialog(CareProviderActivity.this);

    }


}