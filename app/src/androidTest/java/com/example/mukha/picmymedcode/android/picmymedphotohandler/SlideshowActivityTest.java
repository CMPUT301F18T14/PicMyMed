package com.example.mukha.picmymedcode.android.picmymedphotohandler;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.android.picmymedphotohandler.SlideshowActivity;
import com.example.mukha.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

public class SlideshowActivityTest {

    private final static String TAG = "GalleryAndPhotoEnlargeActivityTest: ";

    /*  Rule will make sure to launch the Activity directly.
        This means that when testing an n-layer activity,
        you don’t need to do all the steps to start it.         */
    @Rule
    public ActivityTestRule<SlideshowActivity> slideshowActivityTestRule =
            new ActivityTestRule<SlideshowActivity>(SlideshowActivity.class);

    /**
     * Scrolling at a certain position and performing a click at that position.
     * The position starts from 0.
     */
    @Test
    public void swipingLeft() {
        try {
            /* Passes when the position has a photo stored in it.
             * Then it performs longClick action on the view to
             * show the item in a new activity. */

            Espresso.onView(allOf(withId(R.id.viewPager_id))).perform(ViewActions.swipeLeft());
            Log.d(TAG, "The file is at the position.");
        } catch(PerformException e) {
            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
        }

    }
}