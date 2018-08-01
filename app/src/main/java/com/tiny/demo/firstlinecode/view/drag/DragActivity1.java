package com.tiny.demo.firstlinecode.view.drag;

import android.os.Bundle;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DragActivity1 extends BaseActivity {

    @BindView(R.id.drag_view_view_helper)
    DragView dragViewViewHelper;
    @BindView(R.id.btn_reset_pos_drag_view)
    Button btnResetPosDragView;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_drag1;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.drag_view_view_helper)
    public void onViewClicked() {
        LogUtils.e("drag_view_view_helper clicked");
    }

    @OnClick(R.id.btn_reset_pos_drag_view)
    public void onResetClicked() {
        dragViewViewHelper.resetPosition();
    }
}
