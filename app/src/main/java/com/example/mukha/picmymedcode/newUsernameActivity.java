package com.example.mukha.picmymedcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mukha.picmymedcode.ProblemFile.ProblemActivity;

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
                if (userType.equals("patient")) {
                    Patient user = new Patient(username, "", "");
                }
                else if (userType.equals("careProvider")) {
                    CareProvider user = new CareProvider(username);
                }
                Intent problemIntent = new Intent(newUsernameActivity.this, ProblemActivity.class);
                startActivity(problemIntent);

            }
        });

    }
}
