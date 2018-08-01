package com.tiny.demo.firstlinecode.kfysts.chapter03.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:    GestureDetector测试
 *
 * @author tiny
 * @date 2018/3/20 下午2:50
 */

public class GestureDetectorTestButton extends AppCompatTextView {
    public static final String TAG = "GestureDetector";
    private Context mContext;

    public GestureDetectorTestButton(Context context) {
        super(context);
        init(context);
    }

    public GestureDetectorTestButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureDetectorTestButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetector.OnGestureListener mGestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                LogUtils.e(TAG, "onDown");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                LogUtils.e(TAG, "onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                LogUtils.e(TAG, "onSingleTapUp");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                LogUtils.e(TAG, "onScroll");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                LogUtils.e(TAG, "onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                LogUtils.e(TAG, "onFling");
                return false;
            }
        };
        GestureDetector.OnDoubleTapListener mDoubleTapListener = new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                LogUtils.e(TAG, "onSingleTapConfirmed");
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                LogUtils.e(TAG, "onDoubleTap");
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                LogUtils.e(TAG, "onDoubleTapEvent");
                return false;
            }
        };
        GestureDetector mGestureDetector = new GestureDetector(mContext, mGestureListener);
        //解决长按屏幕后无法拖动的线下
        mGestureDetector.setIsLongpressEnabled(false);
        mGestureDetector.setOnDoubleTapListener(mDoubleTapListener);
        boolean consume = mGestureDetector.onTouchEvent(event);
        return consume;
    }
}
