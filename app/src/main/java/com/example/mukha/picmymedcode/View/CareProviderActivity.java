package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.PatientList;
import com.example.mukha.picmymedcode.R;

import java.util.ArrayList;


public class CareProviderActivity extends AppCompatActivity {

    ListView patientListView;
    PatientList patientList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcareprovider_activity);

        //creates the welcome care provider text at the top
        CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();
        TextView careProviderName = (TextView) findViewById(R.id.careProviderName);
        String welcomeText = getResources().getString(R.string.careProviderWelcomeAndName)
                + " " + user.getUsername();
        careProviderName.setText(welcomeText);


        patientListView = (ListView) findViewById(R.id.PatientList);
        patientList = new PatientList();
        //fake temp data
        Patient patient1 = new Patient("123","123@a.ca","7801112222");
        Patient patient2 = new Patient("bomba","123@a.ca","7801112222");
        Patient patient3 = new Patient("k1tt3n","123@a.ca","7801112222");
        patientList.addPatient(patient1);
        patientList.addPatient(patient2);
        patientList.addPatient(patient3);

        ArrayList<String> arrayPatientList = new ArrayList<>();
        //populate the array list with patient fake temp data
        for (int i = 0; i < patientList.sizeOfPatientList();i++){
            arrayPatientList.add(i,patientList.getPatient(i).getUsername());
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.patientlist_layout, arrayPatientList);
        patientListView.setAdapter(arrayAdapter);

        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CareProviderActivity.this,CareProviderProblemActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        //search for new patient button
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