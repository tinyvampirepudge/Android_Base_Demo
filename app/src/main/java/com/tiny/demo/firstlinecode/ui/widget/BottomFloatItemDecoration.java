package com.tiny.demo.firstlinecode.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

/**
 * @Description: 底部悬停
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 17:50
 * @Version
 */
public class BottomFloatItemDecoration extends RecyclerView.ItemDecoration {
    public static final String TAG = BottomFloatItemDecoration.class.getSimpleName();

    int mGroupHeight = 120;


    private TextPaint mTextPaint;
    private Paint mGroupPaint;
    @ColorInt
    private int mGroupTextColor = Color.WHITE;//字体颜色，默认黑色
    private int mSideMargin = 32;   //边距 左边距
    private int mTextSize = 50;     //字体大小


    public BottomFloatItemDecoration(Context context) {
        //设置悬浮栏的画笔---mGroupPaint
        mGroupPaint = new Paint();
        mGroupPaint.setColor(Color.BLUE);
        //设置悬浮栏中文本的画笔
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mGroupTextColor);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // 获取悬停View的高度，这里设置的事50dp
        mGroupHeight = ScreenUtils.dip2px(context, 50);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        if (parent == null || parent.getAdapter() == null || parent.getAdapter().getItemCount() <= 0) {
            return;
        }

        // 获取当前可见的item数量
        final int childCount = parent.getChildCount();

        // 获取最后一个可见的View
        // 先获取最后一个View的相对位置
        int relativeLastPosition = childCount - 1;
        View childView = parent.getChildAt(relativeLastPosition);
        if (childView == null) {
            LogUtils.INSTANCE.e(TAG, "childView == null");
            return;
        }

        // 如果不足一屏，就不用绘制
        if (childView.getBottom() < parent.getHeight()) {
            LogUtils.INSTANCE.e(TAG, "childView.getBottom() < parent.getHeight()");
            return;
        }

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        LogUtils.INSTANCE.e(TAG, "childView.getTop():" + childView.getTop());
        LogUtils.INSTANCE.e(TAG, "childView.getBottom():" + childView.getBottom());
        LogUtils.INSTANCE.e(TAG, "parent.getHeight():" + parent.getHeight());

        int top = childView.getTop();

        // 设置偏移量
        int offset = parent.getHeight() - childView.getTop();
        LogUtils.INSTANCE.e(TAG, "offset:" + offset);

        // 绘制View，通过生成Bitmap来完成。
        View bottomView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rv_bottom_footer, parent, false);
        measureAndLayoutView(bottomView, left, right);
        Bitmap bitmap = Bitmap.createBitmap(bottomView.getDrawingCache());
        // 这里只能向上偏移才会绘制，向下偏移不能超出Item边界。
        canvas.drawBitmap(bitmap, left, top - mGroupHeight + offset, null);
    }

    /**
     * 对view进行测量和布局
     *
     * @param groupView groupView
     * @param left      left
     * @param right     right
     */
    private void measureAndLayoutView(View groupView, int left, int right) {
        groupView.setDrawingCacheEnabled(true);
        //手动对view进行测量，指定groupView的高度、宽度
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(right, mGroupHeight);
        groupView.setLayoutParams(layoutParams);
        groupView.measure(
                View.MeasureSpec.makeMeasureSpec(right, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(mGroupHeight, View.MeasureSpec.EXACTLY));
        groupView.layout(left, 0 - mGroupHeight, right, 0);
    }

    private void drawDecoration(Canvas c, int left, int right, int bottom) {

        //根据top绘制group背景
        c.drawRect(left, bottom - mGroupHeight, right, bottom, mGroupPaint);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        //文字竖直居中显示
        float baseLine = bottom - (mGroupHeight - (fm.bottom - fm.top)) / 2 - fm.bottom;
        //获取文字宽度
        mSideMargin = Math.abs(mSideMargin);
        c.drawText("猫了个咪", left + mSideMargin, baseLine, mTextPaint);
    }

}
