/*
 * AddProblemActivity
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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Controller.PicMyMedController;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.R;
import com.example.mukha.picmymedcode.Model.Record;
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
 * AddProblemActivity extends AppCompatActivity to create an activity for the patient to
 * to add a new problem.
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class AddProblemActivity extends AppCompatActivity{
    private Date date;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public ArrayList<Problem> problemArrayList;
    public ArrayList<Record> recordsArrayList;
    private static final String FILENAME = "file.sav";

    /**
     * Method initiates the add problem activity
     *
     * @param savedInstanceState Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproblem_activity);

        final EditText problemTitleEditText = findViewById(R.id.problem_title_edit_text);
        final EditText problemDescriptionEditText = findViewById(R.id.problem_description_edit_text);

        Button problemSaveButton = findViewById(R.id.problem_save_button);
        problemSaveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles activity action when user clicks the save problem button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Problem problem = new Problem (PicMyMedApplication.getUsername(), date,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString());
                PicMyMedController.addProblem(problem);
                //problemArrayList.add(problem);
                //saveInFile();
                onBackPressed();//go back to previous activity
            }
        });
    }

    /**
     * Method is started when activity begins to load data from file
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        //loadFromFile();
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }

    /**
     * Method loads saved data from file, if it exists
     * Used prior to implementation of elastic search
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
     * Method saves data to file
     * Used prior to implementation of elastic search
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(problemArrayList,osw);

            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
