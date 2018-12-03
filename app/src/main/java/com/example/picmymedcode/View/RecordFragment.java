/*
 * RecordFragment
 *
 * 1.2
 *
 * November 16, 2018
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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;

/**
 * RecordFragment extends Fragment to handle records
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class RecordFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    Patient patient;
    View v;

    /**
     * Class constructor
     */
    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Creates the view
     *
     * @param inflater              LayoutInflater
     * @param container             ViewGroup
     * @param savedInstanceState    Bundle
     * @return                      inflater
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_record, container, false);
        manageRecyclerview();
        return v;
    }

    /**
     * Handles recycler view
     */
    public void manageRecyclerview(){

        if (!PicMyMedApplication.getLoggedInUser().isPatient()){
            patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
            problemArrayList = patient.getProblemList();
            problemArrayList.get(CareProviderRecordActivity.problemPosition).getRecordList();
            mRecyclerView = v.findViewById(R.id.fragment_record_recycle_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManage = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManage);
            mAdapter = new RecordAdapter(getContext(), problemArrayList.get(CareProviderRecordActivity.problemPosition).getRecordList());
            mRecyclerView.setAdapter(mAdapter);
        }else{
            Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
            problemArrayList = user.getProblemList();
            mRecyclerView = v.findViewById(R.id.fragment_record_recycle_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManage = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManage);
            mAdapter = new RecordAdapter(getContext(),problemArrayList.get(RecordActivity.position).getRecordList());
            mRecyclerView.setAdapter(mAdapter);
        }


    }

}