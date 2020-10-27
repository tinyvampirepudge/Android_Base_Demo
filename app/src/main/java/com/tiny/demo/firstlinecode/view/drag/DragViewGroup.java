package com.tiny.demo.firstlinecode.view.drag;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

/**
 * Desc:
 * Created by tiny on 2017/9/26.
 * Version:
 * http://blog.csdn.net/yingtian648/article/details/52751061
 */

public class DragViewGroup extends RelativeLayout {
    private int lastX, lastY, screenWidth, screenHeight;

    public DragViewGroup(Context context) {
        this(context, null);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - ScreenUtils.dip2px(context, 100);//减去下边的高度
    }

    //定位
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //可以在这里确定这个viewGroup的：宽 = r-l.高 = b - t
    }

    //拦截touch事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) ev.getRawX();//设定移动的初始位置相对位置
                lastY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                //event.getRawX()事件点距离屏幕左上角的距离
                int dx = (int) ev.getRawX() - lastX;
                int dy = (int) ev.getRawY() - lastY;

                int left = this.getLeft() + dx;
                int top = this.getTop() + dy;
                int right = this.getRight() + dx;
                int bottom = this.getBottom() + dy;
                if (left < 0) { //最左边
                    left = 0;
                    right = left + this.getWidth();
                }
                if (right > screenWidth) { //最右边
                    right = screenWidth;
                    left = right - this.getWidth();
                }
                if (top < 0) {  //最上边
                    top = 0;
                    bottom = top + this.getHeight();
                }
                if (bottom > screenHeight) {//最下边
                    bottom = screenHeight;
                    top = bottom - this.getHeight();
                }
                this.layout(left, top, right, bottom);//设置控件的新位置
                LogUtils.INSTANCE.e("position:" + left + ", " + top + ", " + right + ", " + bottom);
                lastX = (int) ev.getRawX();//再次将滑动其实位置定位
                lastY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

}
