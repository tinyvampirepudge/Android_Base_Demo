package com.tiny.demo.firstlinecode.kfysts.chapter02.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc: Binder线程池
 *
 * @author tiny
 * @date 2018/3/18 下午8:06
 */
public class BinderPoolService extends Service {
    public static final String TAG = "BinderPoolService";

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    public BinderPoolService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.e(TAG, "onBind");
        return mBinderPool;
    }

    @Override
    public void onDestroy() {
        LogUtils.e(TAG, "onDestroy");
        super.onDestroy();
    }
}
