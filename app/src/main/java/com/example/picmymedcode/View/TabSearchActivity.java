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

public class TabSearchActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabsearch_activity);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.Tab_Search_Toolbar);
        setSupportActionBar(toolbar);
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
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
