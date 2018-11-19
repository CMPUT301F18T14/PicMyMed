package com.example.mukha.picmymedcode.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Controller.PicMyMedController;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.Model.Record;
import com.example.mukha.picmymedcode.R;

import java.util.ArrayList;
import java.util.Date;

public class EditProblemActivity extends AppCompatActivity {
    public ArrayList<Problem> problemArrayList;
    int position;
    Date date;
    protected void onCreate(Bundle savedInstanceState) {
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problemedit_activity);
        position = getIntent().getIntExtra("key",0);
        final EditText problemTitleEditText = findViewById(R.id.problem_edit_title_edit_text);
        final EditText problemDescriptionEditText = findViewById(R.id.problem_edit_description_edit_text);
        final EditText problemTimeEditText = findViewById(R.id.problem_edit_time_edit_text);

        problemTitleEditText.setHint(problemArrayList.get(position).getTitle());
        problemDescriptionEditText.setHint(problemArrayList.get(position).getDescription());


        Button problemSaveButton = findViewById(R.id.problem_edit_save_button);
        problemSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Problem problem = problemArrayList.get(position);
                PicMyMedController.editProblem(problem, date,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString());
               // Problem problem = new Problem (PicMyMedApplication.getUsername(),date,problemTitleEditText.getText().toString(),problemDescriptionEditText.getText().toString());
                //PicMyMedController.addProblem(problem);
                //problemArrayList.add(problem);
                //saveInFile();
                onBackPressed();//go back to previous activity
            }
        });
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        problemArrayList = user.getProblemList();
        //loadFromFile();
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }
}
