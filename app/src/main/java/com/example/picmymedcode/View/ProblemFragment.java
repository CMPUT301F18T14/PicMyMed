package com.example.picmymedcode.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProblemFragment extends Fragment  {
    private RecyclerView mRecyclerView;
    private SearchProblemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    private View.OnClickListener mListener;
    public Patient patient;
    public ArrayList<Problem> problemArrayList,filteredDataList;
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

        SearchView searchView = v.findViewById(R.id.searchProblem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredDataList = filter(problemArrayList, newText);
                mAdapter.setFilter(filteredDataList);
                return true;
            }
        });

        return v;

    }
    private void filter(String text) {
        ArrayList<Problem> filteredList = new ArrayList<>();

        for (int i = 0; i < problemArrayList.size(); i++) {
            if (problemArrayList.get(i).getDescription().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(problemArrayList.get(i));
            }
        }

        mAdapter.setFilter(filteredList);
    }
    public void manageRecyclerview(){

        if (PicMyMedApplication.getLoggedInUser().isPatient()) {
            problemArrayList = ((Patient)PicMyMedApplication.getLoggedInUser()).getProblemList();
        } else {
            problemArrayList = ((Patient)PicMyMedController.getPatient(CareProviderProblemActivity.name)).getProblemList();
        }

        mRecyclerView = v.findViewById(R.id.fragment_problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new SearchProblemAdapter(getContext(), problemArrayList);
        mRecyclerView.setAdapter(mAdapter);


    }




    private ArrayList<Problem> filter(ArrayList<Problem> problemArrayList, String newText) {
        newText=newText.toLowerCase();
        String text;
        String text2;
        filteredDataList=new ArrayList<>();
        for(Problem dataFromDataList:problemArrayList){
            text=dataFromDataList.getDescription().toLowerCase();
            text2=dataFromDataList.getTitle().toString();
            if(text.contains(newText) || text2.contains(newText)){
                filteredDataList.add(dataFromDataList);
            }
        }

        return filteredDataList;
    }
}
