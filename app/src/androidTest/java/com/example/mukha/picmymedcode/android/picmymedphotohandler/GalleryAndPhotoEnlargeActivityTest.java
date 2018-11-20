/*
 * GalleryAndPhotoEnlargeActivityTest
 *
 * 1.1
 *
 * November 16, 2018
 *
 * Copyright 2018 CMPUT301F18T14. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mukha.picmymedcode.android.picmymedphotohandler;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.android.picmymedphotohandler.GalleryActivity;
import com.example.mukha.picmymedcode.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * GalleryAndPhotoEnlargeActivityTest performs Activity tests using Espresso APKs.
 * This class tests for GalleryActivity and PhotoEnlargementActivity.
 * In this class it tests for how clicking on the specific item on the
 * RecyclerView directs towards a new activity, PhotoEnlargementActivity.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.1, 16/11/18
 * @since   1.1
 *
 * Ideas Combined from the following sources:
 * 1. https://www.youtube.com/watch?v=56xINIkzBy8
 * 2. https://android.jlelse.eu/the-basics-of-android-espresso-testing-activities-fragments-7a8bfbc16dc5
 */
@RunWith(AndroidJUnit4.class)
public class GalleryAndPhotoEnlargeActivityTest {

    private final static String TAG = "GalleryAndPhotoEnlargeActivityTest: ";

    /*  Rule will make sure to launch the Activity directly.
        This means that when testing an n-layer activity,
        you don’t need to do all the steps to start it.         */
    @Rule
    public ActivityTestRule<GalleryActivity> galleryActivityTestRuleActivity =
            new ActivityTestRule<GalleryActivity>(GalleryActivity.class);

    /**
     * Scrolling at a certain position and performing a click at that position.
     * The position starts from 0.
     */
    @Test
    public void scrollToPosition(){

        try {
            /* Passes when the position has a photo stored in it.
             * Then it performs longClick action on the view to
             * show the item in a new activity. */
            Espresso.onView(ViewMatchers.withId(R.id.gallery))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(0, ViewActions.longClick()));
            Log.d(TAG, "The file is at the position.");
        } catch(PerformException e) {
            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
        }

//        try {
//            /* Fail when the position is out of bound, means no photo exists at that position.
//             * It tries to scroll at position 50 and throws an error. */
//            Espresso.onView(ViewMatchers.withId(R.id.gallery))
//                    .perform(RecyclerViewActions
//                            .actionOnItemAtPosition(50, ViewActions.longClick()));
//            Log.d(TAG, "The file is at the position.");
//        } catch(PerformException e) {
//            Log.d(TAG, "Error performing. Nothing exists at that position on the view.");
//        }
    }
}