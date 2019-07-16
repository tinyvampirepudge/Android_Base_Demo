package com.tiny.demo.firstlinecode.kfysts.chapter03.eventview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2019-07-10 12:16
 * @Version TODO
 */
public class EventViewGroupB extends LinearLayout {
    public static final String TAG = EventViewGroupB.class.getSimpleName();
    public EventViewGroupB(Context context) {
        super(context);
    }

    public EventViewGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventViewGroupB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EventViewGroupB(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "dispatchTouchEvent ");
        boolean result = super.dispatchTouchEvent(event);
        LogUtils.e(TAG, "dispatchTouchEvent result:" + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "onInterceptTouchEvent ");
        boolean result = super.onInterceptTouchEvent(event);
        LogUtils.e(TAG, "onInterceptTouchEvent result:" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "onTouchEvent ");
        boolean result = super.onTouchEvent(event);
        LogUtils.e(TAG, "onTouchEvent result:" + result);
        return result;
    }
}
