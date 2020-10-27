package com.tiny.demo.firstlinecode.kfysts.chapter03.eventview;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: 继承自TextView
 * @Author wangjianzhou@qding.me
 * @Date 2019-07-10 12:07
 * @Version TODO
 */
public class EventViewB extends AppCompatTextView {

    public static final String TAG = EventViewB.class.getSimpleName();

    public EventViewB(Context context) {
        super(context);
    }

    public EventViewB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventViewB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
