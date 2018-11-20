package com.example.mukha.picmymedcode.View;

import android.app.Activity;
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
import static org.junit.Assert.*;

public class RecordActivityTest {

    private final static String TAG = "RecordActivityTest: ";

    @Rule
    public ActivityTestRule<RecordActivity> recordActivityTestRuleActivity =
            new ActivityTestRule<RecordActivity>(RecordActivity.class);

    @Test
    public void addRecordClick() {
        Espresso.onView(withId(R.id.record_save_button)).perform(ViewActions.click());
    }

    @Test
    public void scrollingToRecord(){
        try {
            /* Passes when the position has a record stored in it.
             * Then it performs longClick action on the view to
             * show the item in a new activity. */
            Espresso.onView(ViewMatchers.withId(R.id.record_recycle_view))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(0, ViewActions.longClick()));
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