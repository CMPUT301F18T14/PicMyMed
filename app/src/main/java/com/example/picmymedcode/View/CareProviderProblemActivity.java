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
package com.example.picmymedcode.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;

import java.util.ArrayList;

/**
 * CareProviderProblemActivity extends AppCompatActivity
 * and handles displaying a care provider's patient's problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class CareProviderProblemActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ProblemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    static String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproviderpatient_activity);
        final User user = PicMyMedApplication.getLoggedInUser();

        //get patient name from intent
        String name = getIntent().getStringExtra("name");//pass intent name
        //patient object
        final Patient patient = PicMyMedController.getPatient(name);

        //set name text view
        TextView patientName = findViewById(R.id.careprovider_problem_name_text_view);
        patientName.setText(name);

        //set phone number text view
        TextView patientPhone = findViewById(R.id.careprovider_problem_phone_text_view);
        patientPhone.setText(patient.getPhoneNumber());
        //wow factor pass intent to call
        patientPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);// Send phone number to intent as data
                intent.setData(Uri.parse("tel:" + patient.getPhoneNumber()));// Start the dialer app activity with number
                startActivity(intent);
            }
        });

        //set phone number text view
        TextView patientEmail = findViewById(R.id.careprovider_problem_email_text_view);
        patientEmail.setText(patient.getEmail());
        //wow factor pass intent email
        patientEmail.setOnClickListener(new View.OnClickListener() {
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

    }
    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();
        mRecyclerView = findViewById(R.id.careprovider_problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new ProblemAdapter(CareProviderProblemActivity.this, problemArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onStart() {

        // TODO Auto-generated method stub
        super.onStart();
        //get patient name from intent
        name = getIntent().getStringExtra("name");;//pass intent name
        //patient object
        final Patient patient = PicMyMedController.getPatient(name);
        problemArrayList = patient.getProblemList();
        // problemPosition = position cicked

        mAdapter = new ProblemAdapter(CareProviderProblemActivity.this, problemArrayList);
        //loadFromFile();
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }
}
