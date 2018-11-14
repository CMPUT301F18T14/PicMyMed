package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        final Intent newUsernameIntent = new Intent(SignUpActivity.this, newUsernameActivity.class);
        Button patientBtn = (Button) findViewById(R.id.patientButton);
        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUsernameIntent.putExtra("userType", "patient");
                startActivity(newUsernameIntent);

            }
        });

        Button careProviderBtn = (Button) findViewById(R.id.careProviderButton);
        careProviderBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newUsernameIntent.putExtra("userType", "care provider");
                startActivity(newUsernameIntent);
            }
        });
    }
}