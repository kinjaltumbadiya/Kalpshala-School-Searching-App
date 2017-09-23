package com.example.afreen.kalpshala.adapter;

/**
 * Created by Afreen on 6/14/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.afreen.kalpshala.fragment.HomeFragment;
import com.example.afreen.kalpshala.fragment.OneFragment;
import com.example.afreen.kalpshala.fragment.SchoolSecFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new OneFragment();
            case 1:
                // Games fragment activity
                return new SchoolSecFragment();
            case 2:
                // Movies fragment activity
                return new HomeFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}