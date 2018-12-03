/*
 * MapButtonActivity
 *
 * 1.2
 *
 * Copyright (C) 2018 CMPUT301F18T14. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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

/**
 * MapButtonActivity extends AppCompatActivity to handle user selecting Map Button
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class MapButtonActivity extends AppCompatActivity {

    private static final String TAG = "MapButtonActivity: ";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    /**
     * Sets the state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_button);

        if (isServicesOK()) {
            init();
        }
    }

    /**
     * Initializing the Map Drawing button
     */
    private void init(){
        Button buttonMap = (Button) findViewById(R.id.map_button);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles user clicking on Map button
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapButtonActivity.this, DrawMapActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * Checking the version of google play services
     *
     * @return  Boolean
     */
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
