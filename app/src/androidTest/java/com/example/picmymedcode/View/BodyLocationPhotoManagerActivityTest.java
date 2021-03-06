package com.example.picmymedcode.View;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class BodyLocationPhotoManagerActivityTest {
    private final static String TAG = "BodyLocationPhotoManagerActivityTest: ";

    @Rule
    public ActivityTestRule<BodyLocationPhotoManagerActivity> mActivity = new ActivityTestRule<BodyLocationPhotoManagerActivity>(BodyLocationPhotoManagerActivity.class){
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
     * Test for editing phone number and email address
     */
    @Test
    public void testCameraIntent() {
        Espresso.onView(withId(R.id.take_photo_button)).perform(ViewActions.click());
    }

    /**
     * Test for editing phone number and email address
     */
    @Test
    public void testClickingOnPhoto() {

        try {
            /* Passes when the position has a problem stored in it.
             * Then it performs longClick action on the view to
             * show the item in a new activity. */
            onView(ViewMatchers.withId(R.id.gallery))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(0, ViewActions.click()));
            Log.d(TAG, "The Problem is at the position.");
        } catch(PerformException e) {
            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
        }
    }

}