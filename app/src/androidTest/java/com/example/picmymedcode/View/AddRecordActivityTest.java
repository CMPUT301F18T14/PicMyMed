package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.AddRecordActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AddRecordActivityTest {

    private final static String TAG = "EditProblemActivityTest: ";

    @Rule
    public ActivityTestRule<AddRecordActivity> addRecordActivityTestRuleActivity =
            new ActivityTestRule<AddRecordActivity>(AddRecordActivity.class) {
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    LoggedInUserForTesting.LoggedInUserForTesting();
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

        Espresso.closeSoftKeyboard();

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
     * Testing opening a map intent
     */
    @Test
    public void openBodyLocationIntent(){
        Espresso.onView(withId(R.id.bodyLocation)).perform(ViewActions.click());
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