package com.tiny.demo.firstlinecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tinytongtong.tinyutils.ThreadUtils;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

public class TestOneService extends Service {
    public static final String TAG = TestOneService.class.getSimpleName();
    private int count = 0;

//    public TestOneService() {
//        LogUtils.INSTANCE.e(TAG, "TestOneService Constructor");
//        ThreadUtils.INSTANCE.logCurrThreadName(TAG);
//    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.INSTANCE.e(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.INSTANCE.e(TAG, "onCreate");
        ThreadUtils.INSTANCE.logCurrThreadName(TAG);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ToastUtils.showSingleToast("onStartCommand");
        LogUtils.INSTANCE.e(TAG, "onStartCommand");
        LogUtils.INSTANCE.e(TAG, "count --> " + count++);
        ThreadUtils.INSTANCE.logCurrThreadName(TAG);
        LogUtils.INSTANCE.e(TAG, "startId --> " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.INSTANCE.e(TAG, "onDestroy");
        ThreadUtils.INSTANCE.logCurrThreadName(TAG);
    }
}
