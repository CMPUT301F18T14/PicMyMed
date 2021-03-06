package com.example.picmymedcode.View;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.EditProblemActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class EditProblemActivityTest {

    private final static String TAG = "EditProblemActivityTest: ";

    @Rule
    public ActivityTestRule<EditProblemActivity> mActivity = new ActivityTestRule<EditProblemActivity>(EditProblemActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra("key", 0);
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
    public void testEditingProblem() throws InterruptedException {


        // Find a view with id problem_edit_title_edit_text and type "test" on that view.
        Espresso.onView(withId(R.id.problem_edit_title_edit_text)).perform(ViewActions.clearText(), ViewActions.typeText("HeadAche"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        Espresso.closeSoftKeyboard();

        // Find a view with id problem_edit_description_edit_text and type "What is happening?" on that view.
        Espresso.onView(withId(R.id.problem_edit_description_edit_text)).perform(ViewActions.clearText(), ViewActions.typeText("What is happening?"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        Espresso.closeSoftKeyboard();

        // Find a view with id problem_edit_save_button and clicks on that view.
        Espresso.onView(withId(R.id.problem_edit_save_button)).perform(ViewActions.click());

    }

}