package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ProfileActivityTest {
    private final static String TAG = "ProfileActivityTest: ";

    @Rule
    public ActivityTestRule<ProfileActivity> ProfileActivityTestRuleActivity =
            new ActivityTestRule<ProfileActivity>(ProfileActivity.class) {
                /**
                 * Initializing a patient before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    LoggedInUserForTesting.LoggedInUserForTesting();

                }
            };

    @Test
    public void TestEditProfileDetails(){
        Espresso.onView(withId(R.id.editProfile_button)).perform(ViewActions.click());
    }

}