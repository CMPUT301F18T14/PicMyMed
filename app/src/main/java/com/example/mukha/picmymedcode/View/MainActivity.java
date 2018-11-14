package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mukha.picmymedcode.Model.Login;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText enteredUsername = (EditText) findViewById(R.id.enteredUID);
                String username = enteredUsername.getText().toString();
                Login login = new Login();
                if (login.checkUsername(username)) {
                    Intent problemIntent = new Intent(MainActivity.this, ProblemActivity.class);
                    startActivity(problemIntent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid username",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        Button signupBtn = (Button) findViewById(R.id.signUpButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent problemIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(problemIntent);
            }
        });
    }




}
