package com.tiny.demo.firstlinecode.uicomponents.viewpager.pagetransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Desc:    左右缩放的PageTransformer
 * Created by tiny on 2018/1/19.
 * Time: 17:04
 * Version:
 */

public class ScaleDownPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.9f;
    private float mMinScale = DEFAULT_MIN_SCALE;

    /**
     * Apply a property transformation to the given page.
     *
     * @param view     Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     *                 page position to the right, and -1 is one page position to the left.
     */
    @Override
    public void transformPage(View view, float position) {
        float limitLow = -1;
        float limitMid = 0;
        float limitHigh = 1;

        if (position < limitLow) {
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        } else if (position <= limitHigh) { // [-1,1]

            if (position < limitMid) //[-1,0)
            {
                float factor = mMinScale + (1 - mMinScale) * (1 + position);
                view.setScaleX(factor);
                view.setScaleY(factor);
            } else//[0,1]
            {
                float factor = mMinScale + (1 - mMinScale) * (1 - position);
                view.setScaleX(factor);
                view.setScaleY(factor);
            }
        } else { // (1,+Infinity]
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        }
    }
}
