package com.tiny.demo.firstlinecode.uicomponents.viewpager.pagetransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Desc:    www.jianshu.com/p/722ece163629
 * Created by tiny on 2018/1/23.
 * Time: 10:48
 * Version:
 */

public class MyPageTransform implements ViewPager.PageTransformer {
    final float SCALE_MAX = 0.8f;
    final float ALPHA_MAX = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        float scale = (position < 0)
                ? ((1 - SCALE_MAX) * position + 1)
                : ((SCALE_MAX - 1) * position + 1);
        float alpha = (position < 0)
                ? ((1 - ALPHA_MAX) * position + 1)
                : ((ALPHA_MAX - 1) * position + 1);
        //为了滑动过程中，page间距不变，这里做了处理
        if (position < 0) {
            page.setPivotX(page.getWidth());
            page.setPivotY(page.getHeight() / 2);
        } else {
            page.setPivotX(0);
            page.setPivotY(page.getHeight() / 2);
        }
        page.setScaleX(scale);
        page.setScaleY(scale);
        page.setAlpha(Math.abs(alpha));
    }
}
