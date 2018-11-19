package com.example.mukha.picmymedcode.Controller;


import android.util.Log;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.User;

import java.util.ArrayList;

public class PicMyMedController {



    public static int createUser(User user) {
        if (user.isPatient()) {

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


        }
        return 1;
    }

}