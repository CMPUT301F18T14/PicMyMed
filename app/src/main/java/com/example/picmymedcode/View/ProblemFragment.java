package com.example.picmymedcode.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class ProblemFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private ProblemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    private View.OnClickListener mListener;
    public Patient patient;
    public ArrayList<Problem> problemArrayList;
    View v;

    public ProblemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_problem, container, false);
        manageRecyclerview();
        return v;

    }
    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();

        Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        mRecyclerView = v.findViewById(R.id.fragment_problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new ProblemAdapter(getContext(), problemArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
