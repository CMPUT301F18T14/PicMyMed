package com.example.mukha.picmymedcode.Controller;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.User;

public class PicMyMedApplication {

    static User loggedInUser;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static void getLoggedInUser() {

        if (loggedInUser.isPatient()){
            getPatientUser();
        } else {
            getCareProviderUser();
        }

    }

    public static void logoutUser() {
        loggedInUser = null;
    }

    public static Patient getPatientUser() {
        Patient patient = (Patient) loggedInUser;
        return patient;
    }

    public static CareProvider getCareProviderUser() {
        CareProvider careProvider = (CareProvider) loggedInUser;
        return careProvider;
    }
}
