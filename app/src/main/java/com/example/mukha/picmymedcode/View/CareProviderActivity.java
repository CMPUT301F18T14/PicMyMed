package com.example.mukha.picmymedcode.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mukha.picmymedcode.R;

import java.util.ArrayList;


public class CareProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcareprovider_activity);

        ListView patientList = (ListView) findViewById(R.id.PatientList);
        ArrayList<String> PatientList = patientList.getPatientList();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.newcareprovider_activity, PatientList);
        patientList.setAdapter(arrayAdapter);

        Button addPatientButton = findViewById(R.id.addPatientButton);
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.patientsearch_layout);
                EditText enteredPatient = (EditText) findViewById(R.id.enteredPatientID);
                String patientToAdd = enteredPatient.getText().toString();
                Toast.makeText(getApplicationContext(), patientToAdd,
                        Toast.LENGTH_LONG).show();
                //Need to implement try catch to take username and search if it's available

            }
        });

    }


}
