package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.CareProviderProblemActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CareProviderProblemActivityTest {

    private final static String TAG = "CareProviderProblemActivityTest: ";

    @Rule
    public ActivityTestRule<CareProviderProblemActivity> CareProviderProblemActivityTestRule =
            new ActivityTestRule<CareProviderProblemActivity>  (CareProviderProblemActivity.class) {
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
    public void TestSearchPatient() {
        Espresso.onView(withId(R.id.careprovider_search_image_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }
    @Test
    public void TestEmailSending() {
        Espresso.onView(withId(R.id.careprovider_problem_email_text_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }
    @Test
    public void TestPhoneCalling() {
        Espresso.onView(withId(R.id.careprovider_problem_phone_text_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }

}