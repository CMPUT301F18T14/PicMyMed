package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UserProfileTypeActivityTest {

    /* Redundant Test: Already Performed in MainActivityTest */

    @Rule
    public ActivityTestRule<UserProfileTypeActivity> signUpActivityTestRuleActivity =
            new ActivityTestRule<UserProfileTypeActivity>(UserProfileTypeActivity.class);

    /**
     * Testing signing up as a patient. If the patient userId already exists, you will
     * receive user already exists.
     * Try putting new patientId every time.
     */
    @Test
    public void TestSignupAsPatient(){

        Espresso.onView(withId(R.id.patientButton)).perform(ViewActions.click());
    }

    /**
     * Testing signing up as a patient. If the careProvider userId already exists, you will
     * receive user already exists.
     * Try putting new careProviderId every time.
     */
    @Test
    public void TestSignupAsCareProvider(){

        Espresso.onView(withId(R.id.careProviderButton)).perform(ViewActions.click());
    }

}