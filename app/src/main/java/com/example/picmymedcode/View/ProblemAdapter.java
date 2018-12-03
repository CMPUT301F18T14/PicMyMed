/*
 * ProblemAdapter
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

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * ProblemAdapter extends ecyclerView.Adapter<ProblemAdapter.ProblemViewHolder>
 * to create an activity for the user to
 * add a record to a problem
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ProblemViewHolder>{
    private static final String FILENAME = "file.sav";


    private ArrayList<Problem> problems;
    Context context;

    /**
     * Method extends ViewHolder
     */
    public static class ProblemViewHolder extends RecyclerView.ViewHolder{
        TextView problemTitleTextView;
        TextView problemDateTextView;
        TextView numberofRecordTextView;
        TextView descriptionTextView;
        ImageView problemMoreImageView;

        /**
         * Method handles how problems are viewed
         *
         * @param itemView View
         */
        public ProblemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.problemTitleTextView = itemView.findViewById(R.id.problem_title_text_view);
            this.problemDateTextView = itemView.findViewById(R.id.problem_date_text_view);
            this.numberofRecordTextView = itemView.findViewById(R.id.problem_record_text_view);
            this.descriptionTextView = itemView.findViewById(R.id.problem_description_text_view);
            this.problemMoreImageView = itemView.findViewById(R.id.problem_more_bar);


            if (!PicMyMedApplication.getLoggedInUser().isPatient()){
                problemMoreImageView .setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     *
     * @param context
     * @param problemsdata
     */
    public ProblemAdapter(Context context, ArrayList<Problem> problemsdata){
        this.problems = problemsdata;
        this.context = context; //collection of data
    }

    /**
     * Method checks if the existing view is being reused, otherwise it inflates the view
     *
     * @param parent    ViewGroup
     * @param viewType  viewType
     * @return          myViewHolder
     */
    @NonNull
    @Override
    public ProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.problemcard_layout,parent,false);
        ProblemViewHolder myViewHolder = new ProblemViewHolder(view);
        return myViewHolder;
    }

    /**
     * Method gets element from the dataset at a certain position and replaces contents of the view
     * with that element
     *
     * @param myViewHolder  ProblemViewHolder
     * @param listPosition  int
     */
    @Override

    public void onBindViewHolder(@NonNull final ProblemViewHolder myViewHolder, final int listPosition) {
        //set title
        TextView problemTitleTextView = myViewHolder.problemTitleTextView;
        problemTitleTextView.setText(problems.get(listPosition).getTitle().toUpperCase());
        //set date
        TextView problemDateTextView = myViewHolder.problemDateTextView;
        problemDateTextView.setText(problems.get(listPosition).getStartDate());
        //set number of record
        TextView numberofRecordTextView = myViewHolder.numberofRecordTextView;
        numberofRecordTextView.setText("Number of Records : "+problems.get(listPosition).getRecordList().size());
        //set description
        TextView DescriptionTextView = myViewHolder.descriptionTextView;
        if(problems.get(listPosition).getDescription().equals("")){
            DescriptionTextView.setVisibility(View.GONE);
        } else {
            DescriptionTextView.setText(problems.get(listPosition).getDescription());
        }

        if (!PicMyMedApplication.getLoggedInUser().isPatient()){
            myViewHolder.problemTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                //onClick to go to next activity
                public void onClick(View v) {
                    Intent Intent = new Intent(context,CareProviderRecordActivity.class);
                    Intent.putExtra("key",listPosition);
                    context.startActivity(Intent);
                }
            });
        }else{
            myViewHolder.problemTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                //onClick to go to next activity
                public void onClick(View v) {
                    Intent Intent = new Intent(context,RecordActivity.class);
                    Intent.putExtra("key",listPosition);
                    context.startActivity(Intent);
                }
            });
        }



        myViewHolder.problemMoreImageView.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on an item in the view
             *
             * @param view  View
             */
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, myViewHolder.problemMoreImageView);
                //inflating menu from xml resource
                popup.inflate(R.menu.problem_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent Intent = new Intent(context,EditProblemActivity.class);
                                Intent.putExtra("key",listPosition);
                                context.startActivity(Intent);
                                //handle menu1 click
                                break;
                            case R.id.delete:
                                //handle menu2 click
                                if (PicMyMedApplication.isNetworkAvailable(context)) {
                                    PicMyMedController.deleteProblem(problems.get(listPosition), context);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "You must be online to delete a problem" , Toast.LENGTH_SHORT).show();
                                }

                                //saveInFile();
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });


    }

    /**
     * Method gets number of problems
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return (problems == null) ? 0 : problems.size();
    }

    /**
     * Method saved data to file. No longer implemented, data now saved to database
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(problems,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
