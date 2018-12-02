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

public class RecordFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    Patient patient;
    View v;

    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_record, container, false);
        manageRecyclerview();
        return v;
    }

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