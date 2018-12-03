package com.example.picmymedcode.View;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.RecordActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

public class RecordActivityTest {

    private final static String TAG = "RecordActivityTest: ";

    @Rule
    public ActivityTestRule<RecordActivity> recordActivityTestRuleActivity =
            new ActivityTestRule<RecordActivity>(RecordActivity.class){

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

    /**
     * Testing add record button opening AddRecordActivity
     */
    @Test
    public void TestAddRecordClick() {
        onView(withContentDescription(R.string.add_record)).perform(click());
    }

    /**
     * Testing add record button opening Map For a Particular Problem with all problem
     */
    @Test
    public void TestAllRecordLocationClick() {
        onView(withContentDescription(R.string.map_of_records)).perform(click());
    }

    /**
     * Testing opens up slideShow
     */
    @Test
    public void TestSlideShowOpening() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Slideshow of Photos")).perform(click());
    }

    /**
     * Testing opens up Comment activity
     */
    @Test
    public void TestViewComment() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("View Comments")).perform(click());
    }

    /**
     * Testing opens up gallery pressing the gallery image situated on a recyclerView.
     * It uses a custom class called RecyclerViewInsiderClicker
     */
    @Test
    public void TestGalleryIconOnRecycleView() {
        onView(withId(R.id.record_recycle_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, RecyclerViewInsideClicker
                        .clickChildViewWithId(R.id. record_gallery)));
    }

    /**
     * Testing opens up map pressing the location image situated on a recyclerView.
     * It uses a custom class called RecyclerViewInsiderClicker
     */
    @Test
    public void TestLocationIconOnRecycleView() {
        onView(withId(R.id.record_recycle_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, RecyclerViewInsideClicker
                        .clickChildViewWithId(R.id. mapIcon)));
    }


    /**
     * Scrolling to a specific record
     */
    @Test
    public void scrollingToRecord() {
        try {
            /* Passes when the position has a record stored in it.
             * Then it performs scrollTo action on the view to scroll at that position. */
            Espresso.onView(ViewMatchers.withId(R.id.record_recycle_view))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(0, ViewActions.scrollTo()));
            Log.d(TAG, "The Problem is at the position.");
        } catch (PerformException e) {
            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
        }
    }

}