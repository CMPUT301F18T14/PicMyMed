package com.example.mukha.picmymedcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Date;

public class ProblemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;

    public Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_activity);

        ProblemList problemList = new ProblemList();
        Problem problem = new Problem(date,"Problem","Description");
        problemList.addProblem(problem);
        Problem problem1 = new Problem(date,"Problem1","Description1");
        problemList.addProblem(problem1);

        mRecyclerView = (RecyclerView) findViewById(R.id.problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new ProblemAdapter(problemList.problemList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
