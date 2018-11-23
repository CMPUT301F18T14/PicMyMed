package com.example.cmput301f18t14.PicMyMed.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.cmput301f18t14.PicMyMed.Controller.PicMyMedApplication;
import com.example.cmput301f18t14.PicMyMed.Model.Patient;
import com.example.cmput301f18t14.PicMyMed.Model.Problem;
import com.example.cmput301f18t14.PicMyMed.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AddRecordActivityTest {

    private final static String TAG = "EditProblemActivityTest: ";

    Patient patient = new Patient("mockuser","adsfa","5656");

    Problem problem = new Problem("test",new Date(), "mockTitle", "mock description");

    @Rule
    public ActivityTestRule<AddRecordActivity> addRecordActivityTestRuleActivity =
            new ActivityTestRule<AddRecordActivity>(AddRecordActivity.class) {
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
     * Testing adding a record
     */
    @Test
    public void addingRecordDetails(){
        Espresso.onView(withId(R.id.record_title_edit_text))
                .perform(typeText("Felt 1"));
        Espresso.onView(withId(R.id.record_description_edit_text))
                .perform(typeText("Felt like something is trying to rip my heart apart."));

        closeSoftKeyboard();

        Espresso.onView(withId(R.id.record_save_button)).perform(ViewActions.click());
    }
    /**
     * Testing opening a map intent
     */
    @Test
    public void openMapIntent(){
        Espresso.onView(withId(R.id.record_geo_button)).perform(ViewActions.click());
    }

    /**
     * Testing opening a camera intent
     */
    @Test
    public void openCameraIntent(){
        Espresso.onView(withId(R.id.record_camera_button)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.camera_button)).perform(ViewActions.click());
    }

}