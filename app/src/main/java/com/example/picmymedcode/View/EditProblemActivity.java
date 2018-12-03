/*
 * EditProblemActivity
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
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * EditProblemActivity extends AppCompatActivity and handles a patient editing a problem
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class EditProblemActivity extends AppCompatActivity {
    public ArrayList<Problem> problemArrayList;
    int position;
    String date;
    EditText problemTitleEditText;
    EditText problemDescriptionEditText;
    private TextView problemTimeEditText;
    private SimpleDateFormat mSimpleDateFormat;
    private Calendar mCalendar;
    private Activity mActivity;
    private String mDate;


    /**
     * Method sets EditProblemActivity state
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problemedit_activity);
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();

        position = getIntent().getIntExtra("key",0);
        problemTitleEditText = findViewById(R.id.problem_edit_title_edit_text);
        problemDescriptionEditText = findViewById(R.id.problem_edit_description_edit_text);
        problemTimeEditText = findViewById(R.id.problem_edit_time_text_view);

        problemTitleEditText.setText(problemArrayList.get(position).getTitle());
        problemDescriptionEditText.setText(problemArrayList.get(position).getDescription());
        problemTimeEditText.setText(problemArrayList.get(position).getStartDate());

        mDate = problemArrayList.get(position).getStartDate();

        //System.out.println("FUCK"+problemArrayList.get(position).getStartDate());
        mActivity = this;
        mSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.getDefault());
        problemTimeEditText.setOnClickListener(textListener);


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
                PicMyMedController.editProblem(problem, mDate,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString(), EditProblemActivity.this);
               // Problem problem = new Problem (PicMyMedApplication.getUsername(),date,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString());
                //PicMyMedController.addProblem(problem);
                //problemArrayList.add(problem);
                //saveInFile();
                onBackPressed();//go back to previous activity
            }
        });
    }

    /* Define the onClickListener, and start the DatePickerDialog with users current time */
    private final View.OnClickListener textListener = new View.OnClickListener() {
        /**
         * Method handles clicking on the date
         *
         * @param v View
         */
        @Override
        public void onClick(View v) {
            mCalendar = Calendar.getInstance();
            new DatePickerDialog(mActivity, mDateDataSet, mCalendar.get(Calendar.YEAR),
                    mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    };

    /* After user decided on a date, store those in our calendar variable and then start the TimePickerDialog immediately */
    private final DatePickerDialog.OnDateSetListener mDateDataSet = new DatePickerDialog.OnDateSetListener() {
        /**
         * Method handles setting the date
         *
         * @param view          DatePicker
         * @param year          int
         * @param monthOfYear   int
         * @param dayOfMonth    int
         */
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(mActivity, mTimeDataSet, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false).show();
        }
    };

    /* After user decided on a time, save them into our calendar instance, and now parse what our calendar has into the TextView */
    private final TimePickerDialog.OnTimeSetListener mTimeDataSet = new TimePickerDialog.OnTimeSetListener() {
        /**
         * Method sets the time
         *
         * @param view      TimePicker
         * @param hourOfDay int
         * @param minute    int
         */
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            problemTimeEditText.setText(mSimpleDateFormat.format(mCalendar.getTime()));
            mDate = mSimpleDateFormat.format(mCalendar.getTime());
        }
    };

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
