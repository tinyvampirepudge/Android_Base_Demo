package com.tiny.demo.firstlinecode.kfysts.chapter03.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.nineoldandroids.view.ViewHelper;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/3/21 上午9:52
 */

public class AnimationTestView extends AppCompatTextView {
    public AnimationTestView(Context context) {
        super(context);
    }

    public AnimationTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                LogUtils.e("move, deltaX:" + deltaX + " deltaY:" + deltaY);
                int translationX = (int) (ViewHelper.getTranslationX(this) + deltaX);
                int translationY = (int) (ViewHelper.getTranslationY(this) + deltaY);
                ViewHelper.setTranslationX(this, translationX);
                ViewHelper.setTranslationY(this, translationY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }
}
