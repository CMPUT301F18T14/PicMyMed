package com.example.cmput301f18t14.PicMyMed.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.cmput301f18t14.PicMyMed.Controller.PicMyMedController;
import com.example.cmput301f18t14.PicMyMed.R;

import java.util.ArrayList;

public class CareProviderAddPatientActivity extends AppCompatActivity{
    ListView allPatientListView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientsearch_layout);

    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        allPatientListView = findViewById(R.id.patient_list);
        ArrayList<String> patientUsernames = PicMyMedController.getPatient();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.patientsearch_layout, patientUsernames);
        allPatientListView.setAdapter(arrayAdapter);



    }

}
