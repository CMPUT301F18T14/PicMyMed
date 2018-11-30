package com.example.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class CareProviderRecordActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    static int problemPosition;
    Patient patient;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproviderrecord_activity);
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        manageRecyclerview();


        ImageView addRecordImageView = (ImageView) findViewById(R.id.add_record_image_view);
        addRecordImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent problemIntent = new Intent(CareProviderRecordActivity.this,CareProviderAddComment.class);
                problemIntent.putExtra("key2",problemPosition);
                startActivity(problemIntent);

            }
        });

        ImageView careproviderViewCommentImageView = (ImageView) findViewById(R.id.careprovider_view_comment_image_view);
        careproviderViewCommentImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent problemIntent = new Intent(CareProviderRecordActivity.this,CareProviderCommentActivity.class);
                startActivity(problemIntent);

            }
        });


    }

    public void manageRecyclerview(){
        mRecyclerView = findViewById(R.id.careprovider_record_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new RecordAdapter(problemArrayList.get(problemPosition).getRecordList());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method starts the RecordActivity by getting the user and their problems
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        problemPosition = getIntent().getIntExtra("key",0);
        super.onStart();
        problemArrayList = patient.getProblemList();
        problemArrayList.get(problemPosition).getRecordList();
        //loadFromFile();
        mAdapter = new RecordAdapter(problemArrayList.get(problemPosition).getRecordList());
        mRecyclerView.setAdapter(mAdapter);

    }


}
