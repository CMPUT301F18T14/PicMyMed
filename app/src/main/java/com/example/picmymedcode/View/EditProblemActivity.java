/*
 * EditProblemActivity
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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * EditProblemActivity extends AppCompatActivity and handles a patient editing a problem
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class EditProblemActivity extends AppCompatActivity {
    public ArrayList<Problem> problemArrayList;
    int position;
    Date date;

    /**
     * Method sets EditProblemActivity state
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problemedit_activity);
        position = getIntent().getIntExtra("key",0);
        final EditText problemTitleEditText = findViewById(R.id.problem_edit_title_edit_text);
        final EditText problemDescriptionEditText = findViewById(R.id.problem_edit_description_edit_text);
        final EditText problemTimeEditText = findViewById(R.id.problem_edit_time_edit_text);

        problemTitleEditText.setHint(problemArrayList.get(position).getTitle());
        problemDescriptionEditText.setHint(problemArrayList.get(position).getDescription());


        Button problemSaveButton = findViewById(R.id.problem_edit_save_button);
        problemSaveButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Method handles user clicking save button to update a problem
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Problem problem = problemArrayList.get(position);
                PicMyMedController.editProblem(problem, date,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString());
               // Problem problem = new Problem (PicMyMedApplication.getUsername(),date,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString());
                //PicMyMedController.addProblem(problem);
                //problemArrayList.add(problem);
                //saveInFile();
                onBackPressed();//go back to previous activity
            }
        });
    }

    /**
     * Method starts the activity by getting the user and the problem list
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        //loadFromFile();
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }
}
