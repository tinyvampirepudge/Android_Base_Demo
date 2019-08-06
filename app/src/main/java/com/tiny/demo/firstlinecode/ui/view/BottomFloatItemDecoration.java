package com.tiny.demo.firstlinecode.ui.view;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
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
        if (bottomView != null) {
            LogUtils.e(TAG, "bottomView.getTop(): " + bottomView.getTop());
            LogUtils.e(TAG, "bottomView.getHeight(): " + bottomView.getHeight());
            //给画布设置偏移量，避免重叠。
            canvas.translate(0, 200);
            bottomView.draw(canvas);
        }

    }

}
