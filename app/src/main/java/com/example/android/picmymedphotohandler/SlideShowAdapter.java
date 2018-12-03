/*
 * SlideshowActivity
 *
 * 1.2
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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.picmymedcode.R;

import java.util.ArrayList;

/**
 * This class is an adapter for SlideshowActivity to show the images using ViewPager.
 * This class inflates the slideshow_cell.xml file to correctly position the images loaded
 * from the internal disk space.
 *
 * @author  Md Touhidul (Apu) Islam
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class SlideShowAdapter extends PagerAdapter {

    private ArrayList<GalleryCells> galleryCells;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Constructor for this class. It initializes two member variables.
     *
     * @param galleryCells  An ArrayList of GalleryCells
     * @param context       The activity context of the class that will use/call this adapter class.
     */
    public SlideShowAdapter(ArrayList<GalleryCells> galleryCells, Context context) {
        this.galleryCells = galleryCells;
        this.context = context;
    }

    /**
     * This method returns the size of the ArrayList of type GallerCells
     * which in turns represents the number of data being presented by this adapter.
     * It lets the ViewPager know how much item to display.
     *
     * @return      An integer of number for the size of GallerCells typed ArrayList
     */
    @Override
    public int getCount() {
        return galleryCells.size();
    }

    /**
     * "In the instantiateItem method we return an object.
     * Here we compare it to the view added via the inflator."
     *
     * @param view      View to be compared
     * @param o         Object to be compared after type cast with appropriate view
     * @return          Returns a boolean value if the object is a view of particular type
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (LinearLayout)o);
    }

    /**
     * Creating the view that will be displayed at the particular position.
     * This method inflates the slideshow_cell.xml layout.
     *
     * @param container     The ViewGroup that will contain the view
     * @param position      The position where the view will be displayed
     * @return              Returns a view drawn with the appropriate data
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slideshow_cell, container, false);

        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView_id);

        imageView.setImageBitmap(galleryCells.get(position).getBitmap());

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        // Add the view to the container of ViewGroup
        container.addView(view);
        return view;
    }

    /**
     * "When the item is not in the currently displayed page nor in the buffer ones, we remove it."
     *
     * @param container     The ViewGroup containing the view
     * @param position      The position where the view was displayed
     * @param object        The object that will be deleted after type casting with appropriate view
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }


}
