package com.example.cmput301f18t14.picmymed.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.cmput301f18t14.picmymed.Controller.PicMyMedApplication;
import com.example.cmput301f18t14.picmymed.Model.Patient;
import com.example.cmput301f18t14.picmymed.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AddProblemActivityTest {

    private final static String TAG = "EditProblemActivityTest: ";

    Patient patient = new Patient("mockuser","adsfa","5656");

//    Problem problem = new Problem("test",new Date(), "mockTitle", "mock description");

    @Rule
    public ActivityTestRule<AddProblemActivity> addProblemActivityTestRuleActivity =
            new ActivityTestRule<AddProblemActivity>(AddProblemActivity.class) {
                /**
                 * Initializing a patient before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    PicMyMedApplication picMyMedApplication = new PicMyMedApplication();
                    picMyMedApplication.setLoggedInUser(patient);

                }
            };

    @Test
    public void addingProblemDetails(){
        Espresso.onView(withId(R.id.problem_title_edit_text))
                .perform(typeText("Heart Pain"));
        Espresso.onView(withId(R.id.problem_description_edit_text))
                .perform(typeText("There is a excruciating pain in my heart."));

        closeSoftKeyboard();

        Espresso.onView(withId(R.id.problem_save_button)).perform(ViewActions.click());
    }

}