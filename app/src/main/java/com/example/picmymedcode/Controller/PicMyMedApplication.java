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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.User;
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

    /**
     * Method will logout the user
     */
    public static void logoutUser() {
        loggedInUser = null;
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
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkStatus = connManager.getActiveNetworkInfo();
        return networkStatus != null && networkStatus.isConnected();
    }

    /**
     * Method loads saved data from file, if it exists
     * Used prior to implementation of elastic search
     */
    private void loadLocalUser() {
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
    }
}
