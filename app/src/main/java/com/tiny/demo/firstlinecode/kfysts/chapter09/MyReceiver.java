package com.tiny.demo.firstlinecode.kfysts.chapter09;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc: 广播接收者典型实现
 *
 * @author tiny
 * @date 2018/5/17 下午10:49
 */
public class MyReceiver extends BroadcastReceiver {
    public static final String TAG = MyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        // onReceive函数不能做耗时的事情，参考值：10s以内。
        LogUtils.e(TAG, "on receive action=" + intent.getAction());
        String action = intent.getAction();
        // do some works
    }
}
