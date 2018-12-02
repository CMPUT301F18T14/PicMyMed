package com.example.picmymedcode.View;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabSearchAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public TabSearchAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

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

    @Override
    public int getCount() {
        return totalTabs;
    }

    // this is for fragment tabs

}
