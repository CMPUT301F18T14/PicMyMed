/*
 * CareProviderAddComment
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
import android.widget.TextView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * CareProviderAddComment extends AppCompatActivity so a care provider can
 * add a comment
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class CareProviderAddComment extends AppCompatActivity{
    Patient patient;
    public ArrayList<Problem> problemArrayList;

    /**
     * Method sets the careprovider state to add comments
     *
     * @param savedInstanceState    Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcomment_activity);
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        final int position = getIntent().getIntExtra("key2",0);
        problemArrayList = patient.getProblemList();
        Button saveCommentButton = (Button) findViewById(R.id.comment_save_button);

        saveCommentButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles the user clicking the save button
             *
             * @param v View
             */
            public void onClick(View v) {
                //time stamp
                SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.getDefault());
                final String date = mSimpleDateFormat.format(new Date());
                //adding comment
                TextView commentEditText = (TextView)findViewById(R.id.comment_edit_text);
                String result = commentEditText.getText().toString();
                User user = PicMyMedApplication.getLoggedInUser();
                String result2 = result+"\n\n\n commented by "+ user.getUsername().toString()+"\n"+date;
                problemArrayList.get(position).addCommentList(result2);
                PicMyMedController.updateUser(patient, CareProviderAddComment.this);
                onBackPressed();
                // TODO Auto-generated method stub
            }
        });



    }
}
