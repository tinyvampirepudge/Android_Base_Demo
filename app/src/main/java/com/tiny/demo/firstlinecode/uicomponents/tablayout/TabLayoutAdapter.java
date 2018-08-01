package com.tiny.demo.firstlinecode.uicomponents.tablayout;


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

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private List<Bundle> bundles;
    private String[] titleList;

    public TabLayoutAdapter(FragmentManager fm, List<Bundle> bundles, String[] titleList) {
        super(fm);
        this.bundles = bundles;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = TabLayoutFragment.newInstance(bundles.get(0));
                break;
            case 1:
                fragment = TabLayoutFragment.newInstance(bundles.get(1));
                break;
            case 2:
                fragment = TabLayoutFragment.newInstance(bundles.get(2));
                break;
            case 3:
                fragment = TabLayoutFragment.newInstance(bundles.get(3));
                break;
            case 4:
                fragment = TabLayoutFragment.newInstance(bundles.get(4));
                break;
            case 5:
                fragment = TabLayoutFragment.newInstance(bundles.get(5));
                break;
            case 6:
                fragment = TabLayoutFragment.newInstance(bundles.get(6));
                break;
            default:
                fragment = TabLayoutFragment.newInstance(bundles.get(0));
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return bundles == null ? 0 : bundles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }
}
