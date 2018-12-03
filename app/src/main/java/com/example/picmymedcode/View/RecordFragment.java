package com.example.picmymedcode.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class RecordFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SearchRecordAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    public ArrayList<Record> allRecordArrayList = new ArrayList<Record>(),filteredDataList;
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

        /*for (int i = 0; i<problemArrayList.size(); i++){
            AllrecordArrayList.addAll(problemArrayList.get(i).getRecordList());
        }*/
        manageRecyclerview();

        for (Problem problem : problemArrayList) {
            if (problem.getRecordList() != null) {
                allRecordArrayList.addAll(problem.getRecordList());
            }
        }


        SearchView searchView = v.findViewById(R.id.searchRecords);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredDataList = filter(allRecordArrayList, newText);
                mAdapter.setFilter(filteredDataList);
                return true;
            }
        });

        return v;
    }

    public void manageRecyclerview(){


        if (PicMyMedApplication.getLoggedInUser().isPatient()) {
            problemArrayList = ((Patient)PicMyMedApplication.getLoggedInUser()).getProblemList();
        } else {
            problemArrayList = ((Patient)PicMyMedController.getPatient(CareProviderProblemActivity.name)).getProblemList();
        }

        mRecyclerView = v.findViewById(R.id.fragment_record_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new SearchRecordAdapter(getContext(),allRecordArrayList);
        mRecyclerView.setAdapter(mAdapter);

    }

    private ArrayList<Record> filter(ArrayList<Record> recordArrayList, String newText) {
        newText=newText.toLowerCase();
        String text;
        String text2;
        filteredDataList=new ArrayList<>();
        for(Record dataFromDataList:recordArrayList){
            text=dataFromDataList.getDescription().toLowerCase();
            text2=dataFromDataList.getTitle().toLowerCase();
            if(text.contains(newText) || text2.contains(newText)){
                filteredDataList.add(dataFromDataList);
            }
        }

        return filteredDataList;
    }

}