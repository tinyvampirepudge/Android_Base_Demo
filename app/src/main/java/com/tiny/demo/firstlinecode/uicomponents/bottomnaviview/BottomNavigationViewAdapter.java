package com.tiny.demo.firstlinecode.uicomponents.bottomnaviview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/11/20.
 * Version:
 */

public class BottomNavigationViewAdapter extends FragmentPagerAdapter {
    private List<Bundle> bundles;

    public BottomNavigationViewAdapter(FragmentManager fm, List<Bundle> bundles) {
        super(fm);
        this.bundles = bundles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = BottomNavigationViewFragment.newInstance(bundles.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return bundles == null ? 0 : bundles.size();
    }

}
