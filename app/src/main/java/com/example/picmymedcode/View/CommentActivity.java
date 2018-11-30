package com.example.picmymedcode.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity{
    public ArrayList<Problem> problemArrayList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    int position;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientcomment_activity);
        manageRecyclerview();
    }

    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();
        Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        mRecyclerView = findViewById(R.id.patient_comment_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new CareProviderCommentAdapter(CommentActivity.this, problemArrayList.get(position).getCommentList());

        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        position = getIntent().getIntExtra("key",0);
        problemArrayList.get(position).getCommentList();
        // problemPosition = position cicked
        mAdapter = new CareProviderCommentAdapter(CommentActivity.this, problemArrayList.get(position).getCommentList());
        //loadFromFile();
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }
}
