/*
 * PicMyMedController
 *
 * 1.1
 *
 * November 16, 2018
 *
 * Copyright 2018 CMPUT301F18T14. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mukha.picmymedcode.Controller;


import android.util.Log;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.Model.Record;
import com.example.mukha.picmymedcode.Model.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * PicMyMedController creates a user
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class PicMyMedController {


    /**
     * Method creates a new user
     *
     * @param user  user
     * @return      int
     */
    public static int createUser(User user) {
        ArrayList<Patient> patients = null;
        ArrayList<CareProvider> careProviders = null;

        ElasticSearchController.GetPatient getPatient = new ElasticSearchController.GetPatient();
        getPatient.execute(user.getUsername());

        ElasticSearchController.GetCareProvider getCareProvider = new ElasticSearchController.GetCareProvider();
        getCareProvider.execute(user.getUsername());

        try {
            patients = getPatient.get();

        } catch (Exception e) {
            Log.i("DEBUG PMMController", "No patients with the entered username was found");
        }
        try {
            careProviders = getCareProvider.get();

        } catch (Exception e) {
            Log.i("DEBUG PMMController", "No careproviders with the entered username was found");
        }

        if (patients.size() == 0 && careProviders.size() == 0) {
            if(user.isPatient()) {
                Patient patient = (Patient) user;
                ElasticSearchController.AddPatient addPatient = new ElasticSearchController.AddPatient();
                addPatient.execute(patient);
            } else {
                CareProvider careProvider = (CareProvider) user;
                ElasticSearchController.AddCareProvider addCareProvider = new ElasticSearchController.AddCareProvider();
                addCareProvider.execute(careProvider);

            }
            return 1;
        }
        return 0;

       /* if (user.isPatient()) {

            ElasticSearchController.GetPatient getPatient = new ElasticSearchController.GetPatient();
            getPatient.execute(user.getUsername());

            ArrayList<Patient> patients = null;

            try {
                patients = getPatient.get();
            } catch (Exception e) {
                Log.i("DEBUG PMMController", "No patients with the entered username was found");

            }

            if (patients.size() == 0) {
                Patient patient = (Patient) user;
                ElasticSearchController.AddPatient addPatient = new ElasticSearchController.AddPatient();
                addPatient.execute(patient);
            } else {
                return 0;
            }


        } else {

            ElasticSearchController.GetCareProvider getCareProvider = new ElasticSearchController.GetCareProvider();
            getCareProvider.execute(user.getUsername());

            ArrayList<CareProvider> careProviders = null;

            try {
                careProviders = getCareProvider.get();
            } catch (Exception e) {
                Log.i("DEBUG PMMController", "No careproviders with the entered username was found");

            }

            if (careProviders.size() == 0) {
                CareProvider careProvider = (CareProvider) user;
                ElasticSearchController.AddCareProvider addCareProvider = new ElasticSearchController.AddCareProvider();
                addCareProvider.execute(careProvider);
            } else {

                return 0;
            }


        }*/

    }

    public static ArrayList<Problem> getProblems() {
        /* Needs to be fixed a bit because an empty ArrayList shouldn't be sent if user is null */
        ArrayList<Problem> problemList = new ArrayList<Problem>();
        Patient patient = PicMyMedApplication.getPatientUser();

        if (patient != null) {
            return patient.getProblemList();
        } else {
            return problemList;

        }

    }

    public static int addProblem(Problem problem) {

        Patient patient = PicMyMedApplication.getPatientUser();
        patient.getProblemList().add(problem);

        updatePatient(patient);


        // push new problem to elastic search
        // add problem to user
        // update user elastic search
        return 1;
    }

    public static int editProblem(Problem problem, Date date, String title, String description) {
        Patient patient = PicMyMedApplication.getPatientUser();
        problem.setStartDate(date);
        problem.setTitle(title);
        problem.setDescription(description);
        updatePatient(patient);
        // push editted problem to elastic search
        // update user problem list
        // update user elastic search
        return 1;
    }

    public static int deleteProblem(Problem problem) {
        Patient patient = PicMyMedApplication.getPatientUser();
        patient.getProblemList().remove(problem);
        updatePatient(patient);
        return 1;
    }

    public static int addRecord(Problem problem, Record record) {

        Patient patient = PicMyMedApplication.getPatientUser();
        problem.addRecord(record);

        updatePatient(patient);


        // push new problem to elastic search
        // add problem to user
        // update user elastic search
        return 1;
    }

    public static int updatePatient(Patient patient) {
        // get user from controller
        // call elastic search and update
        try {
            ElasticSearchController.UpdatePatient updatePatient = new ElasticSearchController.UpdatePatient();
            updatePatient.execute(patient);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int checkLogin(String username) {

        Patient patient = null;
        CareProvider careProvider = null;
        ElasticSearchController.GetPatient getPatient = new ElasticSearchController.GetPatient();
        getPatient.execute(username);

        ElasticSearchController.GetCareProvider getCareProvider = new ElasticSearchController.GetCareProvider();
        getCareProvider.execute(username);

        try {
            patient = getPatient.get().get(0);
            PicMyMedApplication.setLoggedInUser(patient);
        } catch (Exception e) {
            Log.i("DEBUG PMMController", "No patients with the entered username was found");
        }

        try {
             careProvider = getCareProvider.get().get(0);
            PicMyMedApplication.setLoggedInUser(careProvider);
        } catch (Exception e) {
            Log.i("DEBUG PMMController", "No careproviders with the entered username was found");
        }

        if (patient == null && careProvider == null) {
            return 0;
        }
        else  {
            return 1;
        }

    }
}