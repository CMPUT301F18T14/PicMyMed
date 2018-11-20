package com.example.mukha.picmymedcode.View;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.mukha.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class SignUpActivityTest {

    /* Redundant Test: Already Performed in MainActivityTest */

    @Rule
    public ActivityTestRule<SignUpActivity> signUpActivityTestRuleActivity =
            new ActivityTestRule<SignUpActivity>(SignUpActivity.class);

    /**
     * Testing signing up as a patient. If the patient userId already exists, you will
     * receive user already exists.
     * Try putting new patientId every time.
     */
    @Test
    public void TestSignupAsPatient(){

        String patientId = " GucciP";

        Espresso.onView(withId(R.id.patientButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(patientId));

        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }

    /**
     * Testing signing up as a patient. If the careProvider userId already exists, you will
     * receive user already exists.
     * Try putting new careProviderId every time.
     */
    @Test
    public void TestSignupAsCareProvider(){

        String careProviderId = " GucciD";

        Espresso.onView(withId(R.id.careProviderButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(careProviderId));

        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }

}