package com.example.mukha.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.mukha.picmymedcode.R;
import com.example.picmymedcode.View.newUsernameActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class newUsernameActivityTest {
    @Rule
    public ActivityTestRule<newUsernameActivity> newUsernameActivityTestRuleActivity =
            new ActivityTestRule<newUsernameActivity>(newUsernameActivity.class);

    /**
     * Testing new signup activity
     */
    @Test
    public void testNewSignup(){

        Espresso.onView(withId(R.id.enteredUID)).perform(typeText("NewGuc"));

        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
    }

}