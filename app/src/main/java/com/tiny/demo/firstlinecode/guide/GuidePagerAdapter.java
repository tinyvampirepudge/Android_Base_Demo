package com.tiny.demo.firstlinecode.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GuidePagerAdapter extends FragmentPagerAdapter {
    private int pagerCount = 3;

    public GuidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return GuidePageFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return pagerCount;
    }
}