package com.tiny.demo.firstlinecode.uicomponents.bottomnaviview;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Desc:
 * Created by tiny on 2017/11/20.
 * Version:
 */

public class BottomNavigationViewActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_bottom_navigation_view;
    }

    @Override
    protected void buildContentView() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.item_home1:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.item_home2:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.item_home3:
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.item_home4:
                            viewPager.setCurrentItem(3);
                            break;
                        case R.id.item_home5:
                            viewPager.setCurrentItem(4);
                            break;
                    }
                    return false;
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        List<Bundle> bundles = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Bundle bundle = new Bundle();
            bundle.putString("BottomNavigationView", "BottomNavigationView --> " + j);
            bundles.add(bundle);
        }

        BottomNavigationViewAdapter adapter = new BottomNavigationViewAdapter(getSupportFragmentManager(), bundles);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initViewData() {

    }

}
