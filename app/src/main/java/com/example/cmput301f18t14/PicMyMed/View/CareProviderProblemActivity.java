package com.example.cmput301f18t14.PicMyMed.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.cmput301f18t14.PicMyMed.Controller.PicMyMedController;
import com.example.cmput301f18t14.PicMyMed.Model.Patient;
import com.example.cmput301f18t14.PicMyMed.R;

public class CareProviderProblemActivity extends Activity{
    String name;//pass intent name
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproviderpatient_activity);
        //get patient name from intent
        name = getIntent().getStringExtra("name");
        //patient object
        final Patient patient = PicMyMedController.getPatient(name);

        //set name text view
        TextView patientName = findViewById(R.id.careproviderpation_name_text_view);
        patientName.setText(name);

        //set phone number text view
        TextView patientPhone = findViewById(R.id.careproviderpation_phone_text_view);
        patientPhone.setText(patient.getPhoneNumber());
        //wow factor pass intent to call
        patientPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                // Send phone number to intent as data
                intent.setData(Uri.parse("tel:" + patient.getPhoneNumber()));
                // Start the dialer app activity with number
                startActivity(intent);
            }
        });

        //set phone number text view
        TextView patientEmail = findViewById(R.id.careproviderpation_email_text_view);
        patientEmail.setText(patient.getEmail());
        patientEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822"); //specifies message for email app.
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "email content"); //adds the actual content of the email by calling the method previously defined
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "email subject"); //adds the subject by calling the method previously defined.
                startActivity(Intent.createChooser(emailIntent, "Title of the dialog chooser"));
            }
        });

    }
}
