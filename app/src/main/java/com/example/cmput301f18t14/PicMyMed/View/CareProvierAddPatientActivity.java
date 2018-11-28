package com.example.cmput301f18t14.PicMyMed.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.cmput301f18t14.PicMyMed.Controller.ElasticSearchController;
import com.example.cmput301f18t14.PicMyMed.Controller.PicMyMedApplication;
import com.example.cmput301f18t14.PicMyMed.Controller.PicMyMedController;
import com.example.cmput301f18t14.PicMyMed.Model.CareProvider;
import com.example.cmput301f18t14.PicMyMed.R;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Update;

public class CareProvierAddPatientActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView mListView;
    private PatientListViewAdapter mAdapter;
    private SearchView searchView;
    public static ArrayList<String> patientName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientsearch_layout);

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
                Toast.makeText(CareProvierAddPatientActivity.this, patientName.get(position)+" is added "+name, Toast.LENGTH_SHORT).show();
                user.getPatientList().add(patientName.get(position));
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


    /*protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        mListView = findViewById(R.id.patient_listview);
        ArrayList<String> patientUsernames = PicMyMedController.getAllPatients();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.patientlist_layout, patientUsernames);
        mListView.setAdapter(arrayAdapter);
    } */
}
