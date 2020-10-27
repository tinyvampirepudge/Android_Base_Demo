package com.tiny.demo.firstlinecode.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ServiceActivity extends BaseActivity {
    @BindView(R.id.btn_start_service)
    Button btnStartService;
    @BindView(R.id.btn_stop_service)
    Button btnStopService;
    @BindView(R.id.activity_service)
    ScrollView activityService;
    @BindView(R.id.btn_bind_service)
    Button btnBindService;
    @BindView(R.id.btn_unbind_service)
    Button btnUnbindService;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.INSTANCE.e("ServiceConnection onServiceConnected");
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.INSTANCE.e("ServiceConnection onServiceDisconnected");
        }
    };

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_service;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_start_service, R.id.btn_stop_service, R.id.btn_bind_service, R.id.btn_unbind_service,
            R.id.btn_start_intent_service, R.id.btn_start_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_service:
                Intent startIntent = new Intent(mContext, MyService.class);
                startService(startIntent);
                break;
            case R.id.btn_stop_service:
                Intent stopIntent = new Intent(mContext, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_bind_service:
                Intent bindIntent = new Intent(mContext, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(connection);
                break;
            case R.id.btn_start_intent_service:
                //打印当前线程的id
                LogUtils.INSTANCE.e("Main Thread id is --> " + Thread.currentThread().getId());
                LogUtils.INSTANCE.e("Main Thread id name --> " + Thread.currentThread().getName());
                MyIntentService.startActionFoo(mContext, "hello", "world");
                break;
            case R.id.btn_start_download:
                DownloadActivity.actionStart(mContext);
                break;
        }
    }
}
