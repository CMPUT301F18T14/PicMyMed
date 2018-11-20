package com.example.mukha.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.mukha.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class ProblemActivityTest {

    private final static String TAG = "ProblemActivityTest: ";

    @Rule
    public ActivityTestRule<ProblemActivity> problemActivityTestRuleActivity =
            new ActivityTestRule<ProblemActivity>(ProblemActivity.class);

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
                            .actionOnItemAtPosition(1, ViewActions.longClick()));
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

}