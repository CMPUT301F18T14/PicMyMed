package com.example.picmymedcode.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.R;

import java.util.ArrayList;

public class CareProviderRecordActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    static int problemPosition;
    Patient patient;
    SwipeRefreshLayout swipeLayout;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproviderrecord_activity);
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        final User user = PicMyMedApplication.getLoggedInUser();// log in user

        //get patient name from intent
        String name = getIntent().getStringExtra("name");//pass intent name
        //patient object

        //set name text view
        TextView patientName = findViewById(R.id.careprovider_record_name_text_view);
        patientName.setText(CareProviderProblemActivity.name);

        //set phone number text view
        TextView patientPhone = findViewById(R.id.careprovider_record_phone_text_view);
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
        TextView patientEmail = findViewById(R.id.careprovider_record_email_text_view);
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




        ImageView addRecordImageView = (ImageView) findViewById(R.id.add_comment_image_view);
        addRecordImageView.bringToFront();
        addRecordImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent problemIntent = new Intent(CareProviderRecordActivity.this,CareProviderAddComment.class);
                problemIntent.putExtra("key2",problemPosition);
                startActivity(problemIntent);

            }
        });

        Button careproviderViewCommentImageView = (Button) findViewById(R.id.careprovider_view_comment_image_view);
        careproviderViewCommentImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent problemIntent = new Intent(CareProviderRecordActivity.this,CareProviderCommentActivity.class);
                startActivity(problemIntent);

            }
        });

        //swipe to refresh
        swipeLayout = findViewById(R.id.careprovider_record_swipeRefresh);
        // Adding Listener
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (PicMyMedApplication.isNetworkAvailable(CareProviderRecordActivity.this)) {
                    // To keep animation for 4 seconds
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            PicMyMedApplication.getMostRecentChanges();
                            manageRecyclerview();
                            // Stop animation (This will be after 3 seconds)
                            swipeLayout.setRefreshing(false);
                            Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_LONG).show();
                        }
                    }, 5000); // Delay in millis

                }else {
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            // Stop animation (This will be after 3 seconds)
                            swipeLayout.setRefreshing(false);
                            Toast.makeText(getApplicationContext(), "No internet Connection!", Toast.LENGTH_LONG).show();
                        }
                    }, 500); // Delay in millis
                }

            }
        });

        manageRecyclerview();


    }

    public void manageRecyclerview(){
        problemPosition = getIntent().getIntExtra("key",0);
        patient = PicMyMedController.getPatient(CareProviderProblemActivity.name);
        problemArrayList = patient.getProblemList();
        problemArrayList.get(problemPosition).getRecordList();
        mRecyclerView = findViewById(R.id.careprovider_record_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new RecordAdapter(CareProviderRecordActivity.this, problemArrayList.get(problemPosition).getRecordList());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method starts the RecordActivity by getting the user and their problems
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        problemPosition = getIntent().getIntExtra("key",0);
        super.onStart();
        problemArrayList = patient.getProblemList();
        problemArrayList.get(problemPosition).getRecordList();
        //loadFromFile();
        mAdapter = new RecordAdapter(CareProviderRecordActivity.this, problemArrayList.get(problemPosition).getRecordList());
        mRecyclerView.setAdapter(mAdapter);

    }


}
