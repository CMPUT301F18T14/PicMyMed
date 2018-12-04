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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.picmymedphotohandler.GalleryActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.User;
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
    public Patient user;
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

        user = (Patient)PicMyMedApplication.getLoggedInUser();

        if (user.getBodyLocationPhotoList().size()==0) {
            try {
                String imageUri = "drawable://" + R.drawable.default_bodyloc;

                BodyLocationPhoto bodyLocationPhoto = new BodyLocationPhoto(imageUri);
                bodyLocationPhoto.setLabel("Default Photo");
                bodyLocationPhoto.setBase64EncodedString("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");
                //user.getBodyLocationPhotoList().add(bodyLocationPhoto);
                PicMyMedController.addBodyLocationPhoto(bodyLocationPhoto, PatientActivity.this);
            } catch (Exception e ) {
                Log.i("DEBUG", e.getMessage());
            }
        }

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
            case R.id.pushData:
                User user = (Patient)PicMyMedApplication.getLoggedInUser();
                if (PicMyMedApplication.isNetworkAvailable(PatientActivity.this)) {
                    PicMyMedController.updateUser(user, PatientActivity.this);
                    Toast.makeText(getApplicationContext(), "Data is synced!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You are offline" , Toast.LENGTH_SHORT).show();
                }

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
