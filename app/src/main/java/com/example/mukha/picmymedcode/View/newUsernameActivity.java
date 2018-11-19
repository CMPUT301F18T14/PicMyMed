package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mukha.picmymedcode.Controller.PicMyMedController;
import com.example.mukha.picmymedcode.Model.User;
import com.example.mukha.picmymedcode.R;
import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;

public class newUsernameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newusername_activity);
        Intent newuserIntent = getIntent();
        final String userType = newuserIntent.getStringExtra("userType");   // get user type

        setContentView(R.layout.newusername_activity);
        TextView textView = (TextView) findViewById(R.id.textView2);    // for testing
        textView.setText(userType);

        Button signUpBtn = (Button) findViewById(R.id.signUpButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText enteredUsername = (EditText) findViewById(R.id.enteredUID);
                String username = enteredUsername.getText().toString();


                // This was previously done to pass user to problem activity, but we decided to redirect to login page
                /*Intent problemIntent = new Intent(newUsernameActivity.this, ProblemActivity.class);
                startActivity(problemIntent);*/
                User user = null;
                try {
                    if (userType.equals("patient")) {
                        user = new Patient(username, "", "");
                    } else if (userType.equals("careprovider")) {
                        user = new CareProvider(username);
                    }
                } catch (Exception e) {
                    toastMessage(e.getMessage());
                }
                if (user != null && PicMyMedController.createUser(user) != 1) {
                    toastMessage("Error: Username already exists, please try another one.");
                } else {
                    toastMessage("Account successfully created. Please login.");
                    finish();


                }

            }
        });

    }
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}