package com.example.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.R;
import com.example.picmymedcode.View.AddProblemActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AddProblemActivityTest {

    private final static String TAG = "AddProblemActivityTest: ";


    @Rule
    public ActivityTestRule<AddProblemActivity> addProblemActivityTestRuleActivity =
            new ActivityTestRule<AddProblemActivity>(AddProblemActivity.class) {
                /**
                 * Initializing a patient before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    LoggedInUserForTesting.LoggedInUserForTesting();

                }
            };

    @Test
    public void TestAddingProblemDetails(){
        Espresso.onView(withId(R.id.problem_title_edit_text))
                .perform(typeText("Heart Pain 2"));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.problem_description_edit_text))
                .perform(typeText("There is a excruciating pain in my heart 2."));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.problem_save_button)).perform(ViewActions.click());
    }

}