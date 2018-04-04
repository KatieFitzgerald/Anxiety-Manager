package com.example.katiefitzgerald.anxietymanager.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.fragments.LocationFragment;
import com.example.katiefitzgerald.anxietymanager.fragments.SubjectFragment;
import com.example.katiefitzgerald.anxietymanager.fragments.ReactionFragment;

/**
 * Created by Katie Fitzgerald on 28/03/2018.
 */

public class InsightActivity extends AppCompatActivity {

    TabLayout tabLayout;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#0042d0"));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return new ReactionFragment();
                case 1:
                    return new LocationFragment();
                case 2:
                    return new SubjectFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabLayout.getTabCount();
        }
    }
}
