/*
 * SlideshowActivity
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

package com.example.android.picmymedphotohandler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mukha.picmymedcode.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * SlideshowActivity performs actions on the database and the SlideShowAdapter settings
 * to run a seamless slide show of the photos stored in the device.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.1, 16/11/18
 * @since   1.1
 *
 * Ideas were combined from
 * 1. https://www.youtube.com/watch?v=0U61HP7ZipE
 * 2. https://www.youtube.com/watch?v=DenAOzzxiFY
 * 3. https://github.com/ongakuer/CircleIndicator
 * 4. https://dubedout.eu/2016/09/13/viewpager-basics/
 * Used in: SlideshowActivity.java, SlideShowAdapter.java
 */

public class SlideshowActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ViewPager viewPager;

    private SlideShowAdapter adapter;

    private CircleIndicator indicator;

    private Handler handler;

    private Runnable runnable;

    private Timer timer;

    private ArrayList<GalleryCells> galleryCells;

    private LoadingImageFiles loadingImageFiles;

    private final int DELAY_TIME = 4000;        // The delay time for the handler

    private final int PERIOD_TIME = 4000;       // The period time for the handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        // Load the image files
        loadingImageFiles = new LoadingImageFiles(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES));

//        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
//        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager_id);

        indicator = (CircleIndicator) findViewById(R.id.circleIndicator_id);

        galleryCells = preparedData();

        adapter = new SlideShowAdapter(galleryCells,this);

        viewPager.setAdapter(adapter);

        indicator.setViewPager(viewPager);

        // Initializing the handler
        handler = new Handler();

        // Initializing the runnable task that handler will handle
        runnable = new Runnable() {
            @Override
            public void run() {
                // Getting the current item the pager is on
                int currentItem = viewPager.getCurrentItem();

                if (currentItem == adapter.getCount() - 1){
                    /* If the pager is on the last item, bring to the first item */
                    currentItem = 0;
                    viewPager.setCurrentItem(currentItem, true);
                } else{
                    /* If the pager is not on the last item, take it to the next item*/
                    currentItem ++;
                    viewPager.setCurrentItem(currentItem, true);
                }

            }
        };

        // Initializing the timer
        timer = new Timer();

        // Scheduling task
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    /**
     * This method performs operation on the data
     * to make it viewable under the defined adapter setting.
     *
     * @return      ArrayList of GalleryCells containing modified data for adapter compatibility
     */
    private ArrayList<GalleryCells> preparedData(){
        ArrayList<GalleryCells> imagesModifed = new ArrayList<>();
        ArrayList<Bitmap> bitmaps = loadingImageFiles.convertingToBitmap();

        for(int i = 0; i < bitmaps.size(); i++){
            GalleryCells galleryCells = new GalleryCells();
            galleryCells.setTitle(""+(i + 1));
            galleryCells.setBitmap(bitmaps.get(i));
            imagesModifed.add(galleryCells);
        }
        return imagesModifed;
    }
}
