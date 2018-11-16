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

public class ElasticSearchController {

    private static JestDroidClient client;

    private static final String serverURI = "http://cmput301.softwareprocess.es:8080/";
    private static final String indexPath = "cmput301f18t14test";
    private static final String querySize = "10";
    private static final String patientType = "patient";
    private static final String careProviderType = "careprovider";


    public static class AddProblemsTask extends AsyncTask<Problem, Void, Void> {

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
                            Log.i("Insert", "Elasticsearch performed a problem insert");
                        } else {
                            Log.i("Update", "Elasticsearch performed a problem update");
                        }
                        Log.i("Success", "Elasticsearch successfully added the problem");
                    }
                    else {
                        Log.i("Error", "Elasticsearch was not able to add the problem");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the problems");
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
                    Log.i("Error", "The search query failed to find any problems that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return problems;
        }
    }

    public static class AddPatientTask extends AsyncTask<Patient, Void, Void> {

        @Override
        protected Void doInBackground(Patient... patients) {
            Log.i("Elasticsearch:", "Attempting to query...");
            verifySettings();
            Patient patient = patients[0];
            Log.i("Elasticsearch:", "Creating index...");
            Index index = new Index.Builder(patient).index(indexPath).type("patient").build();
            if (patient.getUserID() != null) {
                index = new Index.Builder(patient).index(indexPath).type("patient").id(patient.getUserID()).build();
                Log.i("Elasticsearch", "... ID exists. Adding ID to index");
            }

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    if (patient.getUserID() == null) {
                        patient.setUserID(result.getId());
                        Log.i("Elasticsearch", "Elasticsearch successfully performed a patient insert");
                    } else {
                        Log.i("Elasticsearch", "Elasticsearch successfully performed a patient update");
                    }
                }
                else {
                    Log.i("Elasticsearch", "Elasticsearch failed to execute.");
                }
            }
            catch (Exception e) {
                Log.i("Error", "The application failed to build and send the patient");
            }


            return null;
        }
    }

    public static class GetPatientTask extends AsyncTask<String, Void, ArrayList<Patient>> {
        @Override
        protected ArrayList<Patient> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Patient> patients = new ArrayList<Patient>();


            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String patientQuery = "{ \"size\": " + querySize +
                    ", \n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"username\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(patientQuery)
                    .addIndex(indexPath)
                    .addType("patient")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<Patient> foundPatients = result.getSourceAsObjectList(Patient.class);
                    patients.addAll(foundPatients);
                }
                else {
                    Log.i("Error", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return patients;
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

                    //Log.i("DeBug", "Succeeded in finding a user");

                    List<Patient> foundPatients = result.getSourceAsObjectList(Patient.class);
                    patients.addAll(foundPatients);
                }
                else{
                    Log.i("Error", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return patients;
        }
    }
    public static class AddCareProvider extends AsyncTask<CareProvider, Void, Void> {

        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            verifySettings();

            CareProvider careProvider = careProviders[0];
            Index index = new Index.Builder(careProvider).index(indexPath).type(careProviderType).build();

            try {
                // where is the client?
                DocumentResult result = client.execute(index);

                if (result.isSucceeded()) {
                    if (careProvider.getUserID() == null) {
                        careProvider.setUserID(result.getId());
                        Log.i("AddCareProvider", "Careprovider ID " + result.getId() + "generated.");
                    } else {
                        Log.i("AddCareProvider", "Failed to generate Careprovider ID.");
                    }
                    Log.i("AddCareProvider", "Elasticsearch successfully added the Careprovider");
                }
                else {
                    Log.i("Error", "Elasticsearch was not able to add the Careprovider");
                }
            }
            catch (Exception e) {
                Log.i("Error", "The application failed to build and add the Careprovider");
            }


            return null;
        }
    }

    public static class GetCareProviderTask extends AsyncTask<String, Void, ArrayList<CareProvider>> {
        @Override
        protected ArrayList<CareProvider> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<CareProvider> careProviders = new ArrayList<CareProvider>();


            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String careProviderQuery = "{ \"size\": " + querySize +
                    ", \n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"username\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(careProviderQuery)
                    .addIndex(indexPath)
                    .addType("careprovider")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<CareProvider> foundCareProviders = result.getSourceAsObjectList(CareProvider.class);
                    careProviders.addAll(foundCareProviders);
                }
                else {
                    Log.i("Error", "The search query failed to find any care providers that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return careProviders;
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

                    //Log.i("DeBug", "Succeeded in finding a user");

                    List<CareProvider> foundCareProviders = result.getSourceAsObjectList(CareProvider.class);
                    careProviders.addAll(foundCareProviders);
                }
                else{
                    Log.i("Error", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
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
                        Log.i("Update", "Elasticsearch performed record update");
                    } else {
                        Log.i("Insert", "Elasticsearch performed a record insert");
                    }
                    Log.i("Success", "Elasticsearch successfully added the record");
                }
                else {
                    Log.i("Error", "Elasticsearch was not able to add the record");
                }
            }
            catch (Exception e) {
                Log.i("Error", "The application failed to build and send the record");
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
                    Log.i("Error", "The search query failed to find any care providers that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return records;
        }
    }

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
