package com.tiny.demo.firstlinecode.test.view.floating;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/4/1 下午4:06
 */

public class NestedListView extends ListView implements NestedScrollingChild{
    private NestedScrollingChildHelper mChildHelper;
    private int[] mNestedOffsets = new int[2];
    private int[] mScrollConsumed = new int[2];
    private int[] mScrollOffset = new int[2];
    private int mScrollPointerId;
    private int mLastTouchX;
    private int mLastTouchY;
    private final static String TAG = "NestedListView";

    public NestedListView(Context context) {
        super(context);
        init();
    }

    public NestedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NestedListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private boolean isFirst = true;//DOWN事件没执行暂时
    private int lastDy;//暂时解决第一次MOVE与后序符号相反，导致的抖动问题

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //下述代码主要复制于RecyclerView
        final MotionEvent vtev = MotionEvent.obtain(e);
        final int action = MotionEventCompat.getActionMasked(e);
        final int actionIndex = MotionEventCompat.getActionIndex(e);
        if (action == MotionEvent.ACTION_DOWN) {
            mNestedOffsets[0] = mNestedOffsets[1] = 0;
        }
        vtev.offsetLocation(mNestedOffsets[0], mNestedOffsets[1]);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                //不知道为啥没有执行
                resetScroll(e);
            }
            break;

            case MotionEventCompat.ACTION_POINTER_DOWN: {
                mScrollPointerId = MotionEventCompat.getPointerId(e, actionIndex);
                mLastTouchX = (int) (MotionEventCompat.getX(e, actionIndex) + 0.5f);
                mLastTouchY = (int) (MotionEventCompat.getY(e, actionIndex) + 0.5f);
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                final int index = MotionEventCompat.findPointerIndex(e, mScrollPointerId);
                if (index < 0) {
                    Log.e(TAG, "Error processing scroll; pointer index for id " +
                            mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                    return false;
                }

                final int x = (int) (MotionEventCompat.getX(e, index) + 0.5f);
                final int y = (int) (MotionEventCompat.getY(e, index) + 0.5f);
                int dx = mLastTouchX - x;
                int dy = mLastTouchY - y;
                if (isFirst) {//暂时解决第次dy与后序符号相反导致的闪动问题
                    Log.i("pyt", "FIRST");
                    isFirst = false;
                    resetScroll(e);
                    return true;
                }
                if (!isSignOpposite(lastDy, dy)) {//解决手机触摸在屏幕上不松开一直抖动的问题
                    lastDy = dy;
                    Log.i("pyt", "move lastY" + mLastTouchY + ",y=" + y + ",dy=" + dy);
                    if (dispatchNestedPreScroll(dx, dy, mScrollConsumed, mScrollOffset)) {
                        vtev.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
                        // Updated the nested offsets
                        mNestedOffsets[0] += mScrollOffset[0];
                        mNestedOffsets[1] += mScrollOffset[1];
                    }
                    mLastTouchX = x - mScrollOffset[0];
                    mLastTouchY = y - mScrollOffset[1];
                }
            }
            break;
            case MotionEvent.ACTION_UP: {
                stopNestedScroll();
//                resetTouch();
                isFirst = true;
            }
            break;

            case MotionEvent.ACTION_CANCEL: {
//                cancelTouch();
            }
            break;
        }
        super.onTouchEvent(e);
        return true;
    }

    private void resetScroll(MotionEvent e) {
        lastDy = 0;
        mNestedOffsets[0] = mNestedOffsets[1] = 0;
        mScrollPointerId = MotionEventCompat.getPointerId(e, 0);
        mLastTouchX = (int) (e.getX() + 0.5f);
        mLastTouchY = (int) (e.getY() + 0.5f);
        int nestedScrollAxis = ViewCompat.SCROLL_AXIS_NONE;
        nestedScrollAxis |= ViewCompat.SCROLL_AXIS_VERTICAL;
        startNestedScroll(nestedScrollAxis);
    }

    /**
     * 判断符号是否相反，可以改成异或
     *
     * @param f
     * @param s
     * @return
     */
    private boolean isSignOpposite(int f, int s) {
        if (f > 0 && s < 0 || f < 0 && s > 0) {
            return true;
        }
        return false;
    }

    //以下为接口实现--------------------------------------------------

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
