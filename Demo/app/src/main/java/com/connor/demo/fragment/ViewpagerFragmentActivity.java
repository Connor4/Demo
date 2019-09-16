package com.connor.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import androidx.annotation.Nullable;

import com.connor.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerFragmentActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private DemoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_viewpager_layout);
        initFragment();
        mViewPager = findViewById(R.id.fragment_vp);
        mViewPager.setAdapter(mAdapter);

    }

    private void initFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        mAdapter = new DemoAdapter(getSupportFragmentManager(), list);
    }


    class DemoAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;

        DemoAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
