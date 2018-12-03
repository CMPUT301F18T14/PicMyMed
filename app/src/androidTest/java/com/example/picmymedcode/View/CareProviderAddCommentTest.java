package com.example.picmymedcode.View;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class CareProviderAddCommentTest {
    private final static String TAG = "CareProviderAddComment: ";

    @Rule
    public ActivityTestRule<CareProviderAddComment> careProviderAddCommentActivityTestRule =
            new ActivityTestRule<CareProviderAddComment>  (CareProviderAddComment.class) {
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    LoggedInUserForTesting.LoggedInUserForTestingCare();
                    CareProviderProblemActivity.name = "intenttesting";  // Very bad practice
                }

                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra("key2", 0);
                    return intent;
                }
            };

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */
    @Test
    public void testAddComment() {
        String comment = "See me in my chamber!";
        Espresso.onView(withId(R.id.comment_edit_text)).perform(ViewActions.clearText(), ViewActions.typeText(comment));
    }

}