package com.tiny.demo.firstlinecode.ui.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: 绘制分割线的ItemDecoration
 * https://www.jianshu.com/p/b335b620af39
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 11:00
 * @Version TODO
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final String TAG = MyDividerItemDecoration.class.getSimpleName();

    private int mHeight = 5; //分割线高度
    Paint mPaint;

    public MyDividerItemDecoration() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ef5350"));
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        LogUtils.INSTANCE.e(TAG, "onDraw");
        final int left = parent.getLeft();
        final int right = parent.getRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            final int top = childView.getBottom();
            final int bottom = top + mHeight;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        LogUtils.INSTANCE.e(TAG, "onDrawOver");
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        LogUtils.INSTANCE.e(TAG, "getItemOffsets");
        int position = parent.getChildAdapterPosition(view);
//        if (position != 0) {
            //第一个item预留空间
            outRect.top = mHeight;
//        }
    }
}
