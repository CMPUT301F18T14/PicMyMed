package com.example.mukha.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.mukha.picmymedcode.R;
import com.example.picmymedcode.View.RecordActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class RecordActivityTest {

    private final static String TAG = "RecordActivityTest: ";

    Patient patient = new Patient("mockuser","adsfa","5656");

    Problem problem = new Problem("test",new Date(), "mockTitle", "mock description");

    Record record = new Record("Felt 1");

    @Rule
    public ActivityTestRule<RecordActivity> recordActivityTestRuleActivity =
            new ActivityTestRule<RecordActivity>(RecordActivity.class){
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    PicMyMedApplication picMyMedApplication = new PicMyMedApplication();
                    problem.addRecord(record);
                    patient.getProblemList().add(problem);
                    picMyMedApplication.setLoggedInUser(patient);

                }
            };

    /**
     * Testing add record button opening AddRecordActivity
     */
    @Test
    public void addRecordClick() {
        Espresso.onView(withId(R.id.record_save_button)).perform(ViewActions.click());
    }

    /**
     * Scrolling to a specific record
     */
    @Test
    public void scrollingToRecord(){
        try {
            /* Passes when the position has a record stored in it.
             * Then it performs scrollTo action on the view to scroll at that position. */
            Espresso.onView(ViewMatchers.withId(R.id.record_recycle_view))
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
     * Testing Gallery Button directing to GalleryActivity
     */
    @Test
    public void testOpeningGallery() {
        Espresso.onView(withId(R.id.gallery_button)).perform(ViewActions.click());
    }

    /**
     * Testing Slide Show button directing to SlideShowActivity
     */
    @Test
    public void testOpeningSlideShow() {
        Espresso.onView(withId(R.id.slideshow_button)).perform(ViewActions.click());
    }

    /**
     * Clicking on specific item on the adapterView
     */
    @Test
    public void testClickOnSpecificItemInAdapterView() {
        // Will be implemented in project 5
    }

}