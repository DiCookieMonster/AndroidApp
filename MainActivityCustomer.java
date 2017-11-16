package com.example.user.juneyipuser.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.user.juneyipuser.BaseActivity;
import com.example.user.juneyipuser.Order.NewOrderActivity;
import com.example.user.juneyipuser.Order.editOrder;
import com.example.user.juneyipuser.R;
import com.example.user.juneyipuser.login.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by User on 11/3/2016.
 */

public class MainActivityCustomer extends BaseActivity {

    private static final String TAG = "MainActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new CurrentOrder(),
                    new MyOrders(),
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.heading_current),
                    getString(R.string.heading_my_orders),
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

        // Button launches NewPostActivity
        findViewById(R.id.fab_new_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityCustomer.this, NewOrderActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(this, updateProfile.class));
            return true;
        }

        if (i == R.id.action_edit_order) {
            startActivity(new Intent(this, editOrder.class));
            return true;
        }

        else {
            return super.onOptionsItemSelected(item);
        }
    }


}
