package com.tiny.demo.firstlinecode.view.drag;

import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

public class DragActivity5 extends BaseActivity {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_drag5;
    }

    @Override
    protected void buildContentView() {
        findViewById(R.id.view_drager_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.INSTANCE.e("view_drager_layout clicked");
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
