/*
 * CareProviderCommentActivity
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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;

import java.util.ArrayList;

/**
 * CareProviderAddCommentActivity extends AppCompatActivity to
 * add a comment
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class CareProviderCommentActivity extends AppCompatActivity{
    Patient patient;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    SwipeRefreshLayout swipeLayout;

    final int position = CareProviderRecordActivity.problemPosition;

    /**
     * Method sets the state
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careprovidercomment_activity);
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        final User user = PicMyMedApplication.getLoggedInUser();// log in user

        //get patient name from intent
        String name = getIntent().getStringExtra("name");//pass intent name
        //patient object

        //set name text view
        TextView patientName = findViewById(R.id.careprovider_comment_name_text_view);
        patientName.setText(CareProviderProblemActivity.name);

        //set phone number text view
        TextView patientPhone = findViewById(R.id.careprovider_comment_phone_text_view);
        patientPhone.setText(patient.getPhoneNumber());
        //wow factor pass intent to call
        patientPhone.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles a user clicking on the patients phone number
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);// Send phone number to intent as data
                intent.setData(Uri.parse("tel:" + patient.getPhoneNumber()));// Start the dialer app activity with number
                startActivity(intent);
            }
        });

        //set phone number text view
        TextView patientEmail = findViewById(R.id.careprovider_comment_email_text_view);
        patientEmail.setText(patient.getEmail());
        //wow factor pass intent email
        patientEmail.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on the patient's email
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822"); //specifies message for email app.
                emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{patient.getEmail().toString()});//add patient email
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi "+patient.getUsername().toString()+","); //adds the actual content of the email by calling the method previously defined
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "NEW MESSAGE FROM YOUR CARE PROVIDER [" +user.getUsername().toString()+"]"); //adds the subject by calling the method previously defined.
                startActivity(Intent.createChooser(emailIntent, "Title of the dialog chooser"));
            }
        });

        manageRecyclerview();

        //swipe to refresh
        swipeLayout = findViewById(R.id.careprovider_comment_swipeRefresh);
        // Adding Listener
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            /**
             * Method handles the user swiping down on the screen to refresh the comments
             *
             */
            @Override
            public void onRefresh() {

                if (PicMyMedApplication.isNetworkAvailable(CareProviderCommentActivity.this)) {
                    // To keep animation for 4 seconds
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            PicMyMedApplication.getMostRecentChanges();
                            manageRecyclerview();
                            // Stop animation (This will be after 3 seconds)
                            swipeLayout.setRefreshing(false);
                            Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_LONG).show();
                        }
                    }, 2000); // Delay in millis

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
     * Method handles recycler view of patients
     */
    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        mRecyclerView = findViewById(R.id.careprovider_comment_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new CareProviderCommentAdapter(CareProviderCommentActivity.this, problemArrayList.get(position).getCommentList());

        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method starts the activity
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //get patient name from intent
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        // problemPosition = position cicked
        mAdapter = new CareProviderCommentAdapter(CareProviderCommentActivity.this, problemArrayList.get(position).getCommentList());
        //loadFromFile();
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }
}
