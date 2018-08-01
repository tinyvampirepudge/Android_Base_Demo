package com.tiny.demo.firstlinecode.test.view.floating;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.tiny.demo.firstlinecode.R;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/4/1 下午4:04
 */

public class NestedScrollParentLayout extends RelativeLayout implements NestedScrollingParent{
    private NestedScrollingParentHelper mParentHelper;
    private int mTitleHeight;
    private View mTitleTabView;

    public NestedScrollParentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedScrollParentLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    //获取子view
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitleTabView = this.findViewById(R.id.title_container);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTitleHeight = mTitleTabView.getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec + mTitleHeight);
    }

    //接口实现--------------------------------------------------

    //在此可以判断参数target是哪一个子view以及滚动的方向，然后决定是否要配合其进行嵌套滚动
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        if (target instanceof NestedListView) {
            return true;
        }
        return false;
    }


    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        mParentHelper.onStopNestedScroll(target);
    }

    //先于child滚动
    //前3个为输入参数，最后一个是输出参数
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (dy > 0) {//手势向上滑动
            if (getScrollY() < mTitleHeight) {
                scrollBy(0, dy);//滚动
                consumed[1] = dy;//告诉child我消费了多少
            }
        } else if (dy < 0) {//手势向下滑动
            if (getScrollY() > 0) {
                scrollBy(0, dy);//滚动
                consumed[1] = dy;//告诉child我消费了多少
            }
        }
    }

    //后于child滚动
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    //返回值：是否消费了fling
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    //返回值：是否消费了fling
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
//        if (!consumed) {
//            return true;
//        }
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }


    //scrollBy内部会调用scrollTo
    //限制滚动范围
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTitleHeight) {
            y = mTitleHeight;
        }

        super.scrollTo(x, y);
    }
}
