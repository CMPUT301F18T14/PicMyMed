package com.example.mukha.picmymedcode.RecordFile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mukha.picmymedcode.ProblemFile.AddProblemActivity;
import com.example.mukha.picmymedcode.ProblemFile.Problem;
import com.example.mukha.picmymedcode.ProblemFile.ProblemActivity;
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
import java.util.ArrayList;

public class AddRecordActivity extends AppCompatActivity{
    //RecordList recordList = new RecordList();
    public ArrayList<Problem> arrayListProblem;
    public ArrayList<Record> recordsArrayList;
    private static final String FILENAME = "file.sav";
    int position;

    protected void onCreate(Bundle savedInstanceState) {
        arrayListProblem = new ArrayList<>();
        recordsArrayList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord_activity);
        final EditText recordTitleEditText = findViewById(R.id.record_title_edit_text);
        final EditText recordDescriptionEditText = findViewById(R.id.record_description_edit_text);

        Button recordSaveButton = findViewById(R.id.record_save_button);
        recordSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Record record2 = new Record ("bye");
                //recordsArrayList.add(record2);
                Record record = new Record (recordTitleEditText.getText().toString());
                //record.setComment(recordDescriptionEditText.getText().toString());
                position = getIntent().getIntExtra("key",0);
                arrayListProblem.get(position).recordArrayList.add(record);
                saveInFile();
                onBackPressed();//go back to previous activity
            }
        });

    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListProblem = new TypeToken<ArrayList<Problem>>() {
            }.getType();
            arrayListProblem = gson.fromJson(reader, typeListProblem);

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
            gson.toJson(arrayListProblem,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
