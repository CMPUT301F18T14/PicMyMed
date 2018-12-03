/*
 * CareProviderAddPatientAdapter
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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;

import java.util.ArrayList;

/**
 * CareProviderAddPatientAdapter extends AppCompatActivity and implements SearchView to
 * add a patient
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class CareProviderAddPatientAdapter extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView mListView;
    private PatientListViewAdapter mAdapter;
    private SearchView searchView;
    public static ArrayList<String> patientName;

    /**
     * Method creates the state
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientsearch_activity);

        //Load all data to array
        patientName = PicMyMedController.getAllPatientUsernames();
        mListView = findViewById(R.id.patient_listview);

        //passing array into PatientListViewAdapter
        mAdapter = new PatientListViewAdapter(this);

        //binds the adapter to PatientListViewAdapter
        mListView.setAdapter(mAdapter);

        //Locate the edittext
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
/*
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();
                String name = user.getUsername();
               // Toast.makeText(CareProviderAddPatientAdapter.this, patientName.get(position)+" is added "+name, Toast.LENGTH_SHORT).show();
                PicMyMedController.addPatientToCareProvider(patientName.get(position));
                onBackPressed();//go back to previous activity

            }

        });*/

    }

    /**
     * Method checks text entry to search by
     *
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Method checks if text query has changed
     *
     * @param newText   String
     * @return          boolean
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        mAdapter.filter(text);
        return false;
    }
}
