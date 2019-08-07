package com.tiny.demo.firstlinecode.ui.view;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 17:50
 * @Version TODO
 */
public class BottomFloatItemDecoration extends RecyclerView.ItemDecoration {
    public static final String TAG = BottomFloatItemDecoration.class.getSimpleName();

    private LinearLayoutManager llm;

    public BottomFloatItemDecoration(LinearLayoutManager llm) {
        this.llm = llm;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        //其实就是获取到每一个可见的位置的item时，执行画顶层悬浮栏
        int firstPosition = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        LogUtils.e(TAG, "onDraw firstPosition:" + firstPosition);

        View childFirst = parent.getChildAt(0);
        RecyclerView.LayoutParams paramsFirst = (RecyclerView.LayoutParams) childFirst.getLayoutParams();
        int firstPosition1 = paramsFirst.getViewLayoutPosition();
        LogUtils.e(TAG, "onDraw firstPosition1:" + firstPosition1);

        int firstCompletelyVisibleItemPosition = ((LinearLayoutManager) parent.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        LogUtils.e(TAG, "onDraw firstCompletelyVisibleItemPosition:" + firstCompletelyVisibleItemPosition);

        int lastPosition = ((LinearLayoutManager) parent.getLayoutManager()).findLastVisibleItemPosition();
        LogUtils.e(TAG, "onDraw lastPosition:" + lastPosition);

        int childCount = parent.getChildCount();
        View childLast = parent.getChildAt(childCount - 1);
        RecyclerView.LayoutParams paramsLast = (RecyclerView.LayoutParams) childLast.getLayoutParams();
        int lastPosition1 = paramsLast.getViewLayoutPosition();
        LogUtils.e(TAG, "onDraw lastPosition1:" + lastPosition1);

        int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) parent.getLayoutManager()).findLastCompletelyVisibleItemPosition();
        LogUtils.e(TAG, "onDraw lastCompletelyVisibleItemPosition:" + lastCompletelyVisibleItemPosition);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        if (parent == null || parent.getAdapter() == null || parent.getAdapter().getItemCount() <= 0) {
            return;
        }
        // 得到的数据其实是一屏可见的item数量并非总item数
        int childCount = parent.getChildCount();
        LogUtils.e(TAG, "childCount:" + childCount);

        // 获取到最后一个View，绘制在底部
        int size = parent.getAdapter().getItemCount();
        LogUtils.e(TAG, "adapter size: " + size);

        View bottomView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rv_bottom_footer, parent, false);

        bottomView = llm.findViewByPosition(size - 1);
//
//        int viewType = parent.getAdapter().getItemViewType(size - 1);
//        LogUtils.e(TAG, "viewType: " + viewType);

        if (bottomView != null) {
            LogUtils.e(TAG, "bottomView.getTop(): " + bottomView.getTop());
            LogUtils.e(TAG, "bottomView.getHeight(): " + bottomView.getHeight());
            //给画布设置偏移量，避免重叠。
            canvas.translate(0, 0);
            bottomView.draw(canvas);
        }

    }

}
