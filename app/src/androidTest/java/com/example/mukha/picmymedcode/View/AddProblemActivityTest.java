package com.example.mukha.picmymedcode.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.mukha.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AddProblemActivityTest {
    @Rule
    public ActivityTestRule<AddProblemActivity> addProblemActivityTestRuleActivity =
            new ActivityTestRule<AddProblemActivity>(AddProblemActivity.class);

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