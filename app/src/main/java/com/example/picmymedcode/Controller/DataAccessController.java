/*
 * DataAccessController
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

import android.os.AsyncTask;
import android.util.Log;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * DataAccessController handles user input, controls background activity
 * and server connectivity
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class DataAccessController {

    private static JestDroidClient client;

    private static final String serverURI = "http://cmput301.softwareprocess.es:8080/";
    private static final String indexPath = "cmput301f18t14test";
    private static final String querySize = "100";
    private static final String maxQuerySize = "999999";
    private static final String patientType = "patient";
    private static final String careProviderType = "careprovider";
    private static final String problemType = "problem";


    /**
     * Method extends AsyncTask to add a patient
     */
    public static class AddPatient extends AsyncTask<Patient, Void, Void> {
        /**
         * Method adds the patient information to the local device
         *
         * @param patients  Patient
         * @return          null
         */
        @Override
        protected Void doInBackground(Patient... patients) {
            Log.i("DEBUG AddPatient:", "Attempting to build patient index...");
            verifySettings();
            Patient patient = patients[0];

            Index index = new Index.Builder(patient).index(indexPath).type(patientType).build();
            Log.i("DEBUG AddPatient:", "Created index...");

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    if (patient.getElasticSearchID() == null) {
                        patient.setElasticSearchID(result.getId());
                        Log.i("DEBUG AddPatient", "Patient ID " + result.getId() + "generated.");
                        Log.i("DEBUG AddPatient", "Updating index of patient ...");
                        try {
                            DataAccessController.UpdatePatient updatePatient = new DataAccessController.UpdatePatient();
                            updatePatient.execute(patient);
                        } catch (Exception e) {
                            Log.i("DEBUG AddPatient", "Elasticsearch unable to update newly generated ID of patient");
                        }
                    } else {
                        Log.i("DEBUG AddPatient", "Failed to generate Patient ID.");
                    }
                    Log.i("DEBUG AddPatient", "Elasticsearch successfully added the Patient");
                }
                else {
                    Log.i("DEBUG AddPatient", "Elasticsearch was not able to add the Patient");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG AddPatient", "The application failed to build and send the patient");
            }


            return null;
        }
    }

    /**
     * Method extends AsyncTask to add a care provider
     */
    public static class AddCareProvider extends AsyncTask<CareProvider, Void, Void> {

        /**
         * Method adds care provider data to local device
         *
         * @param careProviders CareProvider
         * @return              null
         */
        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            Log.i("DEBUG AddCareProvider:", "Attempting to build careprovider index...");
            verifySettings();

            CareProvider careProvider = careProviders[0];
            Index index = new Index.Builder(careProvider).index(indexPath).type(careProviderType).build();
            Log.i("DEBUG AddCareProvider:", "Created index...");

            try {
                // where is the client?
                DocumentResult result = client.execute(index);

                if (result.isSucceeded()) {
                    if (careProvider.getElasticSearchID() == null) {
                        careProvider.setElasticSearchID(result.getId());
                        Log.i("DEBUG AddCareProvider", "Careprovider ID " + result.getId() + "generated.");
                        Log.i("DEBUG AddCareProvider", "Updating index of careprovider ...");
                        try {
                            DataAccessController.UpdateCareProvider updateCareProvider = new DataAccessController.UpdateCareProvider();
                            updateCareProvider.execute(careProvider);
                        } catch (Exception e) {
                            Log.i("DEBUG AddCareProvider", "Elasticsearch unable to update newly generated ID of careprovider");
                        }
                    } else {
                        Log.i("DEBUG AddCareProvider", "Failed to generate Careprovider ID.");
                    }
                    Log.i("DEBUG AddCareProvider", "Elasticsearch successfully added the Careprovider");
                }
                else {
                    Log.i("DEBUG AddCareProvider", "Elasticsearch was not able to add the Careprovider");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG AddCareProvider", "The application failed to build and add the Careprovider");
            }


            return null;
        }
    }

    /**
     * Method extends AsyncTask to get a patient using elastic search
     */
    public static class GetPatient extends AsyncTask<String, Void, ArrayList<Patient>> {
        @Override
        protected ArrayList<Patient> doInBackground(String... search_parameters) {
            Log.i("DEBUG GetPatient:", "Attempting to build patient query...");
            verifySettings();

            ArrayList<Patient> patients = new ArrayList<Patient>();

            String patientName = search_parameters[0];

            String patientQuery = "{ \"size\": " + querySize +
                    ", \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"username\" : \"" + patientName + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(patientQuery)
                    .addIndex(indexPath)
                    .addType(patientType)
                    .build();
            Log.i("DEBUG GetPatient:", "Created query...");
            try {

                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    Log.i("DEBUG GetPatient", "Successfully queried elasticsearch server");
                    List<Patient> foundPatients = result.getSourceAsObjectList(Patient.class);
                    patients.addAll(foundPatients);
                }
                else {
                    Log.i("DEBUG GetPatient", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG GetPatient", "Something went wrong when we tried to communicate with the elasticsearch server!");

            }

            return patients;
        }
    }

    /**
     * Method extends Asynctask to get a care provider using elastic search
     */
    public static class GetCareProvider extends AsyncTask<String, Void, ArrayList<CareProvider>> {
        @Override
        protected ArrayList<CareProvider> doInBackground(String... search_parameters) {
            Log.i("DEBUG GetCareProvider", "Attempting to build careprovider query...");
            verifySettings();

            ArrayList<CareProvider> careProviders = new ArrayList<CareProvider>();
            String careProviderName = search_parameters[0];


            String careProviderQuery = "{ \"size\": " + querySize +
                    ", \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"username\" : \"" + careProviderName + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(careProviderQuery)
                    .addIndex(indexPath)
                    .addType(careProviderType)
                    .build();

            Log.i("DEBUG GetCareProvider:", "Created query...");

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    Log.i("DEBUG GetCareProvider", "Successfully queried elasticsearch server");
                    List<CareProvider> foundCareProviders = result.getSourceAsObjectList(CareProvider.class);
                    careProviders.addAll(foundCareProviders);
                }
                else {
                    Log.i("DEBUG GetCareProvider", "The search query failed to find any careproviders that matched");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG GetCareProvider", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return careProviders;
        }
    }

    /**
     * Method extends AsyncTask to get a patient using elastic search
     */
    public static class GetUsernameByID extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... search_parameters) {
            Log.i("DEBUG GetUsernameByID:", "Attempting to build query...");
            verifySettings();

            String username = new String();

            String randomUserID = search_parameters[0];

            String patientQuery = "{ \"size\": " + querySize +
                    ", \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"randomUserID\" : \"" + randomUserID + "\" }\n" +
                    "    }\n" +
                    "}" ;
            Log.d("QUERY", patientQuery);
            Search search = new Search.Builder(patientQuery)
                    .addIndex(indexPath)
                    .addType(patientType)
                    .build();
            Log.i("DEBUG GetPatient:", "Created query...");
            try {

                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    Log.i("DEBUG GetPatient", "Successfully queried elasticsearch server");
                    List<Patient> foundPatients = result.getSourceAsObjectList(Patient.class);
                    username = foundPatients.get(0).getUsername();

                }
                else {
                    Log.i("DEBUG GetPatient", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG GetPatient", "Something went wrong when we tried to communicate with the elasticsearch server!");

            }

            return username;
        }
    }


    /**
     * Method extends AsyncTask to update patient data using elastic search
     */
    public static class UpdatePatient extends AsyncTask<Patient, Void, Void> {

        @Override
        protected Void doInBackground(Patient... patients) {
            Log.i("DEBUG UpdatePatient:", "Attempting to build patient index...");
            verifySettings();
            Patient patient = patients[0];

            Index index = new Index.Builder(patient).index(indexPath).type(patientType).id(patient.getElasticSearchID()).build();
            Log.i("DEBUG UpdatePatient:", "Created index...");

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    Log.i("DEBUG UpdatePatient", "Patient ID (" +patient.getUsername()+") " + patient.getElasticSearchID() + " updated.");
                }
                else {
                    Log.i("DEBUG UpdatePatient", "Elasticsearch was not able to update the patient");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG UpdatePatient", "The application failed to update the patient");
            }


            return null;
        }
    }


    /**
     * Method extends AsyncTask to update care provider using elastic search
     */
    public static class UpdateCareProvider extends AsyncTask<CareProvider, Void, Void> {

        /**
         * Method runs in the background for care Provider
         *
         * @param careProviders careProvider
         * @return              null
         */
        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            Log.i("DEBUG UpdateCP:", "Attempting to build patient index...");
            verifySettings();
            CareProvider careProvider = careProviders[0];

            Index index = new Index.Builder(careProvider).index(indexPath).type(careProviderType).id(careProvider.getElasticSearchID()).build();
            Log.i("DEBUG UpdateCP:", "Created index...");

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    Log.i("DEBUG UpdateCP", "CareProvider ID (" +careProvider.getUsername()+") " + careProvider.getElasticSearchID() + " updated.");
                }
                else {
                    Log.i("DEBUG UpdateCP", "Elasticsearch was not able to update the careprovider");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG UpdateCP", "The application failed to update the careprovider");
            }

            return null;
        }
    }

    /**
     * Method adds problem using elastic search
     */
     public static class AddProblem extends AsyncTask<Problem, Void, Void> {

        /**
         * Method handles background activity
         *
         * @param problems  Problem
         * @return          null
         */
         @Override
         protected Void doInBackground(Problem... problems) {
             verifySettings();
             Problem problem = problems[0];

             Index index = new Index.Builder(problem).index(indexPath).type(problemType).build();

             try {
                 // where is the client?
                 DocumentResult result = client.execute(index);
                 if (result.isSucceeded()) {

                     if (problem.getProblemID() == null) {
                         problem.setProblemID(result.getId());
                         Log.i("DEBUG AddProblem", "Problem ID " + result.getId() + "generated.");
                         Log.i("DEBUG AddProblem", "Updating index of problem ...");
                         try {
                             DataAccessController.UpdateProblem updateProblem = new DataAccessController.UpdateProblem();
                             updateProblem.execute(problem);
                         } catch (Exception e) {
                             Log.i("DEBUG AddProblem", "Elasticsearch unable to update newly generated ID of problem");
                         }
                         Log.i("DEBUG AddProblem", "Elasticsearch successfully added the problem and updated newly generated index");
                     } else {
                         Log.i("DEBUG AddProblem", "Problem ID already exists!");
                     }
                 }
                 else {
                     Log.i("DEBUG AddProblem", "Elasticsearch was not able to add the problem");
                 }
             }
             catch (Exception e) {
                 Log.i("DEBUG AddProblem", "The application failed to build and add the problem");
             }

             return null;
         }
     }

    /**
     * Method updates problem in elastic search
     */
    public static class UpdateProblem extends AsyncTask<Problem, Void, Void> {

        @Override
        protected Void doInBackground(Problem... problems) {
            Log.i("DEBUG UpdateProblem:", "Attempting to build problem index...");
            verifySettings();
            Problem problem = problems[0];

            Index index = new Index.Builder(problem).index(indexPath).type(problemType).id(problem.getProblemID()).build();
            Log.i("DEBUG UpdateProblem:", "Created index...");

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    Log.i("DEBUG UpdateProblem", "Problem ID (" + problem.getTitle()+") for user "+ problem.getUsername() + " with id " + problem.getProblemID() + " updated.");
                }
                else {
                    Log.i("DEBUG UpdateProblem", "Elasticsearch was not able to update the problem");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG UpdateProblem", "The application failed to update the problem");
            }


            return null;
        }
    }

    /**
     * Method extends AsyncTask and gets all patients
     */
    public static class GetAllPatients extends AsyncTask<Void, Void, ArrayList<Patient>> {
        @Override
        protected ArrayList<Patient> doInBackground(Void... voids) {
            verifySettings();

            ArrayList<Patient> patients = new ArrayList<>();
            String patientsQuery =
                    "{" +
                            "\"size\": " + querySize + "," +
                            "\"query\": {" +
                            "\"match_all\" : {}" +
                            "}" +
                            "}";



            Search search = new Search.Builder(patientsQuery)
                    .addIndex(indexPath)
                    .addType("patient")
                    .build();

            try {
                // Send request to the server to get the user
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){

                    //Log.i("DEBUG DeBug", "Succeeded in finding a user");

                    List<Patient> foundPatients = result.getSourceAsObjectList(Patient.class);
                    patients.addAll(foundPatients);
                }
                else{
                    Log.i("DEBUG Error", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return patients;
        }
    }

    /**
     * Method extends AsyncTask to get all care providers
     */
    public static class GetAllCareProviders extends AsyncTask<Void, Void, ArrayList<CareProvider>> {
        @Override
        protected ArrayList<CareProvider> doInBackground(Void... voids) {
            verifySettings();

            ArrayList<CareProvider> careProviders = new ArrayList<>();
            String careProviderQuery =
                    "{" +
                            "\"size\": " + querySize + "," +
                            "\"query\": {" +
                            "\"match_all\" : {}" +
                            "}" +
                            "}";



            Search search = new Search.Builder(careProviderQuery)
                    .addIndex(indexPath)
                    .addType("careprovider")
                    .build();

            try {
                // Send request to the server to get the user
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){

                    //Log.i("DEBUG DeBug", "Succeeded in finding a user");

                    List<CareProvider> foundCareProviders = result.getSourceAsObjectList(CareProvider.class);
                    careProviders.addAll(foundCareProviders);
                }
                else{
                    Log.i("DEBUG Error", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("DEBUG Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return careProviders;
        }
    }

    /**
     * Method verifies server settings are correct 
     */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder(serverURI).defaultCredentials("elastic", "QGHEMdbRuuW3jA6mLggnLtWU");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}