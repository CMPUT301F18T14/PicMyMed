package com.example.picmymedcode.View;

/**
 * A helper class that would help initializing the user in every intent testing,
 * before the activity launched.
 */

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.User;

public class LoggedInUserForTesting {
    private static User user;

    static void LoggedInUserForTesting() {
        PicMyMedApplication picMyMedApplication = new PicMyMedApplication();
        user = PicMyMedController.getPatient("intenttesting");
        user.setElasticSearchID("AWdzHDiUVa1LxfbRovmp");
        user.setLastDeviceUsed("ffffffff-c4b1-10bc-ffff-ffff8d621788");
        user.addAuthorizedDevice("ffffffff-c4b1-10bc-ffff-ffff8d621788");
        picMyMedApplication.setLoggedInUser(user);
    }

    static void LoggedInUserForTestingCare() {
        PicMyMedApplication picMyMedApplication = new PicMyMedApplication();
        CareProvider careProvider = (CareProvider)PicMyMedController.getUser("intentcareprovider");
        careProvider.setElasticSearchID("AWd0xGOZVa1LxfbRovpL");
        careProvider.setLastDeviceUsed("ffffffff-c4b1-10bc-ffff-ffff8d621788");
        careProvider.addAuthorizedDevice("ffffffff-c4b1-10bc-ffff-ffff8d621788");
        //careProvider.getPatientList().add("intenttesting");

        picMyMedApplication.setLoggedInUser(careProvider);
    }
}
