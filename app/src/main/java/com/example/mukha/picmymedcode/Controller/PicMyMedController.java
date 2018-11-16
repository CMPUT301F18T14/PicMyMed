package com.example.mukha.picmymedcode.Controller;

import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.User;

public class PicMyMedController {

    public static int createUser(User user){
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


}
