/*
 * ElasticSearch Controller
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

import android.os.AsyncTask;
import android.util.Log;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.Record;
import com.example.mukha.picmymedcode.Model.Problem;
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
 * ElasticSearchController handles user input, controls background activity
 * and server connectivity
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class ElasticSearchController {

    private static JestDroidClient client;

    private static final String serverURI = "http://cmput301.softwareprocess.es:8080/";
    private static final String indexPath = "cmput301f18t14test";
    private static final String querySize = "10";
    private static final String patientType = "patient";
    private static final String careProviderType = "careprovider";

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
                    if (patient.getUserID() == null) {
                        patient.setUserID(result.getId());
                        Log.i("DEBUG AddPatient", "Patient ID " + result.getId() + "generated.");
                        Log.i("DEBUG AddPatient", "Updating index of patient ...");
                        try {
                            ElasticSearchController.UpdatePatient updatePatient = new ElasticSearchController.UpdatePatient();
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
                    if (careProvider.getUserID() == null) {
                        careProvider.setUserID(result.getId());
                        Log.i("DEBUG AddCareProvider", "Careprovider ID " + result.getId() + "generated.");
                        Log.i("DEBUG AddCareProvider", "Updating index of careprovider ...");
                        try {
                            ElasticSearchController.UpdateCareProvider updateCareProvider = new ElasticSearchController.UpdateCareProvider();
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
     * Method extends AsyncTask to update patient data using elastic search
     */
    public static class UpdatePatient extends AsyncTask<Patient, Void, Void> {

        @Override
        protected Void doInBackground(Patient... patients) {
            Log.i("DEBUG UpdatePatient:", "Attempting to build patient index...");
            verifySettings();
            Patient patient = patients[0];

            Index index = new Index.Builder(patient).index(indexPath).type(patientType).id(patient.getUserID()).build();
            Log.i("DEBUG UpdatePatient:", "Created index...");

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    Log.i("DEBUG UpdatePatient", "Patient ID (" +patient.getUsername()+") " + patient.getUserID() + " updated.");
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

        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            Log.i("DEBUG UpdateCP:", "Attempting to build patient index...");
            verifySettings();
            CareProvider careProvider = careProviders[0];

            Index index = new Index.Builder(careProvider).index(indexPath).type(careProviderType).id(careProvider.getUserID()).build();
            Log.i("DEBUG UpdateCP:", "Created index...");

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    Log.i("DEBUG UpdateCP", "CareProvider ID (" +careProvider.getUsername()+") " + careProvider.getUserID() + " updated.");
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

    /* public static class AddProblemsTask extends AsyncTask<Problem, Void, Void> {

         @Override
         protected Void doInBackground(Problem... problems) {
             verifySettings();

             for (Problem problem : problems) {

                 Index index = new Index.Builder(problem).index(indexPath).type("problem").build();

                 if (problem.getId() != null) {
                     index = new Index.Builder(problem).index(indexPath).type("problem").id(problem.getId()).build();
                 }

                 try {
                     // where is the client?
                     DocumentResult result = client.execute(index);
                     if (result.isSucceeded()) {
                         if (problem.getId() == null) {
                             problem.setId(result.getId());
                             Log.i("DEBUG Insert", "Elasticsearch performed a problem insert");
                         } else {
                             Log.i("DEBUG Update", "Elasticsearch performed a problem update");
                         }
                         Log.i("DEBUG Success", "Elasticsearch successfully added the problem");
                     }
                     else {
                         Log.i("DEBUG Error", "Elasticsearch was not able to add the problem");
                     }
                 }
                 catch (Exception e) {
                     Log.i("DEBUG Error", "The application failed to build and send the problems");
                 }

             }
             return null;
         }
     }

     public static class GetProblemsTask extends AsyncTask<String, Void, ArrayList<Problem>> {
         @Override
         protected ArrayList<Problem> doInBackground(String... search_parameters) {
             verifySettings();

             ArrayList<Problem> problems = new ArrayList<Problem>();


             //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
             String problemQuery = "{ \"size\": " + querySize +
                     ", \n" +
                     "    \"query\" : {\n" +
                     "        \"term\" : { \"title\" : \"" + search_parameters[0] + "\" }\n" +
                     "    }\n" +
                     "}" ;

             Search search = new Search.Builder(problemQuery)
                     .addIndex(indexPath)
                     .addType("problem")
                     .build();

             try {
                 // TODO get the results of the query
                 SearchResult result = client.execute(search);
                 if (result.isSucceeded()){
                     List<Problem> foundProblems = result.getSourceAsObjectList(Problem.class);
                     problems.addAll(foundProblems);
                 }
                 else {
                     Log.i("DEBUG Error", "The search query failed to find any problems that matched");
                 }
             }
             catch (Exception e) {
                 Log.i("DEBUG Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
             }

             return problems;
         }
     }




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
     public static class AddRecordTask extends AsyncTask<Record, Void, Void> {

         @Override
         protected Void doInBackground(Record... records) {
             verifySettings();
             Record record = records[0];
             Index index = new Index.Builder(record).index(indexPath).type("record").build();

             if (record.getId() != null) {
                 index = new Index.Builder(record).index(indexPath).type("record").id(record.getId()).build();
             }

             try {
                 // where is the client?
                 DocumentResult result = client.execute(index);
                 if (result.isSucceeded()) {
                     if (record.getId() == null) {
                         record.setId(result.getId());
                         Log.i("DEBUG Update", "Elasticsearch performed record update");
                     } else {
                         Log.i("DEBUG Insert", "Elasticsearch performed a record insert");
                     }
                     Log.i("DEBUG Success", "Elasticsearch successfully added the record");
                 }
                 else {
                     Log.i("DEBUG Error", "Elasticsearch was not able to add the record");
                 }
             }
             catch (Exception e) {
                 Log.i("DEBUG Error", "The application failed to build and send the record");
             }


             return null;
         }
     }

     public static class GetRecordTask extends AsyncTask<String, Void, ArrayList<Record>> {
         @Override
         protected ArrayList<Record> doInBackground(String... search_parameters) {
             verifySettings();

             ArrayList<Record> records = new ArrayList<Record>();


             //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
             String recordQuery = "{ \"size\": " + querySize +
                     ", \n" +
                     "    \"query\" : {\n" +
                     "        \"term\" : { \"title\" : \"" + search_parameters[0] + "\" }\n" +
                     "    }\n" +
                     "}" ;

             Search search = new Search.Builder(recordQuery)
                     .addIndex(indexPath)
                     .addType("record")
                     .build();

             try {
                 // TODO get the results of the query
                 SearchResult result = client.execute(search);
                 if (result.isSucceeded()){
                     List<Record> foundRecords = result.getSourceAsObjectList(Record.class);
                     records.addAll(foundRecords);
                 }
                 else {
                     Log.i("DEBUG Error", "The search query failed to find any care providers that matched");
                 }
             }
             catch (Exception e) {
                 Log.i("DEBUG Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
             }

             return records;
         }
     }
 */

    /**
     * Method verifies server settings are correct 
     */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder(serverURI);
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}