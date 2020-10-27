package com.tiny.demo.firstlinecode.view.drag;

import android.os.Bundle;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DragActivity4 extends BaseActivity {

    @BindView(R.id.view_drag_helper)
    View viewDragHelper;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_drag4;
    }

    @Override
    protected void buildContentView() {
        findViewById(R.id.view_drag_helper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.INSTANCE.e("view_drag_helper1");
            }
        });
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.view_drag_helper)
    public void onViewClicked() {
//        LogUtils.INSTANCE.e("view_drag_helper");
    }
}
