package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.EditProfileActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

public class EditProfileActivityTest {

    private final static String TAG = "EditProfileActivityTest: ";

    @Rule
    public ActivityTestRule<EditProfileActivity> mActivity = new ActivityTestRule<EditProfileActivity>(EditProfileActivity.class){
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
    public void testEditingProfile() {

        Espresso.onView(withId(R.id.enteredPhone)).perform(ViewActions.clearText(),
                ViewActions.typeText("7804925050"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        Espresso.closeSoftKeyboard();

        // Find a view with id enteredEmail and type "daenerys.targaryen@got.we" on that view.
        Espresso.onView(withId(R.id.enteredEmail)).perform(ViewActions.clearText(),
                ViewActions.typeText("gucci@gucci.com"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        Espresso.closeSoftKeyboard();

        // Find a view with id updateButton and click on that view.
        Espresso.onView(withId(R.id.updateButton)).perform(ViewActions.click());
    }

}