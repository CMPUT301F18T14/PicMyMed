/*
 * RecordActivity
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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.android.picmymedphotohandler.GalleryActivity;
import com.example.android.picmymedphotohandler.SlideshowActivity;
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

/**
 * RecordActivity extends AppCompatActivity to create an activity for the user to
 * view and manage problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class RecordActivity extends AppCompatActivity{
    private static final String FILENAME = "file.sav";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    static int position;

    /**
     * Method initializes RecordActivity state
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_activity_test_scroll);

        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        //loadFromFile();
        manageRecyclerview();
        position = getIntent().getIntExtra("key",0);
        String name = problemArrayList.get(position).getTitle();
      //  getSupportActionBar().setTitle(name);

        Button addRecordButton = findViewById(R.id.record_save_button);
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking add record button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this,AddRecordActivity.class);
                intent.putExtra("key",position);
                startActivity(intent);
            }
        });

        ImageButton galleryButton = findViewById(R.id.gallery_button);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking gallery button to view photos
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(RecordActivity.this,GalleryActivity.class);
                galleryIntent.putExtra("problemIndex", position);
                startActivity(galleryIntent);
            }
        });

        ImageButton slideshowButton = findViewById(R.id.slideshow_button);
        slideshowButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking slideshow button to view photo slideshow
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(RecordActivity.this,SlideshowActivity.class);
                startActivity(galleryIntent);
            }
        });

        //view comment button
        Button viewCommentButton = findViewById(R.id.view_comment_button);
        viewCommentButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking add record button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this,CommentActivity.class);
                intent.putExtra("key",position);
                startActivity(intent);
            }
        });
    }

    /**
     * Method manages recycler view to view records
     */
    public void manageRecyclerview(){
        mRecyclerView = findViewById(R.id.record_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new RecordAdapter(RecordActivity.this,problemArrayList.get(position).getRecordList());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method starts the RecordActivity by getting the user and their problems
     */
    protected void onStart() {
        // TODO Auto-generated method stub

        super.onStart();
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();


        //loadFromFile();
        mAdapter = new RecordAdapter(RecordActivity.this,problemArrayList.get(position).getRecordList());
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * Method loaded from file. No longer implemented, now loading from database
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
     * Method saved data to file. No longer implemented, now saving to database
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
