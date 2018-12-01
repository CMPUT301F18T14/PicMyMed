package com.example.picmymedcode.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class EditRecordActivity extends AppCompatActivity {
    private Patient user = (Patient) PicMyMedApplication.getLoggedInUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record_activity);

        Intent editRecordIntent = getIntent();
        String problemIndex = editRecordIntent.getStringExtra("problem index");
        int recordIndex = editRecordIntent.getIntExtra("record index", 0);

        final Patient user = (Patient) PicMyMedApplication.getLoggedInUser();
        ArrayList<Problem> problemArrayList = user.getProblemList();
        final Problem problem = problemArrayList.get(RecordActivity.position);
        final Record record = problem.getRecordList().get((int) recordIndex);

        final EditText editTitle = (EditText) findViewById(R.id.record_title_edit_text);
        editTitle.setText(record.getTitle());
        final EditText editDescription = (EditText) findViewById(R.id.record_description_edit_text);
        editDescription.setText(record.getDescription());

        Button editRecordButton = findViewById(R.id.record_save_button);
        editRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record.setTitle(editTitle.getText().toString());
                record.setDescription(editDescription.getText().toString());
                PicMyMedController.updateUser(user);
                finish();
            }
        });

    }

}

