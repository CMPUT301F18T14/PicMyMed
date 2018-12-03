/*
 * PatientActivity
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

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.picmymedphotohandler.GalleryActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.example.picmymedmaphandler.View.DrawMapActivity;
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
 * PatientActivity extends AppCompatActivity to create an activity for the user to
 * view and manage problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class PatientActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    private static final String FILENAME = "file.sav";
    public Date date;
    private RecyclerView mRecyclerView;
    private ProblemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    private View.OnClickListener mListener;
    public Patient patient;
    public ArrayList<Problem> problemArrayList;
    SwipeRefreshLayout swipeLayout;


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
        getSupportActionBar().setTitle("Problems");
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        swipeLayout = findViewById(R.id.problem_swipeRefresh);
        // Adding Listener
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            /**
             * Method handles user swiping on layout to refresh
             */
            @Override
            public void onRefresh() {

                if (PicMyMedApplication.isNetworkAvailable(PatientActivity.this)) {
                    // To keep animation for 4 seconds
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            PicMyMedApplication.getMostRecentChanges();
                            manageRecyclerview();
                            // Stop animation (This will be after 3 seconds)
                            swipeLayout.setRefreshing(false);
                            Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_LONG).show();
                        }
                    }, 3000); // Delay in millis

                }else {
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            // Stop animation (This will be after 3 seconds)
                            swipeLayout.setRefreshing(false);
                            Toast.makeText(getApplicationContext(), "No internet Connection!", Toast.LENGTH_LONG).show();
                        }
                    }, 500); // Delay in millis
                }

            }
        });

    }

    /**
     * Method creates the option menu
     *
     * @param menu  Menu
     * @return      onCreateOptionsMenu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.problem_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method handles menu item selected
     *
     * @param item  MenuItem
     * @return      onItemSelected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addProblem:
                Intent problemIntent = new Intent(PatientActivity.this, AddProblemActivity.class);
                startActivity(problemIntent);
                break;
            case R.id.bodylocationphotos:

                Intent bodyLocationPhotoManagerIntent = new Intent(PatientActivity.this, BodyLocationPhotoManagerActivity.class);
                startActivity(bodyLocationPhotoManagerIntent);
                break;
            case R.id.profile:
                Intent profileIntent = new Intent(PatientActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.photomap:
                Intent mapIntent = new Intent(PatientActivity.this, DrawMapActivity.class);
                mapIntent.putExtra("callingActivity", "AllProblem");
                startActivity(mapIntent);
                break;
            case R.id.search:
                Intent tabIntent = new Intent(PatientActivity.this, TabSearchActivity.class);
                startActivity(tabIntent);
                break;
            case R.id.logout:
                PicMyMedApplication.logoutDialog(PatientActivity.this);
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
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        mRecyclerView = findViewById(R.id.problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new ProblemAdapter(PatientActivity.this, problemArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method starts problem activity
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        manageRecyclerview();

        //loadFromFile();

    }

    /**
     * Method handles user clicking back
     *
     */
    @Override
    public void onBackPressed() {
        PicMyMedApplication.logoutDialog(PatientActivity.this);

    }
    /**
     * Method creates toast message to display on device
     *
     * @param message   String
     */
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
