package com.tiny.demo.firstlinecode.ui.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: 绘制分割线的ItemDecoration
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 11:00
 * @Version TODO
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final String TAG = MyDividerItemDecoration.class.getSimpleName();

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        LogUtils.e(TAG, "onDraw");
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        LogUtils.e(TAG, "onDrawOver");
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        LogUtils.e(TAG, "getItemOffsets");
    }
}
