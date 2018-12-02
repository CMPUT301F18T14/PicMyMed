package com.example.picmymedcode.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class CareProviderCommentActivity extends AppCompatActivity{
    Patient patient;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;

    final int position = CareProviderRecordActivity.problemPosition;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careprovidercomment_activity);
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        final User user = PicMyMedApplication.getLoggedInUser();// log in user

        //get patient name from intent
        String name = getIntent().getStringExtra("name");//pass intent name
        //patient object

        //set name text view
        TextView patientName = findViewById(R.id.careprovider_comment_name_text_view);
        patientName.setText(CareProviderProblemActivity.name);

        //set phone number text view
        TextView patientPhone = findViewById(R.id.careprovider_comment_phone_text_view);
        patientPhone.setText(patient.getPhoneNumber());
        //wow factor pass intent to call
        patientPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);// Send phone number to intent as data
                intent.setData(Uri.parse("tel:" + patient.getPhoneNumber()));// Start the dialer app activity with number
                startActivity(intent);
            }
        });

        //set phone number text view
        TextView patientEmail = findViewById(R.id.careprovider_comment_email_text_view);
        patientEmail.setText(patient.getEmail());
        //wow factor pass intent email
        patientEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822"); //specifies message for email app.
                emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{patient.getEmail().toString()});//add patient email
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi "+patient.getUsername().toString()+","); //adds the actual content of the email by calling the method previously defined
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "NEW MESSAGE FROM YOUR CARE PROVIDER [" +user.getUsername().toString()+"]"); //adds the subject by calling the method previously defined.
                startActivity(Intent.createChooser(emailIntent, "Title of the dialog chooser"));
            }
        });

        manageRecyclerview();


    }

    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();
        problemArrayList = patient.getProblemList();
        mRecyclerView = findViewById(R.id.careprovider_comment_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new CareProviderCommentAdapter(CareProviderCommentActivity.this, problemArrayList.get(position).getCommentList());

        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //get patient name from intent
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        // problemPosition = position cicked
        mAdapter = new CareProviderCommentAdapter(CareProviderCommentActivity.this, problemArrayList.get(position).getCommentList());
        //loadFromFile();
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter = new ProblemAdapter(getApplicationContext(), problemArrayList);
    }
}
