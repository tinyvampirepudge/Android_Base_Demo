package com.tiny.demo.firstlinecode.service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tinytongtong.tinyutils.ThreadUtils;
import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:    IntentService和Service的区别
 * Created by tiny on 2018/2/12.
 * Time: 16:43
 * Version:
 */

public class TestIntentService extends IntentService {
    public static final String TAG = "IntentService";

    public TestIntentService() {
        super("IntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LogUtils.INSTANCE.e(TAG, "IntentService onHandleIntent");
        ThreadUtils.INSTANCE.logCurrThreadName(TAG);
        LogUtils.INSTANCE.e("IntentService 开始睡眠");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.INSTANCE.e("IntentService 睡眠结束");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.INSTANCE.e(TAG, "IntentService onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.INSTANCE.e(TAG, "IntentService onDestroy");
    }
}
