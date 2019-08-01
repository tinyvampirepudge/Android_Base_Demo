package com.tiny.demo.firstlinecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.tinytongtong.tinyutils.ThreadUtils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.util.Random;

/**
 *
 * Desc:    跟Activity绑定的页面。
 * Created by tiny on 2018/2/12 下午2:40
 * Version:
 */
public class TestTwoService extends Service {
    public static final String TAG = "ServiceBind";

//    public TestTwoService() {
//        LogUtils.e(TAG, "TestTwoService");
//    }

    //client 可以通过Binder获取Service实例
    public class TestTwoBinder extends Binder {
        public TestTwoService getService() {
            return TestTwoService.this;
        }
    }

    //通过binder实现调用者client与Service之间的通信
    private TestTwoBinder binder = new TestTwoBinder();

    private final Random generator = new Random();

    @Override
    public void onCreate() {
        LogUtils.e(TAG, "onCreate");
        ThreadUtils.logCurrThreadName(TAG);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e(TAG, "onStartCommand");
        ThreadUtils.logCurrThreadName(TAG);
//        return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.e(TAG, "onBind");
        ToastUtils.showSingleToast("onBind");
        ThreadUtils.logCurrThreadName(TAG);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.e(TAG, "onUnbind");
        LogUtils.e(TAG, "from --> " + intent.getStringExtra("from"));
        ThreadUtils.logCurrThreadName(TAG);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LogUtils.e(TAG, "onDestroy");
        ThreadUtils.logCurrThreadName(TAG);
        super.onDestroy();
    }

    //getRandomNumber是Service暴露出去供client调用的公共方法
    public int getRandomNumber() {
        return generator.nextInt();
    }
}
