/*
 * CareProviderAdapter
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

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;

import java.util.ArrayList;

/**
 * CareProviderAdapter extends RecyclerView.Adapter to
 * handle a care provider logging into the application
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class CareProviderAdapter extends RecyclerView.Adapter<com.example.picmymedcode.View.CareProviderAdapter.CareProviderViewHolder> {

    private static final String FILENAME = "file.sav";


    private ArrayList<String> patientnameData;
    Context context;

    /**
     * Method extends ViewHolder
     */
    public static class CareProviderViewHolder extends RecyclerView.ViewHolder{
        TextView patientNameTextView;
        ImageView patientDeleteImageView;

        /**
         * Method handles how problems are viewed
         *
         * @param itemView View
         */
        public CareProviderViewHolder (@NonNull View itemView) {
            super(itemView);
            this.patientNameTextView = (TextView) itemView.findViewById(R.id.patientlist_name_text_view);
            this.patientDeleteImageView = (ImageView) itemView.findViewById(R.id.patient_delete_image_view);
        }
    }

    /**
     * Method gets patient data
     *
     * @param context           Context
     * @param patientnameData   ArrayList<String></String>
     */
    public CareProviderAdapter (Context context, ArrayList<String> patientnameData){
        this.patientnameData = patientnameData;
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
    public CareProviderAdapter.CareProviderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patientnamecard_layout,parent,false);
        CareProviderAdapter.CareProviderViewHolder myViewHolder = new CareProviderAdapter.CareProviderViewHolder(view);
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
    public void onBindViewHolder(@NonNull final CareProviderAdapter.CareProviderViewHolder myViewHolder, final int listPosition) {
        TextView patientNameTextView = myViewHolder.patientNameTextView;
        patientNameTextView.setText(patientnameData.get(listPosition));


        myViewHolder.patientNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            //onClick to go to next activity
            public void onClick(View v) {
                Intent Intent = new Intent(context,CareProviderProblemActivity.class);
                Intent.putExtra("name",patientnameData.get(listPosition));
                context.startActivity(Intent);
            }
        });
        //delete patient
        ImageView patientDeleteImageView = myViewHolder.patientDeleteImageView;
        myViewHolder.patientDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            //onClick to go to next activity
            public void onClick(View v) {
                patientnameData.remove(listPosition);
                PicMyMedController.updateUser(PicMyMedApplication.getLoggedInUser(), context);
                notifyDataSetChanged();
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
        return (patientnameData == null) ? 0 : patientnameData.size();
    }


}