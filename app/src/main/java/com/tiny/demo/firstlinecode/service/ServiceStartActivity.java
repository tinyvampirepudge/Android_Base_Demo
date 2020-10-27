package com.tiny.demo.firstlinecode.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ThreadUtils;
import com.tiny.demo.firstlinecode.R;

/**
 * Desc:    startService 启动的Service的生命周期测试
 * http://blog.csdn.net/imxiangzi/article/details/76039978
 * Created by tiny on 2018/2/12 下午1:58
 * Version:
 */
public class ServiceStartActivity extends AppCompatActivity {
    public static final String TAG = ServiceStartActivity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ServiceStartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_start);

        ThreadUtils.INSTANCE.logCurrThreadName(TAG);
        LogUtils.INSTANCE.e(TAG, "before startService");
        //连续启动Service
        Intent intentOne = new Intent(this, TestOneService.class);
        startService(intentOne);
        Intent intentTwo = new Intent(this, TestOneService.class);
        startService(intentTwo);
        Intent intentThree = new Intent(this, TestOneService.class);
        startService(intentThree);

        //停止Service
        Intent intentFour = new Intent(this, TestOneService.class);
        stopService(intentFour);

        //再次启动Service
        Intent intentFive = new Intent(this, TestOneService.class);
        startService(intentFive);
        stopService(intentFive);
        LogUtils.INSTANCE.e(TAG, "after startService");
    }
}
