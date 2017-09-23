package com.example.afreen.kalpshala;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.afreen.kalpshala.activity.LoginActivity;
import com.example.afreen.kalpshala.fragment.School;


public class MainActivity extends AppCompatActivity
       implements NavigationView.OnNavigationItemSelectedListener {
       private String fname, lname, email, profile_pic;
       private boolean login_status;
       private TextView txt_name,txt_email;
       SharedPreferences sharedPreferences;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        /*img_profile_pic = (ImageView) header.findViewById(R.id.img_profile_pic)*/;
        txt_name = (TextView) header.findViewById(R.id.txt_name);
        txt_email = (TextView) header.findViewById(R.id.txt_email);

        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        login_status = sharedPreferences.getBoolean("login_status", false);

        if (login_status) {
            fname = sharedPreferences.getString("fname", "");
            lname = sharedPreferences.getString("lname", "");
            email = sharedPreferences.getString("email", "");
            txt_name.setText(fname + " " + lname);
            txt_email.setText(email);

        } else {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            MainActivity.this.finish();
        }


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.schools) {
            School fragment = new School();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.home) {
           /* ProfileSettingsFragments fragment = new ProfileSettingsFragments();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();*/


        } else if (id == R.id.colleges) {
           /* NewsFeedFragment fragment = new NewsFeedFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();*/


        } else if (id == R.id.nav_advisor) {

        } else if (id == R.id.nav_logout) {
          /*  LoginManager.getInstance().logOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                        }
                    });*/
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fname", "");
            editor.putString("lname", "");
            editor.putString("email", "");


            editor.putString("profile_pic", "");
            editor.putBoolean("login_status", false);
            editor.apply();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            MainActivity.this.finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
