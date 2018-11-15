package com.example.mukha.picmymedcode.RecordFile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.mukha.picmymedcode.ProblemFile.AddProblemActivity;
import com.example.mukha.picmymedcode.ProblemFile.Problem;
import com.example.mukha.picmymedcode.ProblemFile.ProblemActivity;
import com.example.mukha.picmymedcode.ProblemFile.ProblemAdapter;
import com.example.mukha.picmymedcode.ProblemFile.ProblemList;
import com.example.mukha.picmymedcode.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public class RecordActivity extends AppCompatActivity{
    private static final String FILENAME = "file.sav";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    RecordList recordList = new RecordList();
    ProblemList problemList = new ProblemList();
    int position;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_activity);
        loadFromFile();

        Record record = new Record ("Record");
        record.setComment("xxxxxxxx");
        recordList.addRecord(record);

        position = getIntent().getIntExtra("key",0);
        String name = problemList.getProblem(position).getTitle();
        getSupportActionBar().setTitle(name);

        manageRecyclerview();

        Button addRecordButton = findViewById(R.id.record_save_button);
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this,AddRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void manageRecyclerview(){
        problemList.getProblem(position).getRecordList();
        mRecyclerView = findViewById(R.id.record_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new RecordAdapter(recordList.recordList);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //loadFromFile();



    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListProblem = new TypeToken<ProblemList>() {
            }.getType();
            problemList = gson.fromJson(reader, typeListProblem);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(problemList,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
