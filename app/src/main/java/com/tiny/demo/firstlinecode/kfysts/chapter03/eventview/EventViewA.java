package com.tiny.demo.firstlinecode.kfysts.chapter03.eventview;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: 继承自View
 * @Author wangjianzhou@qding.me
 * @Date 2019-07-10 12:07
 * @Version TODO
 */
public class EventViewA extends View {

    public static final String TAG = EventViewA.class.getSimpleName();

    public EventViewA(Context context) {
        super(context);
    }

    public EventViewA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventViewA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EventViewA(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.INSTANCE.e(TAG, "dispatchTouchEvent ");
        boolean result = super.dispatchTouchEvent(event);
        LogUtils.INSTANCE.e(TAG, "dispatchTouchEvent result:" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.INSTANCE.e(TAG, "onTouchEvent ");
        boolean result = super.onTouchEvent(event);
        LogUtils.INSTANCE.e(TAG, "onTouchEvent result:" + result);
        return result;
    }
}
