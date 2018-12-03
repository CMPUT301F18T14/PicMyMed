/*
 * CareProviderCommentAdapter
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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * CareProviderCommentAdapter extends RecyclerView to
 * view comments
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class CareProviderCommentAdapter extends RecyclerView.Adapter<CareProviderCommentAdapter.ProblemViewHolder>{
    private static final String FILENAME = "file.sav";


    private ArrayList<String> problemsdata;
        Context context;

/**
 * Method extends ViewHolder
 */
public static class ProblemViewHolder extends RecyclerView.ViewHolder{
    TextView commentTextView;


    /**
     * Method handles how problems are viewed
     *
     * @param itemView View
     */
    public ProblemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.commentTextView = (TextView) itemView.findViewById(R.id.careprovider_comment_text_view);
    }
}

    /**
     * Method handles care providers comments
     *
     * @param context
     * @param problemsdata
     */
    public CareProviderCommentAdapter(Context context, ArrayList<String> problemsdata){
        this.problemsdata = problemsdata;
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
    public CareProviderCommentAdapter.ProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentcard_layout,parent,false);
        CareProviderCommentAdapter.ProblemViewHolder myViewHolder = new CareProviderCommentAdapter.ProblemViewHolder(view);
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
    public void onBindViewHolder(@NonNull final CareProviderCommentAdapter.ProblemViewHolder myViewHolder, final int listPosition) {
        //set title
        TextView commentTextView = myViewHolder.commentTextView;
        commentTextView.setText(problemsdata.get(listPosition));

    }

    /**
     * Method gets number of problems
     *
     * @return  int
     */
    @Override
    public int getItemCount() {
        return (problemsdata == null) ? 0 : problemsdata.size();
    }



}
