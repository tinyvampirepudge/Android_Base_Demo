package com.tiny.demo.firstlinecode.uicomponents.viewpager.pagetransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Desc:    渐变效果
 * Created by tiny on 2018/1/19.
 * Time: 16:53
 * Version:
 * http://blog.csdn.net/lmj623565791/article/details/51339751
 */

public class AlphaPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    @Override
    public void transformPage(View view, float position) {
        if (position < -1) {
            view.setAlpha(mMinAlpha);
        } else if (position <= 1) { // [-1,1]

            if (position < 0) //[0，-1]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                view.setAlpha(factor);
            } else//[1，0]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                view.setAlpha(factor);
            }
        } else { // (1,+Infinity]
            view.setAlpha(mMinAlpha);
        }
    }
}
