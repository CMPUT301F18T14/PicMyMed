package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.R;

public class ProfileActivity extends AppCompatActivity {
    Patient user = (Patient)PicMyMedApplication.getLoggedInUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);


        TextView showUsername = (TextView)findViewById(R.id.username);
        showUsername.setText(user.getUsername());

        TextView showPhoneNumber = (TextView)findViewById(R.id.phoneNumber);
        showPhoneNumber.setText(user.getPhoneNumber());

        TextView showEmail = (TextView)findViewById(R.id.email);
        showEmail.setText(user.getEmail());

        Button editProfileButton = findViewById(R.id.editProfile_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent problemIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(problemIntent);
            }
        });
    }
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        TextView showUsername = (TextView)findViewById(R.id.username);
        showUsername.setText(user.getUsername());

        TextView showPhoneNumber = (TextView)findViewById(R.id.phoneNumber);
        showPhoneNumber.setText(user.getPhoneNumber());

        TextView showEmail = (TextView)findViewById(R.id.email);
        showEmail.setText(user.getEmail());


    }
}
