package com.tiny.demo.firstlinecode.view.drag;

import android.os.Bundle;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DragActivity3 extends BaseActivity {

    @BindView(R.id.txt_drag_view_group)
    TextView txtDragViewGroup;
    @BindView(R.id.drag_view_group)
    DragViewGroup dragViewGroup;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_drag3;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.txt_drag_view_group)
    public void onTxtDragViewGroupClicked() {
        LogUtils.INSTANCE.e("view clicked");
    }

    @OnClick(R.id.drag_view_group)
    public void onDragViewGroupClicked() {
        LogUtils.INSTANCE.e("viewGroup clicked");
    }
}
