package com.example.picmymedcode.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class CareProviderAddComment extends AppCompatActivity{
    Patient patient;
    public ArrayList<Problem> problemArrayList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcomment_activity);
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        final int position = getIntent().getIntExtra("key2",0);
        problemArrayList = patient.getProblemList();
        Button saveCommentButton = (Button) findViewById(R.id.comment_save_button);

        saveCommentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView commentEditText = (TextView)findViewById(R.id.comment_edit_text);
                String result = commentEditText.getText().toString();
                problemArrayList.get(position).addCommentList(result);
                onBackPressed();
                // TODO Auto-generated method stub
            }
        });



    }
}
