/*
 * PicMyMedApplication
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
package com.example.picmymedcode.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
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
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static android.support.v4.content.ContextCompat.getSystemService;

/**
 * PicMyMedApplication handles the logged in user and their type (patient or care provider)
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class PicMyMedApplication {
    public final static String FILENAME = "PicMyMed.sav";
    public static String Preference = "username";

    static User loggedInUser;

    static User localUser;

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

    /**
     * Method gets the user
     *
     * @return localUser
     */
    public static User getLocalUser() {
        return localUser;
    }

    /**
     * Method sets local user
     *
     * @param localUser User
     */
    public static void setLocalUser(User localUser) {
        PicMyMedApplication.localUser = localUser;
    }

    /**
     * Method gets the user that is a patient
     *
     * @return  patient
     */
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
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkStatus = connManager.getActiveNetworkInfo();
        return networkStatus != null && networkStatus.isConnected();
    }

    /**
     * Method logs out the user
     *
     * @param context   Context
     */
    public static void logout(final Context context) {
        try {
            User user = getLoggedInUser();
            PicMyMedController.updateUser(user, context);
            /*if (isNetworkAvailable(context)) {
                Log.i("DEBUG PMA","Saving user to online database");
                PicMyMedController.updateUser(user, context);
            } else {
                user.setRequiresSync(Boolean.TRUE);
                Log.i("DEBUG PMA","Update Required during next login.");

            }
            saveUserLocally(context);
            */
            SharedPreferences.Editor editor = context.getSharedPreferences(Preference,MODE_PRIVATE).edit();
            editor.remove("username");
            editor.apply();
            setLoggedInUser(null);
            Intent problemIntent = new Intent(context, MainActivity.class);
            context.startActivity(problemIntent);
            Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("DEBUG PMA", "Error logging out");
        }
    }

    /**
     * Method displays dialog box to confirm user wants to log out
     *
     * @param context   Context
     */
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




    /**
     * Method saves the user to local
     *
     * @param context   Context
     */

    public static void saveUserLocally(Context context) {
        try {
            ArrayList<User> userList = new ArrayList<>();
            FileOutputStream fos = context.openFileOutput(FILENAME, MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            userList.add(loggedInUser);
            gson.toJson(userList, out);
            out.flush();
            fos.close();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    /**
     * Method loads the user data
     *
     * @param ctx   Context
     * @return      false
     */
    public static boolean loadUserData(Context ctx) {
        try {
            ArrayList<User> userList;
            FileInputStream fis = ctx.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Patient>>(){}.getType();
            userList = gson.fromJson(in, listType);
            setLocalUser(userList.get(0));

            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return false;
        } catch (Exception e) {
            Log.i("DEBUG local", e.getMessage());
        }
        return false;
    }

    /**
     * Method gets the most recent changes
     */
    public static void getMostRecentChanges() {
        setLoggedInUser(PicMyMedController.getUser(loggedInUser.getUsername()));
    }
}
