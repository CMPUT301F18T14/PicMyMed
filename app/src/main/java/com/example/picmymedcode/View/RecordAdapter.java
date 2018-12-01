/*
 * RecordAdapter
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

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.example.picmymedcode.Model.Record;

import java.util.ArrayList;

/**
 * RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>
 * to create an activity for the user to
 * view and manage problems
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private ArrayList<Record> records;
    Context context;

    /**
     * Method extends RecyclerView.Holder to manage how records are displayed
     */
    public static class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView recordTitleTextView;
        //TextView recordLocationTextView;
        TextView recordDescriptionTextView;
        TextView recordTimeTextView;
        ImageView recordMoreImageView;
        TextView recordTimeStampView;

        /**
         * Method takes itemView and assigns it the record title and description
         *
         * @param itemView  View
         */
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recordTitleTextView = itemView.findViewById(R.id.record_title_text_view);
            //this.recordLocationTextView = itemView.findViewById(R.id.record_location_text_view);
            this.recordDescriptionTextView = itemView.findViewById(R.id.record_description_text_view);
            this.recordTimeTextView = itemView.findViewById(R.id.record_time_text_view);
            this.recordTimeStampView = itemView.findViewById(R.id.record_time_text_view);

            this.recordMoreImageView = (ImageView) itemView.findViewById(R.id.record_more_bar);
            if (!PicMyMedApplication.getLoggedInUser().isPatient()){
                recordMoreImageView .setVisibility(View.INVISIBLE);
            }

        }
    }

    /**
     * Method handles record data
     *
     * @param recordsdata   ArrayList<Record>
     */
    public RecordAdapter(Context context, ArrayList<Record> recordsdata) {
        this.records = recordsdata;
        this.context = context;
    }

    /**
     * Method creates record view
     *
     * @param parent    ViewGroup
     * @param viewType  int
     * @return          myViewHolder
     */
    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordcard_layout,parent,false);
        RecordViewHolder myViewHolder = new RecordViewHolder(view);
        return myViewHolder;
    }

    /**
     * Method sets view when specific record is selected
     *
     * @param recordViewHolder  RecordViewHolder
     * @param i                 int
     */
    @Override
    public void onBindViewHolder(@NonNull final RecordViewHolder recordViewHolder,final int i) {
        TextView recordTitleTextView = recordViewHolder.recordTitleTextView;
        TextView recordDescriptionTextView = recordViewHolder.recordDescriptionTextView;
        TextView recordTimeTextView = recordViewHolder.recordTimeTextView;

        TextView recordTimeStampTextView = recordViewHolder.recordTimeStampView;
        recordTitleTextView.setText(records.get(i).getTitle());
        recordDescriptionTextView.setText(records.get(i).getDescription());
        recordTimeTextView.setText(records.get(i).getTimeStamp().toString());



//        recordViewHolder.recordTitleTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            //onClick to go to next activity
//            public void onClick(View v) {
//                Intent Intent = new Intent(context,EditRecordActivity.class);
//                Intent.putExtra("key", i);
//                context.startActivity(Intent);
//            }
//        });

        recordViewHolder.recordMoreImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, recordViewHolder.recordMoreImageView);
                //inflating menu from xml resource
                popup.inflate(R.menu.problem_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
                        ArrayList<Problem> problemArrayList = user.getProblemList();
                        final Problem problem = problemArrayList.get(RecordActivity.position);

                        switch (item.getItemId()) {
                            case R.id.edit:
                                //TODO edit
                                Intent Intent = new Intent(context,EditRecordActivity.class);
                                Intent.putExtra("problem index", RecordActivity.position);
                                Intent.putExtra("record index", i);
                                context.startActivity(Intent);
                                break;
                            case R.id.delete:
                                //handle menu2 click
                                //TODO delete
                                //Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
                                //ArrayList<Problem> problemArrayList;
                                //problemArrayList = user.getProblemList();
                                //problemArrayList.get(RecordActivity.position).getRecordList().remove(i);
                                //PicMyMedController.updatePatient(user);
                                //notifyDataSetChanged();
                                //saveInFile();


                                PicMyMedController.deleteRecord(problem, records.get(i));
                                notifyDataSetChanged();

                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });


        recordTimeStampTextView.setText(records.get(i).getDate().toString());

    }

    /**
     * Method gets the number of records
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return (records == null) ? 0 : records.size();
    }
}
