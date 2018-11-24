package com.example.cmput301f18t14.picmymed.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.cmput301f18t14.picmymed.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRuleActivity =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    /**
     * Testing Login. If the userId already exist, you will receive invalid userId message
     */
    @Test
    public void TestLoginByUsername(){

        // Find a view with id enteredUID and type "username" on that view.
        Espresso.onView(withId(R.id.enteredUID)).perform(typeText("username"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        closeSoftKeyboard();

        // Find a view with id loginButton and clicks on that view.
        Espresso.onView(withId(R.id.loginButton)).perform(ViewActions.click());
    }

    /**
     * Testing signing up as a patient. If the patient userId already exists, you will
     * receive user already exists.
     * Try putting new patientId every time.
     */
    @Test
    public void TestSignupAsPatient(){

        String patientId = " GucciP";

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

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

        String careProviderId = "GucciD";

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.careProviderButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(careProviderId));

        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }


}