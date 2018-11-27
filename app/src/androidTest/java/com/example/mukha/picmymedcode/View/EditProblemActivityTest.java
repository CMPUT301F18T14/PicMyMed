package com.example.mukha.picmymedcode.View;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.Model.User;
import com.example.mukha.picmymedcode.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class EditProblemActivityTest {

    private final static String TAG = "EditProblemActivityTest: ";

    Patient patient = new Patient("mockuser","adsfa","5656");

    Problem problem = new Problem("test",new Date(), "mockTitle", "mock description");

    @Rule
    public ActivityTestRule<EditProblemActivity> mActivity = new ActivityTestRule<EditProblemActivity>(EditProblemActivity.class){
        /**
         * Initializing a patient with a problem before running the activity
         */
        @Override
        protected void beforeActivityLaunched() {
            //super.beforeActivityLaunched();
            PicMyMedApplication picMyMedApplication = new PicMyMedApplication();
            patient.getProblemList().add(problem);
            picMyMedApplication.setLoggedInUser(patient);

        }
    };
    @Test
    public void testEditingProblem() {


        // Find a view with id problem_edit_title_edit_text and type "test" on that view.
        Espresso.onView(withId(R.id.problem_edit_title_edit_text)).perform(typeText("test"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        closeSoftKeyboard();

        // Find a view with id problem_edit_description_edit_text and type "What is happening?" on that view.
        Espresso.onView(withId(R.id.problem_edit_description_edit_text)).perform(typeText("What is happening?"));

        // Close the keyboard, if there is a button that is covered by it,
        // it will not be visible to espresso, and will throw an exception
        closeSoftKeyboard();

        // Find a view with id problem_edit_save_button and clicks on that view.
        Espresso.onView(withId(R.id.problem_edit_save_button)).perform(ViewActions.click());

        assertEquals(problem.getTitle(), "test");
    }

}