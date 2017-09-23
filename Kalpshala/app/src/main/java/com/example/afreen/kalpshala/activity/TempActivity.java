package com.example.afreen.kalpshala.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.fragment.HomeFragment;
import com.example.afreen.kalpshala.fragment.OneFragment;

import java.util.ArrayList;
import java.util.List;

public class TempActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    /* private int[] tabIcons = {
             R.drawable.question,
             R.drawable.question,
             R.drawable.question,
             R.drawable.question
             http://android4beginners.com/2013/07/appendix-c-everything-about-sizes-and-dimensions-in-android/
     };*/

    protected int setLayout() {
        return R.layout.schoolnew;
    }


    protected void initComponents() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        if (ContextCompat.checkSelfPermission(TempActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TempActivity.this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 0);
        }

        viewPager.beginFakeDrag();

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {



            }
        }
    }
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custome_tab, null);
        tabOne.setText("Home");
      //  tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.homeselecter, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custome_tab, null);
        tabTwo.setText("Activity");
      //  tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.activityselecter, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custome_tab, null);
        tabThree.setText("Settings");
       // tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.settingsselecter, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custome_tab, null);
        tabFour.setText("Help");
       // tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.helpselecter, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }
    /*@Override
    protected void prepareView() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int intPosition = tab.getPosition();
                Log.i(TAG, "onTabSelected: position >> " +  intPosition);
                if(intPosition == 1) {
                    ActivitiyFragment.fetchDataFromDatabase();
                    HomeFragment.setflashstateoff(intPosition);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    protected void setActionListener() {

    }*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "TWO");
        adapter.addFrag(new OneFragment(), "THREE");

        adapter.addFrag(new OneFragment(), "FOUR");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

