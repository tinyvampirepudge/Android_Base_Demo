package com.tiny.demo.firstlinecode.handler;

import android.os.Handler;
import android.widget.Button;

import com.tinytongtong.tinyutils.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    Handler相关入口
 * Created by tiny on 2017/10/26.
 * Version:
 */

public class HandlerEntryActivity extends BaseActivity {
    @BindView(R.id.btn_handler)
    Button btnHandler;
    @BindView(R.id.btn_different_handler_communication)
    Button btnHandlerThread;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_handler_entry;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_handler)
    public void onViewClicked() {
        activitySwitch(HandlerActivity.class);
    }

    @OnClick(R.id.btn_different_handler_communication)
    public void onDifferentHandlerCommunicationClicked() {
        activitySwitch(DifferentThreadCommunicationActivity.class);
    }

    @OnClick(R.id.btn_handler_thread)
    public void onHandlerThreadClicked() {
        activitySwitch(HandlerThreadActivity.class);
    }

    /**
     * Handler最常见的崩溃
     * java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
     */
    @OnClick(R.id.btn_handler_error)
    public void onHandlerErrorClicked() {
        ThreadUtils.INSTANCE.logCurrThreadName("111");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();

                ThreadUtils.INSTANCE.logCurrThreadName("222");
            }
        }).start();
    }

    @OnClick(R.id.btn_handler_sync_barrier)
    public void onHandlerSybcBarrierClicked() {
        HandlerSyncBarrierActivity.actionStart(this);
    }

}
