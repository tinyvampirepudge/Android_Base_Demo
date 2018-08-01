package com.tiny.demo.firstlinecode.kfysts.chapter03.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.Scroller;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/3/20 下午3:33
 */

public class ScrollerTestButton extends AppCompatTextView {
    private Context mContext;
    private Scroller mScroller;

    public ScrollerTestButton(Context context) {
        super(context);
        init(context);
    }

    public ScrollerTestButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerTestButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mScroller = new Scroller(mContext);
    }

    /**
     * 缓慢滚动到指定位置
     *
     * @param destX
     * @param destY
     */
    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        //1000ms内滑动destX, 效果就是慢慢滑动
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
