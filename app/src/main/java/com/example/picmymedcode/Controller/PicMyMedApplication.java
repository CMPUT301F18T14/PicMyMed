/*
 * PicMyMedApplication
 *
 * 1.1
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
package com.example.picmymedcode.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.User;
import com.example.picmymedcode.View.MainActivity;
import com.example.picmymedcode.View.PatientActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.getSystemService;

/**
 * PicMyMedApplication handles the logged in user and their type (patient or care provider)
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class PicMyMedApplication {
    public final static String FILENAME = "PicMyMedData.sav";

    static User loggedInUser;

    /**
     * Method sets logged in user
     *
     * @param user  User
     */
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    /**
     * Method gets the user logged in
     *
     * @return  User
     */
    public static User getLoggedInUser() {

        return loggedInUser;
       /* if (loggedInUser.isPatient()){
            getPatientUser();
        } else {
            getCareProviderUser();
        }
        */
    }


    public static Patient getPatientUser() {
        Patient patient = (Patient) loggedInUser;
        return patient;
    }

    /**
     * Method returns the care provider type
     *
     * @return careProvider
     */
    public static CareProvider getCareProviderUser() {
        CareProvider careProvider = (CareProvider) loggedInUser;
        return careProvider;
    }

    /**
     * Method gets the user's username
     *
     * @return  String
     */
    public static String getUsername() {
        return getLoggedInUser().getUsername();
    }

    /**
     * Checks for internet connectivity
     * Obtained from https://stackoverflow.com/questions/5474089/how-to-check-currently-internet-connection-is-available-or-not-in-android
     * author: Sandeep Reddy M
     * @return
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkStatus = connManager.getActiveNetworkInfo();
        return networkStatus != null && networkStatus.isConnected();
    }

    public static void logout(final Context context) {
        try {
            User user = getLoggedInUser();
            if (isNetworkAvailable(context)) {
                Log.i("DEBUG PMA","Saving user to online database");
                PicMyMedController.updateUser(user);
            } else {
                user.setRequiresSync(Boolean.TRUE);
                Log.i("DEBUG PMA","Saving user locally");
                saveUserLocally();
            }
            setLoggedInUser(null);
            Intent problemIntent = new Intent(context, MainActivity.class);
            context.startActivity(problemIntent);
            Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("DEBUG PMA", "Error logging out");
        }
    }

    public static void logoutDialog(final Context context) {
        AlertDialog.Builder authorizationDialog = new AlertDialog.Builder(context);
        authorizationDialog.setTitle("Logout")
                .setCancelable(false)
                .setMessage("Is recording problems causing even more problems?! Meta ... we know.\n Would you like to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PicMyMedApplication.logout(context);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Keep enjoying recording your problems with this painful app!", Toast.LENGTH_SHORT).show();
                    }
                });
        authorizationDialog.show();
    }

    private static void saveUserLocally() {}

    /**
     * Method loads saved data from file, if it exists
     * Used prior to implementation of elastic search
     */
   /* private void loadLocalUser() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListProblem = new TypeToken<ArrayList<User>>() {}.getType();
            problemArrayList = gson.fromJson(reader, typeListProblem);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
}
