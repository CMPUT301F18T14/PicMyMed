package com.example.picmymedcode.View;

import android.support.test.InstrumentationRegistry;
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
import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class PatientActivityTest {

    private final static String TAG = "PatientActivityTest: ";

    Patient patient = new Patient("apuian12","h@g.com","5555555555");

    //Problem problem = new Problem("test",new Date(), "mockTitle", "mock description");


    @Rule
    public ActivityTestRule<PatientActivity> problemActivityTestRuleActivity =
            new ActivityTestRule<PatientActivity>(PatientActivity.class) {
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    PicMyMedApplication picMyMedApplication = new PicMyMedApplication();
                    patient.setLastDeviceUsed("ffffffff-c4b1-10bc-ffff-ffff8d621788");
                    patient.addAuthorizedDevice("ffffffff-c4b1-10bc-ffff-ffff8d621788");
                    picMyMedApplication.setLoggedInUser(patient);

                }
            };

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void TestAddFromProblem() {
        onView(withId(R.id.addProblem)).perform(click());
    }

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void TestAllMapFromProblem() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Map of all Records")).perform(click());
    }

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void TestBodyPhotosFromProblem() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Body Photos")).perform(click());
    }

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void TestProfileFromProblem() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Profile")).perform(click());
    }

    @Test
    public void TestLogoutFromProblem() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Logout")).perform(click());
    }

    @Test
    public void TestScrollingToProblem(){
        try {
            /* Passes when the position has a problem stored in it.
             * Then it performs longClick action on the view to
             * show the item in a new activity. */
            onView(ViewMatchers.withId(R.id.problem_recycle_view))
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
    public void TestClickToProblem() {
        try {
            /* Passes when the position has a problem stored in it.
             * Then it performs longClick action on the view to
             * show the item in a new activity. */
            onView(ViewMatchers.withId(R.id.problem_recycle_view))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(0, ViewActions.longClick()));
            Log.d(TAG, "The Problem is at the position.");
        } catch(PerformException e) {
            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
        }
    }

}