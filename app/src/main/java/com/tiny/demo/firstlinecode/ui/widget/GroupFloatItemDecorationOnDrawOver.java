package com.tiny.demo.firstlinecode.ui.widget;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * @Description: 分组悬停，复用Item的View
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 15:50
 * @Version
 */
public class GroupFloatItemDecorationOnDrawOver extends RecyclerView.ItemDecoration {
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
