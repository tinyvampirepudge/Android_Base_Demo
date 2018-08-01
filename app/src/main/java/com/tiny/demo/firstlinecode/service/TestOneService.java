package com.tiny.demo.firstlinecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

public class TestOneService extends Service {
    public static final String TAG = TestOneService.class.getSimpleName();
    private int count = 0;

//    public TestOneService() {
//        LogUtils.e(TAG, "TestOneService Constructor");
//        ThreadUtils.logCurrThreadName(TAG);
//    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.e(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e(TAG, "onCreate");
        ThreadUtils.logCurrThreadName(TAG);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ToastUtils.showSingleToast("onStartCommand");
        LogUtils.e(TAG, "onStartCommand");
        LogUtils.e(TAG, "count --> " + count++);
        ThreadUtils.logCurrThreadName(TAG);
        LogUtils.e(TAG, "startId --> " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "onDestroy");
        ThreadUtils.logCurrThreadName(TAG);
    }
}
