/*
 * ProblemActivity
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
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * ProblemActivity extends AppCompatActivity to create an activity for the user to
 * view and manage problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class ProblemActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    private static final String FILENAME = "file.sav";
    public Date date;
    private RecyclerView mRecyclerView;
    private ProblemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    private View.OnClickListener mListener;
    public Patient patient;
    public ArrayList<Problem> problemArrayList;

    /**
     * Method initiates problem activity
     *
     * @param savedInstanceState Bundle
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_activity);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.problemToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        manageRecyclerview();


        Button addproblembutton = findViewById(R.id.problem_save_button);
        addproblembutton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking add problem button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent problemIntent = new Intent(ProblemActivity.this,AddProblemActivity.class);
                startActivity(problemIntent);
            }
        });

        Button profileButton = findViewById(R.id.profile_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on profile button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(ProblemActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        Button bodyLocationPhotosButton = findViewById(R.id.bodylocationphotos_button);
        bodyLocationPhotosButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on profile button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent bodyLocationPhotoManagerIntent = new Intent(ProblemActivity.this, BodyLocationPhotoManagerActivity.class);
                startActivity(bodyLocationPhotoManagerIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.problem_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addProblem:
                Intent problemIntent = new Intent(ProblemActivity.this, AddProblemActivity.class);
                startActivity(problemIntent);
                break;
            case R.id.bodylocationphotos:
                Intent bodyLocationPhotoManagerIntent = new Intent(ProblemActivity.this, BodyLocationPhotoManagerActivity.class);
                startActivity(bodyLocationPhotoManagerIntent);
                break;
            case R.id.profile:
                Intent profileIntent = new Intent(ProblemActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method manages problem view layout
     */
    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();
        mRecyclerView = findViewById(R.id.problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new ProblemAdapter(ProblemActivity.this, problemArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method starts problem activity
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        //loadFromFile();
        mAdapter = new ProblemAdapter(ProblemActivity.this, problemArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method loads data from saved file. No longer implemented as app loads from
     * database rather than local now
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListProblem = new TypeToken<ArrayList<Problem>>() {
            }.getType();
            problemArrayList = gson.fromJson(reader, typeListProblem);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Problem saves data to file. No longer implemented since app saves to database now.
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(problemArrayList,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
