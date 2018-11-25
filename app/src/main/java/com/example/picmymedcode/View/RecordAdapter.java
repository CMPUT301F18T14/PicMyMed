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

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    /**
     * Method extends RecyclerView.Holder to manage how records are displayed
     */
    public static class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView recordTitleTextView;
        //TextView recordLocationTextView;
        TextView recordDescriptionTextView;

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
        }
    }

    /**
     * Method handles record data
     *
     * @param recordsdata   ArrayList<Record>
     */
    public RecordAdapter(ArrayList<Record> recordsdata) {
        this.records = recordsdata;
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
    public void onBindViewHolder(@NonNull RecordViewHolder recordViewHolder, int i) {
        TextView recordTitleTextView = recordViewHolder.recordTitleTextView;
        //TextView recordLocationTextView = recordViewHolder.recordLocationTextView;
        TextView recordDescriptionTextView = recordViewHolder.recordDescriptionTextView;
        recordTitleTextView.setText(records.get(i).getTitle());
        //recordLocationTextView.setText(records.get(i).getTitle());
        recordDescriptionTextView.setText(records.get(i).getDescription());

    }

    /**
     * Method gets the number of records
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return records.size();
    }
}
