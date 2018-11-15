package com.example.mukha.picmymedcode.ProblemFile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.Date;

public class AddProblemActivity extends AppCompatActivity{
    private Date date;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    ProblemList problemList = new ProblemList();
    private static final String FILENAME = "file.sav";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproblem_activity);

        final EditText problemTitleEditText = findViewById(R.id.problem_title_edit_text);
        final EditText problemDescriptionEditText = findViewById(R.id.problem_description_edit_text);

        Button problemSaveButton = findViewById(R.id.problem_save_button);
        problemSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Problem problem = new Problem (date,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString());
                problemList.addProblem(problem);
                saveInFile();
                Intent problemIntent = new Intent(AddProblemActivity.this,ProblemActivity.class);
                startActivity(problemIntent);

            }
        });
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        mAdapter = new ProblemAdapter(getApplicationContext(),problemList.getProblemList());

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

            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
