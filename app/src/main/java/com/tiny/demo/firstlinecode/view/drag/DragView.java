package com.tiny.demo.firstlinecode.view.drag;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;
import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:
 * Created by tiny on 2017/9/26.
 * Version:
 */

public class DragView extends RelativeLayout {
    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private float resetDeltaX;
    private float resetDeltaY;
    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        LogUtils.INSTANCE.e("event.getRawX() --> " + event.getRawX());
        LogUtils.INSTANCE.e("event.getRawY() --> " + event.getRawY());
        LogUtils.INSTANCE.e("event.getX() --> " + event.getX());
        LogUtils.INSTANCE.e("event.getY() --> " + event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                int translationX = (int) (ViewHelper.getTranslationX(this) + deltaX);
                int translationY = (int) (ViewHelper.getTranslationY(this) + deltaY);
                ViewHelper.setTranslationX(this, translationX);
                ViewHelper.setTranslationY(this, translationY);
                LogUtils.INSTANCE.e("translationX --> " + translationX);
                LogUtils.INSTANCE.e("translationY --> " + translationY);
                resetDeltaX += deltaX;
                resetDeltaY += deltaY;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.onTouchEvent(event);
    }

    public void resetPosition() {
        ViewHelper.setTranslationX(this, resetDeltaX);
        ViewHelper.setTranslationY(this, resetDeltaY);
        resetDeltaX = 0;
        resetDeltaY = 0;
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }
}
