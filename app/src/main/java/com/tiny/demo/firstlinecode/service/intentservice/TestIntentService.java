package com.tiny.demo.firstlinecode.service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

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
        LogUtils.e(TAG, "IntentService onHandleIntent");
        ThreadUtils.logCurrThreadName(TAG);
        LogUtils.e("IntentService 开始睡眠");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.e("IntentService 睡眠结束");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e(TAG, "IntentService onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "IntentService onDestroy");
    }
}
