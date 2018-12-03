/*
 * PicMyMedController
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

import android.content.Context;
import android.os.Build;
import android.util.AtomicFile;
import android.util.Log;

import com.example.picmymedcode.Model.BodyLocationPhoto;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Photo;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.Model.User;

import java.util.ArrayList;

import java.util.Date;
import java.util.UUID;

/**
 * PicMyMedController creates a user
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class PicMyMedController {

    /**
     * Method checks if the user is valid
     *
     * @param username  String
     * @return          int
     */
    public static int checkValidUser(String username) {
        ArrayList<Patient> patients = null;
        ArrayList<CareProvider> careProviders = null;

        ElasticSearchController.GetPatient getPatient = new ElasticSearchController.GetPatient();
        getPatient.execute(username);

        ElasticSearchController.GetCareProvider getCareProvider = new ElasticSearchController.GetCareProvider();
        getCareProvider.execute(username);

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
            return 0;
        }
        return 1;
    }

    /**
     * Method creates a new user
     *
     * @param user  user
     * @return      int
     */
    public static int createUser(User user) {
        try {
            if (checkValidUser(user.getUsername()) == 0) {
                user.addAuthorizedDevice(getUniquePsuedoID());

                if (user.isPatient()) {
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

        } catch (Exception e) {
            Log.i("DEBUG PMMController", e.getMessage());
        }
        return 0;
    }

    /**
     * Method checks if username provided matches a user in the database and returns the user type
     *
     * @param username  String
     * @return          int
     */
    public static User getUser(String username) {

        User user = null;
        ElasticSearchController.GetPatient getPatient = new ElasticSearchController.GetPatient();
        getPatient.execute(username);

        ElasticSearchController.GetCareProvider getCareProvider = new ElasticSearchController.GetCareProvider();
        getCareProvider.execute(username);

        try {
            user = getPatient.get().get(0);
        } catch (Exception e) {
            Log.i("DEBUG PMMController", "No patients with the entered username was found");
        }

        if (user == null) {
            try {
                user = getCareProvider.get().get(0);
            } catch (Exception e) {
                Log.i("DEBUG PMMController", "No careproviders with the entered username was found");
            }
        }

        return user;
    }

    /**
     * Method updates the user
     *
     * @param user      User
     * @param context   Context
     * @return          int
     */
    public static int updateUser(User user, Context context) {
        try {
            if(PicMyMedApplication.isNetworkAvailable(context) ) {
                if (user != null) {
                    if (user.isPatient()) {
                        updatePatient((Patient) user, context);
                    } else {
                        updateCareProvider((CareProvider) user, context);
                    }
                    return 1;
                }
            } else {
                user.setRequiresSync(Boolean.TRUE);
                Log.i("DEBUG PMC", "Unable to save to the online database, storing locally ...");
            }

        } catch (Exception e) {
                Log.d("DEBUG PMC", "Error updating user");
                Log.d("Error message", e.getMessage());
        }
        return 0;
    }

    /**
     * Method checks if the device is authorized for the user
     *
     * @param user  User
     * @return      user
     */
    public static int checkAuthorizedDevice(User user) {
        return user.checkDeviceAuthorized(getUniquePsuedoID());
    }

    /**
     * Method adds an authorized device
     *
     * @param user      User
     * @param context   Context
     * @return          int
     */
    public static int addAuthorizedDevice(User user, Context context) {

        try {
            if (user != null) {
                String deviceID = getUniquePsuedoID();
                if (user.checkDeviceAuthorized(deviceID) == 0) {
                    user.addAuthorizedDevice(deviceID);
                    Log.d("DEBUG", "Trying to add device to authorized list");
                    updateUser(user, context);
                } else {
                    Log.d("DEBUG PMC", "Device already authorized!");
                }
                return 1;
            }
        } catch (Exception e) {
            Log.d("DEBUG PMC", "Error adding authorized device");
            Log.d("Error message", e.getMessage());
        }

        return 0;
    }

    /**
     * Method returns the users problems. Needs to be fized to address if the array list is empty
     *
     * @return  problemList
     */
    public static String getUsernameByID(String randomUserID) {

        String username = null;

        ElasticSearchController.GetUsernameByID getUsernameByID= new ElasticSearchController.GetUsernameByID();
        getUsernameByID.execute(randomUserID);
        try {
            username = getUsernameByID.get();
        } catch (Exception e) {
            Log.i("DEBUG PMMController", "No users with the QR Code was found");
        }
        return username;
    }

    /**
     * Method gets a list of problems
     *
     * @return  problemList
     */
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

    /**
     * Method gets a string of all patient usernames
     *
     * @return  patientUsernames
     */
    public static ArrayList<String> getAllPatientUsernames() {
        ArrayList<String> patientUsernames = new ArrayList<String>();
        ElasticSearchController.GetAllPatients getAllPatients = new ElasticSearchController.GetAllPatients();
        getAllPatients.execute();

        try {
            for (Patient patient : getAllPatients.get()) {
                if (!patientUsernames.contains(patient.getUsername())) {
                    patientUsernames.add(patient.getUsername());
                }
            }
        } catch (Exception e) {
            Log.i("DEBUG PMMController", "Error retrieving patients");
        }
        return patientUsernames;
    }

    /**
     * Method gets the patient
     *
     * @param username  String
     * @return          patient
     */
    public static Patient getPatient(String username) {
        Patient patient = (Patient) getUser(username);
        return patient;
    }


    /**
     * Method adds new problems to elastic search. problem is added to the user
     * Database is updated
     *
     * @param problem   Problem
     * @return          int
     */
    public static int addProblem(Problem problem, Context context) {

        Patient patient = PicMyMedApplication.getPatientUser();
        patient.getProblemList().add(problem);

        updateUser(patient, context);

        return 1;
    }

    /**
     * Method adds editted problem to elastic search and updates problem list
     *
     * @param problem       Problem
     * @param date          Date
     * @param title         String
     * @param description   String
     * @return              int
     */
    public static int editProblem(Problem problem, String date, String title, String description, Context context) {
        Patient patient = PicMyMedApplication.getPatientUser();
        problem.setStartDate(date);
        problem.setTitle(title);
        problem.setDescription(description);
        updateUser(patient, context);

        return 1;
    }

    /**
     * Method deletes problem from database and updates the patient and problem list
     *
     * @param problem   Problem
     * @return          int
     */
    public static int deleteProblem(Problem problem, Context context) {
        Patient patient = PicMyMedApplication.getPatientUser();
        patient.getProblemList().remove(problem);
        updateUser(patient, context);
        return 1;
    }

    /**
     * Method adds a record to a problem and to a user in the database and updates database
     *
     * @param problem   Problem
     * @param record    Record
     * @return          int
     */
    public static int addRecord(Problem problem, Record record, Context context) {

        Patient patient = PicMyMedApplication.getPatientUser();
        problem.addRecord(record);
        updateUser(patient, context);

        return 1;
    }

    /**
     * Method deletes a record from a problem
     *
     * @param problem   Problem
     * @param record    Record
     * @param context   Context
     * @return          int
     */
    public static int deleteRecord(Problem problem, Record record, Context context) {
        Patient patient = PicMyMedApplication.getPatientUser();
        problem.removeRecord(record);
        updateUser(patient, context);

        return 1;
    }

    /**
     * Method deletes a record from a photo
     *
     * @param problemIndex  int
     * @param recordIndex   int
     * @param photoIndex    int
     * @param context       Context
     * @return              int
     */
    public static int deleteRecordPhoto(int problemIndex, int recordIndex, int photoIndex, Context context) {
        Patient patient = PicMyMedApplication.getPatientUser();
        Problem problem = patient.getProblemList().get(problemIndex);
        Record record = problem.getRecordList().get(recordIndex);
        Photo photo = record.getPhotoList().remove(photoIndex);
        //patient.getProblemList().get(problemIndex).getRecordList().get(recordIndex).getPhotoList().remove(photoIndex);
        updateUser(patient, context);

        return 1;
    }

    /**
     * Method gets user from the controller and calls elastic search and updates the database
     *
     * @param patient   Patient
     * @return          int
     */
    public static int updatePatient(Patient patient, Context context) {

        try {
            ElasticSearchController.UpdatePatient updatePatient = new ElasticSearchController.UpdatePatient();
            updatePatient.execute(patient);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Method gets user from the controller and calls elastic search and updates the database
     *
     * @param careProvider   CareProvider
     * @return               int
     */
    public static int updateCareProvider(CareProvider careProvider, Context context) {

        try {
            ElasticSearchController.UpdateCareProvider updateCareProvider = new ElasticSearchController.UpdateCareProvider();
            updateCareProvider.execute(careProvider);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Method adds patient to careprovider
     *
     * @param patientUsername   String
     * @return               int
     */
    public static int addPatientToCareProvider(String patientUsername, Context context) {

        CareProvider careProvider = PicMyMedApplication.getCareProviderUser();
        if (!careProvider.getPatientList().contains(patientUsername)){
            careProvider.getPatientList().add(patientUsername);
            updateUser(careProvider, context);
            return 1;
        }
        return 0;
    }

    /**
     * Method updates the patients proifle with an email and phone then updates the database
     *
     * @param email String
     * @param phone String
     * @return      int
     */
    public static int updateUserProfile(User user, String email, String phone, Context context) {

        try {
            user.setEmail(email);
            user.setPhoneNumber(phone);
            updateUser(user, context);
            return 1;
        } catch (IllegalArgumentException e) {
            Log.i("DEBUG PMMC", "Unable to update user profile!");
        }
        return 0;

    }

    /**
     * Method adds a body location photo
     *
     * @param photo     BodyLocationPhoto
     * @param context   Context
     * @return          int
     */
    public static int addBodyLocationPhoto(BodyLocationPhoto photo, Context context) {
        Log.d ("addBodyLocationPhoto", "in controller");
        Patient patient = PicMyMedApplication.getPatientUser();
        patient.addBodyLocationPhoto(photo);

        updateUser(patient, context);
        return 1;
    }

    /**
     * Method removes a body location photo
     *
     * @param index     int
     * @param context   Context
     * @return          int
     */
    public static int removeBodyLocationPhoto(int index, Context context) {
        Patient patient = PicMyMedApplication.getPatientUser();
        patient.getBodyLocationPhotoList().remove(index);

        updateUser(patient, context);
        return 1;
    }

    /**
     * Method updates a body location photo
     *
     * @param index     int
     * @param label     String
     * @param context   Context
     * @return          int
     */
    public static int updateBodyLocationPhoto(int index, String label, Context context) {
        Patient patient = PicMyMedApplication.getPatientUser();
        patient.getBodyLocationPhotoList().get(index).setLabel(label);

        updateUser(patient, context);
        return 1;
    }

    /**
     * Method gets all care providers
     *
     * @return  careProviders
     */
    public static ArrayList<CareProvider> getAllCareProviders() {
        ArrayList<CareProvider> careProviders = null;
        ElasticSearchController.GetAllCareProviders getAllCareProviders = new ElasticSearchController.GetAllCareProviders();
        getAllCareProviders.execute();
        try {
            careProviders = getAllCareProviders.get();
        } catch (Exception e) {
            Log.i("DEBUG PMMController", "No patients with the entered username was found");

        }
        return careProviders;
    }

    /**
     * Method gets photo list
     *
     * @param problemIndex  int
     * @param recordIndex   int
     * @return              photoList
     */
    public static ArrayList<Photo> getPhotoList(int problemIndex, int recordIndex) {
        return PicMyMedApplication.getPatientUser().getProblemList().get(problemIndex).getRecordList().get(recordIndex).getPhotoList();
    }

    /**
     * Method checks if the device is the same
     *
     * @param user  User
     * @return      int
     */
    public static int checkIfSameDevice(User user) {

        try {
            if (user != null && user.getLastDeviceUsed().equals(getUniquePsuedoID())) {
                return 1;
            }
        } catch (Exception e) {
            Log.i("DEBUG PMC", "Could not update last device used!");
            Log.i("DEBUG PMC", e.getMessage());
        }
        return 0;
    }

    /**
     * Method updates the last device used
     *
     * @param user      User
     * @param context   Context
     * @return          int
     */
    public static int updateLastDeviceUsed(User user, Context context) {
        try {
            user.setLastDeviceUsed(getUniquePsuedoID());
            updateUser(user, context);
            return 1;
        } catch (Exception e) {
            Log.i("DEBUG PMC", "Could not update last device used!");
            Log.i("DEBUG PMC", e.getMessage());
        }
        return 0;
    }
    /**
     * Return pseudo unique ID
     * @return ID
     */
    public static String getUniquePsuedoID() {
        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their device or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        // Thanks to @Roman SL!
        // https://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their device, there will be a duplicate entry
        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            // String needs to be initialized
            serial = "serial"; // some value
        }
        // Thanks @Joe!
        // https://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to create a unique identifier
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }
}
