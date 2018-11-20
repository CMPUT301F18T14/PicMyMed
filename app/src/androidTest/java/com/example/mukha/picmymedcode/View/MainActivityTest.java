package com.example.mukha.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.mukha.picmymedcode.R;

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

    @Test
    public void TestSignupAsPatient(){

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.patientButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText("GucciP"));

        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }

    @Test
    public void TestSignupAsCareProvider(){

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.careProviderButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText("GucciD"));

        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }


}