package com.example.picmymedmaphandler.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.picmymedcode.R;
import com.example.picmymedmaphandler.View.DrawMapActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MapButtonActivity extends AppCompatActivity {

    private static final String TAG = "MapButtonActivity: ";

    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_button);

        if (isServicesOK()) {
            init();
        }
    }

    // Initializing the Map Drawing button
    private void init(){
        Button buttonMap = (Button) findViewById(R.id.map_button);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapButtonActivity.this, DrawMapActivity.class);
                startActivity(intent);
            }
        });
    }


    // Checking the version of google play services
    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapButtonActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            // Everything is fine; User can make map request
            Log.d(TAG, "isServicesOK: GooglePlayServices is working.");
            return true;
        }

        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // An error occurred but can be resolved
            Log.d(TAG, "isServicesOK: an error occurred but can be fixed.");

            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapButtonActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }

        else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


}
