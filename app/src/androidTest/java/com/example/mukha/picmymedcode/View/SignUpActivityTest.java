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
/* Reduntant class, might not be used in the whole program. MainActivity has the required tests for this parts.*/

//    @Rule
//    public ActivityTestRule<SignUpActivity> signUpActivityTestRuleActivity =
//            new ActivityTestRule<SignUpActivity>(SignUpActivity.class);
//
//    @Test
//    public void TestSignupAsPatient(){
//
//        Espresso.onView(withId(R.id.enteredUID)).perform(typeText("GucciP"));
//
//        Espresso.onView(withText("Let's Start")).perform(ViewActions.click());
//    }

}