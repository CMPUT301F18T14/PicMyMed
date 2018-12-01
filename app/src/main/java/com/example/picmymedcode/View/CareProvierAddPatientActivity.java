package com.example.picmymedcode.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.QRCode.ScannerActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class CareProvierAddPatientActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView mListView;
    private PatientListViewAdapter mAdapter;
    private SearchView searchView;
    public static ArrayList<String> patientName;
    private final static int REQUEST_CODE = 100;
    private final static int PERMISSION_REQUEST = 200;
    private Barcode barcode;

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
                Toast.makeText(CareProvierAddPatientActivity.this, patientName.get(position)+" is added "+name, Toast.LENGTH_SHORT).show();
                PicMyMedController.addPatientToCareProvider(patientName.get(position));
                //UpdateCareProvider();
                onBackPressed();//go back to previous activity

            }

        });


        ImageView imageView = findViewById(R.id.careprovider_qr);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CareProvierAddPatientActivity.this, new String[] {Manifest.permission.CAMERA}, PERMISSION_REQUEST);
                }
                Intent scannerIntent = new Intent(CareProvierAddPatientActivity.this, ScannerActivity.class);
                startActivityForResult(scannerIntent, REQUEST_CODE);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("CareProvider", "FUCKED");
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                barcode = data.getParcelableExtra("barcode");
                if (barcode != null) {
                    Log.d("DEBUG", barcode.displayValue);
                    String username = PicMyMedController.getUsernameByID(barcode.displayValue);
                    Log.d("DEBUG",PicMyMedApplication.getLoggedInUser().getRandomUserID());
                    if (!username.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Username "+username+" found", Toast.LENGTH_LONG).show();
                        PicMyMedController.addPatientToCareProvider(username);
                        onBackPressed();
                    } else {
                        Toast.makeText(getApplicationContext(), "Username with "+username+" was not found!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
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
