package com.tiny.demo.firstlinecode.service.intentservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tinytongtong.tinyutils.ThreadUtils;
import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:
 * Created by tiny on 2018/2/12.
 * Time: 16:48
 * Version:
 */

public class TestService extends Service {
    public static final String TAG = "IntentService";

    public TestService() {
        LogUtils.INSTANCE.e(TAG, "TestService构造方法");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.INSTANCE.e(TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.INSTANCE.e(TAG, "Service onStartCommand");
        ThreadUtils.INSTANCE.logCurrThreadName("onStartCommand");
        LogUtils.INSTANCE.e(TAG, "开始睡眠");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.INSTANCE.e(TAG, "睡眠结束");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.INSTANCE.e(TAG, "Service onDestroy");
    }
}
