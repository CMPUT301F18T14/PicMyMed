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

public class createNewUserActivityTest {
    @Rule
    public ActivityTestRule<SignUpActivity> newUsernameActivityTestRuleActivity =
            new ActivityTestRule<SignUpActivity>(SignUpActivity.class);

    @Test
    public void TestSignupAsPatient(){

        String patientId = "apuian10";

        String phoneNumber = "5555555555";

        String email = "apuian@gmail.com";

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(patientId));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.enteredPhone)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredPhone)).perform(typeText(phoneNumber));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.enteredEmail)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredEmail)).perform(typeText(email));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

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

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText(patientId));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.enteredPhone)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredPhone)).perform(typeText(phoneNumber));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.enteredEmail)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.enteredEmail)).perform(typeText(email));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.signUpButton)).perform(ViewActions.click());

    }

}