package com.example.picmymedcode.View;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.junit.Assert.*;

public class SelectBodyLocationActivityTest {

    private final static String TAG = "RecordActivityTest: ";

    @Rule
    public ActivityTestRule<SelectBodyLocationActivity> selectBodyActivityTestRuleActivity =
            new ActivityTestRule<SelectBodyLocationActivity>(SelectBodyLocationActivity.class){
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    LoggedInUserForTesting.LoggedInUserForTesting();
                }


            };

    /**
     * Testing add record button opening CameraIntent
     */
    @Test
    public void testClickOnCamera() {
        onView(withId(R.id.take_photo_button)).perform(click());
    }




}