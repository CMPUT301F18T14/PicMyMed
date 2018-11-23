package com.example.cmput301f18t14.PicMyMed.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.cmput301f18t14.PicMyMed.Controller.PicMyMedApplication;
import com.example.cmput301f18t14.PicMyMed.Model.CareProvider;
import com.example.cmput301f18t14.PicMyMed.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CareProviderActivityTest {

    private final static String TAG = "CareProviderActivtiyTest: ";

    CareProvider careProvider = new CareProvider("CP");


    @Rule
    public ActivityTestRule<CareProviderActivity> careProviderActivityTestRule =
            new ActivityTestRule<CareProviderActivity>  (CareProviderActivity.class) {
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    PicMyMedApplication picMyMedApplication = new PicMyMedApplication();
                    picMyMedApplication.setLoggedInUser(careProvider);

                }
            };

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void addPatient() {
        Espresso.onView(withId(R.id.addPatientButton)).perform(ViewActions.click());
    }
    @Test
    public void searchPatient() {
        //Espresso.onView(withId(R.id.search_patient_button)).perform(ViewActions.click());
        // Will be implemented in project 5
    }
    @Test
    public void testClickOnSpecificItemInAdapterView() {
        // Will be implemented in project 5
    }

}