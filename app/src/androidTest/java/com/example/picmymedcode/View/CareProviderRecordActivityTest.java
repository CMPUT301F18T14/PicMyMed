package com.example.picmymedcode.View;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class CareProviderRecordActivityTest {
    private final static String TAG = "CareProviderRecordActivityTest: ";

    @Rule
    public ActivityTestRule<CareProviderRecordActivity> CareProviderRecordActivityTestRule =
            new ActivityTestRule<CareProviderRecordActivity>  (CareProviderRecordActivity.class) {
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
        Espresso.onView(withId(R.id.careprovider_record_email_text_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }
    @Test
    public void TestPhoneCalling() {
        Espresso.onView(withId(R.id.careprovider_record_phone_text_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }

    @Test
    public void TestClickingAddComment() {
        Espresso.onView(withId(R.id.add_comment_image_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }

    @Test
    public void TestClickingViewComment() {
        Espresso.onView(withId(R.id.careprovider_view_comment_image_view)).perform(ViewActions.click());
        // Will be implemented in project 5
    }

}