package com.example.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.picmymedphotohandler.PhotoIntentActivity;
import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Geolocation;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;
import com.example.picmymedmaphandler.View.DrawMapActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class EditRecordActivity extends AppCompatActivity {
    public static final String TAG = "AddRecordActivity: ";
    public ArrayList<Problem> arrayListProblem;
    private static final String FILENAME = "file.sav";
    private static final int LAT_LNG_REQUEST_CODE = 786;
    private static final int CAMERA_REQUEST_CODE = 787;
    private TextView locationNameTextView;
    private Geolocation geolocation;
    private Photo photo;
    int position;
    Patient user = (Patient)PicMyMedApplication.getLoggedInUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        arrayListProblem = user.getProblemList();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord_activity);
        locationNameTextView = (TextView) findViewById(R.id.location_text);
        final EditText recordTitleEditText = findViewById(R.id.record_title_edit_text);
        final EditText recordDescriptionEditText = findViewById(R.id.record_description_edit_text);

        Button geoLocationButton = (Button) findViewById(R.id.record_geo_button);
        geoLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(EditRecordActivity.this,DrawMapActivity.class);
                startActivityForResult(mapIntent, LAT_LNG_REQUEST_CODE);
            }
        });

        Button cameraPhoto = (Button) findViewById(R.id.record_camera_button);
        cameraPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(EditRecordActivity.this,PhotoIntentActivity.class);
                startActivityForResult(photoIntent, CAMERA_REQUEST_CODE);
            }
        });

        Button recordSaveButton = findViewById(R.id.record_save_button);
        recordSaveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method handles user clicking the save record button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Record record = new Record (recordTitleEditText.getText().toString(), Calendar.getInstance().getTime());
                //Date timeStamp = Calendar.getInstance().getTime();
                //record.setDate(timeStamp);
                record.setDescription(recordDescriptionEditText.getText().toString());
                if(geolocation!=null) {
                    record.setLocation(geolocation);
                }
                if (photo != null){
                    record.addToPhotoList(photo);
                    System.out.println("I'm printing the phoro");
                    System.out.println(photo.getBase64EncodedString().length());
                }
                position = getIntent().getIntExtra("key",0);
                Problem problem = arrayListProblem.get(position);
                PicMyMedController.addRecord(problem, record);
                //saveInFile();
                onBackPressed();//go back to previous activity
            }
        });

    }

    /**
     * Method starts add record activity
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
        arrayListProblem = user.getProblemList();
        //loadFromFile();
    }
}
