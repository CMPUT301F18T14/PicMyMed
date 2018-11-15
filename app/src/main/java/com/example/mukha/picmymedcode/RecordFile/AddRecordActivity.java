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

public class AddRecordActivity extends AppCompatActivity{
    RecordList recordList = new RecordList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord_activity);
        final EditText recordTitleEditText = findViewById(R.id.record_title_edit_text);
        final EditText recordDescriptionEditText = findViewById(R.id.record_description_edit_text);

        Button recordSaveButton = findViewById(R.id.record_save_button);
        recordSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record = new Record (recordTitleEditText.getText().toString());
                record.setComment(recordDescriptionEditText.getText().toString());
                recordList.addRecord(record);
                //saveInFile();
                Intent problemIntent = new Intent(AddRecordActivity.this,RecordActivity.class);
                startActivity(problemIntent);

            }
        });

    }
}
