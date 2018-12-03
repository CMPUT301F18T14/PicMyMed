/*
 * TabSearchActivity
 *
 * 1.2
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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.picmymedcode.R;

import io.searchbox.core.Search;

/**
 * TabSearchActivity handles user searching
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class TabSearchActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    private SearchView searchView;

    /**
     * Creates the state
     *
     * @param savedInstanceState    Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabsearch_activity);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.Tab_Search_Toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Search Records and Problems");

        final Switch geoSwitch = (Switch) findViewById(R.id.GeoSwitch);
        final Switch photoSwitch = (Switch) findViewById(R.id.photoSwitch);
        final SearchView geoSearch = findViewById(R.id.searchGeo);
        final SearchView photoSearch = findViewById(R.id.searchPhoto);



        searchView = findViewById(R.id.searchRecords);

        //searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);

        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Problem"));
        tabLayout.addTab(tabLayout.newTab().setText("Record"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        final TabSearchAdapter adapter = new TabSearchAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        geoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks if geolocation switch is on
             *
             * @param buttonView    CompoundButton
             * @param isChecked     boolean
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //geoSwitch.setVisibility(View.GONE);
                    geoSearch.setVisibility(View.VISIBLE);
                    photoSwitch.setChecked(false);
                }
                if (!isChecked){
//                    photoSearch.setVisibility(View.VISIBLE);
                    geoSearch.setVisibility(View.GONE);
                }
            }
        });

        photoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks if photo search switch is turned on
             *
             * @param buttonView    CompoundButton
             * @param isChecked     boolean
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(TabSearchActivity.this, "Launch Body Location gallery", Toast.LENGTH_SHORT).show();;
                    geoSwitch.setChecked(false);
                }
                if (!isChecked){
                    photoSearch.setVisibility(View.GONE);
                }
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            /**
             * Handles the tab being selected
             *
             * @param tab   TabLayout
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            /**
             * Handles the tab being unselected
             *
             * @param tab   TabLayout
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            /**
             * Handles the tab being selected again
             *
             * @param tab   TabLayout
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
