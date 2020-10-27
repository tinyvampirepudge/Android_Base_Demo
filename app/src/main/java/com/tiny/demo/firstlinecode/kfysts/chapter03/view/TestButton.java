package com.tiny.demo.firstlinecode.kfysts.chapter03.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:    用于测试VelocityTracker
 *
 * @author tiny
 * @date 2018/3/19 下午9:23
 */

public class TestButton extends android.support.v7.widget.AppCompatTextView {
    public TestButton(Context context) {
        super(context);
    }

    public TestButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //在onTouchEvent方法中可获取追踪当前单击事件的速度
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        //当我们先知道当前的滑动速度时，这个时候可以采用如下方法来获得当前的速度：
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();
        LogUtils.INSTANCE.e("xVelocity --> " + xVelocity);
        LogUtils.INSTANCE.e("yVelocity --> " + yVelocity);
        //使用完毕需要回收
        velocityTracker.clear();
        velocityTracker.recycle();
        return super.onTouchEvent(event);
    }
}
