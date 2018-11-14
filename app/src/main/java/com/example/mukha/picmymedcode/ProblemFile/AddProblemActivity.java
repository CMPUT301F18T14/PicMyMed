package com.example.mukha.picmymedcode.ProblemFile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.mukha.picmymedcode.R;

public class AddProblemActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproblem_activity);

        EditText problemTitleEditText = (EditText) findViewById(R.id.problem_title_edit_text);
        EditText problemDescriptionEditText = (EditText) findViewById(R.id.problem_description_edit_text);


    }
}
