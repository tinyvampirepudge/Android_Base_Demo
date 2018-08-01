package com.tiny.demo.firstlinecode.uicomponents.tablayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Desc: TabLayout + ViewPager + Fragment
 * Created by tiny on 2017/11/20.
 * Version:
 */

public class TabLayoutActivity extends BaseActivity {
    @BindView(R.id.title_tabLayout)
    TitleBarLayout titleBarLayout;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_tabLayout)
    ViewPager mPager;
    private String[] titleList;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void buildContentView() {
        titleBarLayout.setTitleBarListener(new OnTitleBarClick() {
            @Override
            public void onLeftClicked(int type, View v) {
                finish();
            }

            @Override
            public void onRightClicked(int type, View v) {

            }
        });

        titleList = getResources().getStringArray(R.array.tabLayoutTitles);
        List<Bundle> bundles = initFragArgs();
        PagerAdapter pagerAdapter = new TabLayoutAdapter(getSupportFragmentManager(), bundles, titleList);
        mPager.setAdapter(pagerAdapter);
        mPager.setOffscreenPageLimit(bundles.size() - 1);//default 1.
        mTabLayout.setupWithViewPager(mPager);
        //选中字体加粗
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private List<Bundle> initFragArgs() {
        List<Bundle> bundles = new ArrayList<>();
        for (int j = 0; j < titleList.length; j++) {
            Bundle bundle = new Bundle();
            bundle.putString("tabLayout", "tabLayout --> " + j);
            bundles.add(bundle);
        }
        return bundles;
    }

    @Override
    protected void initViewData() {

    }

}
