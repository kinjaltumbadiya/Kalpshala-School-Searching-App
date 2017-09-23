package com.example.afreen.kalpshala.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afreen.kalpshala.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;




public class School extends Fragment {

    View view;
    Adapter adapter;

    private void setupViewPager(ViewPager viewPager) {

        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new OneFragment(), "Primary");
        adapter.addFragment(new HomeFragment(), "Secondary");
        adapter.addFragment(new OneFragment(), "Higher Sec");
        viewPager.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_school, container, false);
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this, view);
        final ViewPager viewPager = ButterKnife.findById(view, R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = ButterKnife.findById(view, R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Toast.makeText(getContext(),""+tab.getPosition(), Toast.LENGTH_LONG).show();
                /*if(tab.getPosition()==0)
                     OneFragment.getSchoolPrimaryDetailFromServer();*/


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //ButterKnife.unbind(this);
    }

     class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
