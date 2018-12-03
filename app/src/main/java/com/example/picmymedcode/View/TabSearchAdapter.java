/*
 * TabSearchAdapter
 *
 * 1.2
 *
 * November 16, 2018
 *
 * Copyright (C) 2018 CMPUT301F18T14. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.picmymedcode.View;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * TabSearchAdapter extends FragmentPagerAdapter to handle user searching
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class TabSearchAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    /**
     * Instantiates the class
     *
     * @param context   Context
     * @param fm        FragmentManager
     * @param totalTabs int
     */
    public TabSearchAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    /**
     * Returns the item
     *
     * @param i int
     * @return  Boolean
     */
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                ProblemFragment problemFragment = new ProblemFragment();
                return problemFragment;
            case 1:
                RecordFragment recordFragment = new RecordFragment();
                return recordFragment;
            default:
                return null;
        }
    }

    /**
     * Gets the count
     *
     * @return  totalTabs
     */
    @Override
    public int getCount() {
        return totalTabs;
    }

    // this is for fragment tabs

}
