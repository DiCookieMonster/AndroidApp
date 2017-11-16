package com.example.user.juneyipuser.HandyMan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.juneyipuser.BaseActivity;
import com.example.user.juneyipuser.R;
import com.example.user.juneyipuser.login.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by User on 11/3/2016.
 */

public class MainActivityHandyman extends BaseActivity {
    private static final String TAG = "MainActivityHandyMan";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hm);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new listedOrders(),
                    new pickedOrders(),
                    new ClosedPerformedJobs()
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.heading_listed_orders),
                    getString(R.string.heading_picked_up),
                    getString(R.string.heading_closed)
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return true;
        }
        if (i == R.id.action_edit_profile) {
            startActivity(new Intent(this, updateProfileHm.class));
            return true;
        }


        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
