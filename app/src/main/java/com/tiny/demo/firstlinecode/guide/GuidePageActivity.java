package com.tiny.demo.firstlinecode.guide;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.view.CircleIndicator;

/**
 * Desc:
 * Created by tiny on 2017/10/10.
 * Version:
 */

public class GuidePageActivity extends BaseActivity {
    @Override
    protected int setContentLayout() {
        return R.layout.activity_guide_page;
    }

    @Override
    protected void buildContentView() {
        ViewPager defaultViewpager = (ViewPager) findViewById(R.id.guide_page_viewpager);
        final CircleIndicator indicator = (CircleIndicator) findViewById(R.id.guide_page_indicator);
        GuidePagerAdapter pagerAdapter = new GuidePagerAdapter(getSupportFragmentManager());
        defaultViewpager.setAdapter(pagerAdapter);
        indicator.setViewPager(defaultViewpager);
        //最后一页隐藏
        defaultViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    indicator.setVisibility(View.GONE);
                } else {
                    indicator.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
