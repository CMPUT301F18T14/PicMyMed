package com.example.picmymedcode.View;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;

public class CareProviderAddPatientActivityTest {

    private final static String TAG = "CareProviderAddPatient: ";

    @Rule
    public ActivityTestRule<CareProviderAddPatientActivity> careProviderAddPatientActivityTestRule =
            new ActivityTestRule<CareProviderAddPatientActivity>  (CareProviderAddPatientActivity.class) {
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    LoggedInUserForTesting.LoggedInUserForTestingCare();
                }

                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra("key2", 0);
                    return intent;
                }
            };

    @Test
    public void testSearchAndSelectingPatient() {
        Espresso.onView(withId(R.id.search)).perform(ViewActions.typeText("intenttesting"));
        onData(anything()).inAdapterView(withId(R.id.patient_listview)).atPosition(0).perform(click());
    }

    @Test
    public void testOpeningQRCode() {
        Espresso.onView(withId(R.id.careprovider_qr)).perform(ViewActions.click());
    }

}