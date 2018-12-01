package com.example.picmymedcode.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class EditRecordActivity extends AppCompatActivity {
    private Patient user;
    private Problem problem;
    private Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record_activity);

        Intent editRecordIntent = getIntent();
        String problemIndex = editRecordIntent.getStringExtra("problem index");
        int recordIndex = editRecordIntent.getIntExtra("record index", 0);

        user = (Patient) PicMyMedApplication.getLoggedInUser();
        if (user.isPatient()) {
            ArrayList<Problem> problemArrayList = user.getProblemList();
            problem = problemArrayList.get(RecordActivity.position);
            record = problem.getRecordList().get((int) recordIndex);
        }
        else {
            finish();
        }

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

    protected void onResume() {

        super.onResume();
        if (user == null) {
            user = (Patient) PicMyMedApplication.getLoggedInUser();
        }
        if (PicMyMedController.checkIfSameDevice(user) == 0) {
            Toast.makeText(getApplicationContext(), "Session expired. You have logged in from another device.", Toast.LENGTH_SHORT).show();
            PicMyMedApplication.logout(EditRecordActivity.this );
        }
    }

}

