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

public class CareProviderCommentActivityTest {

    private final static String TAG = "CareProviderProblemActivityTest: ";

    @Rule
    public ActivityTestRule<CareProviderCommentActivity> CareProviderProblemActivityTestRule =
            new ActivityTestRule<CareProviderCommentActivity>  (CareProviderCommentActivity.class) {
                /**
                 * Initializing a patient with a problem before running the activity
                 */
                @Override
                protected void beforeActivityLaunched() {
                    //super.beforeActivityLaunched();
                    LoggedInUserForTesting.LoggedInUserForTestingCare();
                    CareProviderProblemActivity.name = "intenttesting";
                }

                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra("name", "intenttesting");
                    return intent;
                }
            };

    /**
     * Testing add problem button to send an intent to AddProblemActivity
     */

    @Test
    public void TestEmailSending() {
        Espresso.onView(withId(R.id.careprovider_comment_email_text_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }
    @Test
    public void TestPhoneCalling() {
        Espresso.onView(withId(R.id.careprovider_comment_phone_text_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }

}