package com.example.mukha.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Model.CareProvider;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CareProviderProblemActivityTest {

    private final static String TAG = "CareProviderProblemActivityTest: ";

    CareProvider careProvider = new CareProvider("CP");


    @Rule
    public ActivityTestRule<CareProviderProblemActivity> CareProviderProblemActivityTestRule =
            new ActivityTestRule<CareProviderProblemActivity>  (CareProviderProblemActivity.class) {
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
    public void viewProblems() {
        //Espresso.onView(withId(R.id.search_patient_button)).perform(ViewActions.click());
        // Will be implemented in project 5
    }
    @Test
    public void testClickOnSpecificItemInAdapterView() {
        // Will be implemented in project 5
    }

}