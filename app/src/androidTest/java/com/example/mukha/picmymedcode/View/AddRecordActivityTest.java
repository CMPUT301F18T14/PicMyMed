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
import static org.junit.Assert.*;

public class AddRecordActivityTest {
    @Rule
    public ActivityTestRule<AddRecordActivity> addRecordActivityTestRuleActivity =
            new ActivityTestRule<AddRecordActivity>(AddRecordActivity.class);

    @Test
    public void addingRecordDetails(){
        Espresso.onView(withId(R.id.record_title_edit_text))
                .perform(typeText("Felt 1"));
        Espresso.onView(withId(R.id.record_description_edit_text))
                .perform(typeText("Felt like something is trying to rip my heart apart."));

        closeSoftKeyboard();

        Espresso.onView(withId(R.id.record_save_button)).perform(ViewActions.click());
    }

}