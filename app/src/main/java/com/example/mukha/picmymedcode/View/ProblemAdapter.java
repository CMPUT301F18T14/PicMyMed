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
package com.example.mukha.picmymedcode.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Controller.PicMyMedController;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.R;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * ProblemAdapter extends ecyclerView.Adapter<ProblemAdapter.PorblemViewHolder>
 * to create an activity for the user to
 * add a record to a problem
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.PorblemViewHolder>{
    private static final String FILENAME = "file.sav";


    private ArrayList<Problem> problems;
    Context context;

    /**
     * Method extends ViewHolder
     */
    public static class PorblemViewHolder extends RecyclerView.ViewHolder{
        TextView problemTitleTextView;
        TextView problemMoreTextView;

        /**
         * Method handles how problems are viewed
         *
         * @param itemView View
         */
        public PorblemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.problemTitleTextView = (TextView) itemView.findViewById(R.id.problem_title_text_view);
            this.problemMoreTextView = (TextView) itemView.findViewById(R.id.problem_more_bar);
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
    public PorblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.problemcard_layout,parent,false);
        PorblemViewHolder myViewHolder = new PorblemViewHolder(view);
        return myViewHolder;
    }

    /**
     * Method gets element from the dataset at a certain position and replaces contents of the view
     * with that element
     *
     * @param myViewHolder  PorblemViewHolder
     * @param listPosition  int
     */
    @Override
    public void onBindViewHolder(@NonNull final PorblemViewHolder myViewHolder, final int listPosition) {
        TextView problemTitleTextView = myViewHolder.problemTitleTextView;
        problemTitleTextView.setText(problems.get(listPosition).getTitle());
        myViewHolder.problemTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            //onClick to go to next activity
            public void onClick(View v) {
                Intent Intent = new Intent(context,RecordActivity.class);
                Intent.putExtra("key",listPosition);
                context.startActivity(Intent);
            }
        });


        myViewHolder.problemMoreTextView.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking on an item in the view
             *
             * @param view  View
             */
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, myViewHolder.problemMoreTextView);
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
                                PicMyMedController.deleteProblem(problems.get(listPosition));
                                notifyDataSetChanged();
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
