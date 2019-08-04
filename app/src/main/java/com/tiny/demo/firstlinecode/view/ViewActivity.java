package com.tiny.demo.firstlinecode.view;

import android.content.Intent;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.view.dialog.DialogDemoActivity;
import com.tiny.demo.firstlinecode.view.dispatchevent.ViewDispatchTouchEventEntryActivity;
import com.tiny.demo.firstlinecode.view.dispatchevent.scrollview.HorizontalScrollviewDispatchTouchEventActivity;
import com.tiny.demo.firstlinecode.view.drag.DragActivity;
import com.tiny.demo.firstlinecode.view.drag.DragActivity1;
import com.tiny.demo.firstlinecode.view.drag.DragActivity3;
import com.tiny.demo.firstlinecode.view.drag.DragActivity4;
import com.tiny.demo.firstlinecode.view.drag.DragActivity5;
import com.tiny.demo.firstlinecode.view.saverestore.SaveRestoreEntryActivity;

/**
 * Desc: View 相关，
 * ①view事件分发
 * ②Dialog, Google上的demo
 * ③View Drag and Scale, Google上的demo
 * Created by tiny on 2017/9/21.
 * Version:
 */
public class ViewActivity extends BaseActivity {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_view;
    }

    @Override
    protected void buildContentView() {
        //view事件分发
        findViewById(R.id.btn_view_dispatch_touch_event).setOnClickListener((view) -> {
            ViewDispatchTouchEventEntryActivity.actionStart(mContext);
        });
        //对话框展示相关代码
        findViewById(R.id.btn_dialog).setOnClickListener(v -> startActivity(new Intent(mContext, DialogDemoActivity.class)));
        //View Drag
        findViewById(R.id.btn_view_drag).setOnClickListener(v -> startActivity(new Intent(mContext, DragActivity.class)));

        findViewById(R.id.btn_view_drag1).setOnClickListener(v -> startActivity(new Intent(mContext, DragActivity1.class)));

        findViewById(R.id.btn_view_drag3).setOnClickListener(v -> startActivity(new Intent(mContext, DragActivity3.class)));

        findViewById(R.id.btn_view_drag4).setOnClickListener(v -> startActivity(new Intent(mContext, DragActivity4.class)));

        findViewById(R.id.btn_view_drag5).setOnClickListener(v -> startActivity(new Intent(mContext, DragActivity5.class)));

        findViewById(R.id.btn_view_save_restore).setOnClickListener(v -> SaveRestoreEntryActivity.actionStart(this));
    }

    @Override
    protected void initViewData() {

    }
}
