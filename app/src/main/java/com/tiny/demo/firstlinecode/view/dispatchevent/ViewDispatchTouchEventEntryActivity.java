package com.tiny.demo.firstlinecode.view.dispatchevent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;
import com.tiny.demo.firstlinecode.view.dispatchevent.scrollview.HorizontalScrollviewDispatchTouchEventActivity;
import com.tiny.demo.firstlinecode.view.dispatchevent.summary.ViewDispatchTouchEventTestActivity;

/**
 * @Description: View事件分发系列入口
 * @Author wangjianzhou@qding.me
 * @Date 2019-07-26 20:48
 * @Version TODO
 */
public class ViewDispatchTouchEventEntryActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ViewDispatchTouchEventEntryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_view_dispatch_touch_event_entry;
    }

    @Override
    protected void buildContentView() {
        // HorizontalScrollView 和 ScrollView 相关的事件分发
        findViewById(R.id.btn_hsv_dispatch_touch_event).setOnClickListener((view) -> {
            HorizontalScrollviewDispatchTouchEventActivity.actionStart(mContext);
        });

        // View事件分发相关测试
        findViewById(R.id.btn_view_dispatch_touch_event).setOnClickListener(v -> {
            ViewDispatchTouchEventTestActivity.actionStart(mContext);
        });
    }

    @Override
    protected void initViewData() {

    }
}
