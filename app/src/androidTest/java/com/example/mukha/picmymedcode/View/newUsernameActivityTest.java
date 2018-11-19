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

public class newUsernameActivityTest {
    @Rule
    public ActivityTestRule<newUsernameActivity> newUsernameActivityTestRuleActivity =
            new ActivityTestRule<newUsernameActivity>(newUsernameActivity.class);

    @Test
    public void TestSignup(){
        // Will fail unless intent sent from MainActivity
        // Tested Successfully in MainActivityTest

        // In this activity, it can only be typed, but can't be set
        Espresso.onView(withId(R.id.enteredUID)).perform(typeText("NewGuc"));

        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }

}