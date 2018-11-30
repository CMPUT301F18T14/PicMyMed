package com.example.picmymedcode.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class CareProviderAddPatientAdapter extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView mListView;
    private PatientListViewAdapter mAdapter;
    private SearchView searchView;
    public static ArrayList<String> patientName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientsearch_activity);

        //Load all data to array
        patientName = PicMyMedController.getAllPatients();
        mListView = findViewById(R.id.patient_listview);

        //passing array into PatientListViewAdapter
        mAdapter = new PatientListViewAdapter(this);

        //binds the adapter to PatientListViewAdapter
        mListView.setAdapter(mAdapter);

        //Locate the edittext
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CareProvider user = (CareProvider) PicMyMedApplication.getLoggedInUser();
                String name = user.getUsername();
                Toast.makeText(CareProviderAddPatientAdapter.this, patientName.get(position)+" is added "+name, Toast.LENGTH_SHORT).show();
                PicMyMedController.addPatientToCareProvider(patientName.get(position));
                //UpdateCareProvider();
                onBackPressed();//go back to previous activity

            }

        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        mAdapter.filter(text);
        return false;
    }
}
