package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.CareProviderActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CareProviderActivityTest {

    private final static String TAG = "CareProviderActivtiyTest: ";



    @Rule
    public ActivityTestRule<CareProviderActivity> careProviderActivityTestRule =
            new ActivityTestRule<CareProviderActivity>  (CareProviderActivity.class) {
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    LoggedInUserForTesting.LoggedInUserForTestingCare();
                }
            };

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void addPatient() {
        Espresso.onView(withId(R.id.addPatientButton)).perform(ViewActions.click());
    }
}