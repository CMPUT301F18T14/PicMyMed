package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mukha.picmymedcode.Controller.ElasticSearchController;
import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.R;

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
                    try {
                        Patient user = new Patient(username, "", "");

                        ElasticSearchController.AddPatientTask addPatientTask= new ElasticSearchController.AddPatientTask();
                        addPatientTask.execute(user);

                    } catch (IllegalArgumentException e) {

                        e.printStackTrace();
                    }
                }
                else if (userType.equals("careProvider")) {
                    try {
                        CareProvider user = new CareProvider(username);
                        ElasticSearchController.AddCareProviderTask addCareProviderTask= new ElasticSearchController.AddCareProviderTask();
                        addCareProviderTask.execute(user);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
                Intent problemIntent = new Intent(newUsernameActivity.this, ProblemActivity.class);
                startActivity(problemIntent);

            }
        });

    }
}
