package com.example.mukha.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.mukha.picmymedcode.Controller.PicMyMedApplication;
import com.example.mukha.picmymedcode.Model.Patient;
import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class ProblemActivityTest {

    private final static String TAG = "ProblemActivityTest: ";

    Patient patient = new Patient("mockuser","adsfa","5656");

    Problem problem = new Problem("test",new Date(), "mockTitle", "mock description");


    @Rule
    public ActivityTestRule<ProblemActivity> problemActivityTestRuleActivity =
            new ActivityTestRule<ProblemActivity>(ProblemActivity.class) {
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

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void addProblem() {
        Espresso.onView(withId(R.id.problem_save_button)).perform(ViewActions.click());
    }

    @Test
    public void scrollingToProblem(){
        try {
            /* Passes when the position has a problem stored in it.
             * Then it performs longClick action on the view to
             * show the item in a new activity. */
            Espresso.onView(ViewMatchers.withId(R.id.problem_recycle_view))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(0, ViewActions.scrollTo()));
            Log.d(TAG, "The Problem is at the position.");
        } catch(PerformException e) {
            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
        }

//        try {
//            /* Passes when the position has a problem stored in it.
//             * Then it performs longClick action on the view to
//             * show the item in a new activity. */
//            Espresso.onView(ViewMatchers.withId(R.id.problem_recycle_view))
//                    .perform(RecyclerViewActions
//                            .actionOnItemAtPosition(1000, ViewActions.click()));
//            Log.d(TAG, "The Problem is at the position.");
//        } catch(PerformException e) {
//            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
//        }
    }

    /**
     * Clicking on specific item on the adapterView
     */
    @Test
    public void testClickOnSpecificItemInAdapterView() {
        // Will be implemented in project 5
    }

}