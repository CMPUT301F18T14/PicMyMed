package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.R;

public class CareProviderProblemActivity extends AppCompatActivity {

    TextView patientName;
    TextView patientPhoneNumber;
    TextView patientEmail;

    int position;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproviderproblem_activity);

        Intent intentData = getIntent();
        position = intentData.getIntExtra("position",0);

        patientName = (TextView) findViewById(R.id.PatientUsername);
        patientPhoneNumber = (TextView) findViewById(R.id.PatientPhoneNumber);
        patientEmail = (TextView) findViewById(R.id.PatientEmail);


        CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();

        /* fails due to using dummy variables in previous activity
        Patient patient = user.getPatientList().getPatient(position);

        patientName.setText(patient.getUsername());
        patientPhoneNumber.setText(patient.getPhoneNumber());
        patientEmail.setText(patient.getEmail());*/


    }
}
