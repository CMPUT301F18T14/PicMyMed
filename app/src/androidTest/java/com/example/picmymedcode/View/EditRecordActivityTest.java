package com.example.picmymedcode.View;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.junit.Assert.*;

public class EditRecordActivityTest {

    private final static String TAG = "EditRecordActivityTest: ";

    @Rule
    public ActivityTestRule<EditRecordActivity> mActivity = new ActivityTestRule<EditRecordActivity>(EditRecordActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra("problem index", 0);
            intent.putExtra("record index", 0);
            return intent;
        }
        /**
         * Initializing a patient with a problem before running the activity
         */
        @Override
        protected void beforeActivityLaunched() {
            //super.beforeActivityLaunched();
            LoggedInUserForTesting.LoggedInUserForTesting();
        }
    };

    @Test
    public void testEditingProblem() {


        // Find a view with id problem_edit_title_edit_text and type "test" on that view.
        Espresso.onView(withId(R.id.record_title_edit_text)).perform(ViewActions.clearText(), ViewActions.typeText("HeadAche"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        Espresso.closeSoftKeyboard();

        // Find a view with id problem_edit_description_edit_text and type "What is happening?" on that view.
        Espresso.onView(withId(R.id.record_description_edit_text)).perform(ViewActions.clearText(), ViewActions.typeText("What is happening?"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        Espresso.closeSoftKeyboard();

        // Find a view with id problem_edit_save_button and clicks on that view.
        Espresso.onView(withText("Save")).perform(ViewActions.click());

    }

}