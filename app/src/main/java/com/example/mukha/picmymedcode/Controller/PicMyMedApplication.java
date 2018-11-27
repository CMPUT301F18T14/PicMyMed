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
package com.example.mukha.picmymedcode.Controller;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.User;

/**
 * PicMyMedApplication handles the logged in user and their type (patient or care provider)
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class PicMyMedApplication {

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
}
