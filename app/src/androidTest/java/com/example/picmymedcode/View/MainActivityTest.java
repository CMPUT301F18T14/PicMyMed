package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.picmymedcode.R;
import com.example.picmymedcode.View.MainActivity;

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
    public void TestLoginAsPatient(){

        String patientId = "apuian10";

        // Find a view with id enteredUID and type "username" on that view.
        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(patientId));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        closeSoftKeyboard();

        // Find a view with id loginButton and clicks on that view.
        Espresso.onView(withId(R.id.loginButton)).perform(ViewActions.click());
    }

    /**
     * Testing Login. If the userId already exist, you will receive invalid userId message
     */
    @Test
    public void TestLoginAsCareProvider(){

        String careProviderId = "apuian11";

        // Find a view with id enteredUID and type "username" on that view.
        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(careProviderId));

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

        String patientId = "apuian10";

        String phoneNumber = "5555555555";

        String email = "apuian@gmail.com";

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.patientButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(patientId));

        Espresso.onView(withId(R.id.enteredPhone)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredPhone)).perform(typeText(phoneNumber));

        Espresso.onView(withId(R.id.enteredEmail)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredEmail)).perform(typeText(email));

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

        //Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }

    /**
     * Testing signing up as a patient. If the patient userId already exists, you will
     * receive user already exists.
     * Try putting new patientId every time.
     */
    @Test
    public void TestSignupAsCareProvider(){

        String patientId = "apuian11";

        String phoneNumber = "5555555555";

        String email = "apuian@gmail.com";

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.careProviderButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(patientId));

        Espresso.onView(withId(R.id.enteredPhone)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredPhone)).perform(typeText(phoneNumber));

        Espresso.onView(withId(R.id.enteredEmail)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredEmail)).perform(typeText(email));

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

        //Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }

}