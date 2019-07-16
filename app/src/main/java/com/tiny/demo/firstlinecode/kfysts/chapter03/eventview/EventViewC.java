package com.tiny.demo.firstlinecode.kfysts.chapter03.eventview;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * @Description: 继承自Button
 * @Author wangjianzhou@qding.me
 * @Date 2019-07-10 12:07
 * @Version TODO
 */
public class EventViewC extends AppCompatButton {

    public static final String TAG = EventViewC.class.getSimpleName();

    public EventViewC(Context context) {
        super(context);
    }

    public EventViewC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventViewC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "dispatchTouchEvent ");
        boolean result = super.dispatchTouchEvent(event);
        LogUtils.e(TAG, "dispatchTouchEvent result:" + result);
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
