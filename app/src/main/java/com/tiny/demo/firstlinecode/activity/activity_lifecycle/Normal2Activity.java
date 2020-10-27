package com.tiny.demo.firstlinecode.activity.activity_lifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

public class Normal2Activity extends BaseActivity {
    private static final String TAG = "Normal2Activity";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LogUtils.INSTANCE.e(TAG, "onCreate savedInstanceState");
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_normal2;
    }

    @Override
    protected void buildContentView() {
        LogUtils.INSTANCE.e(TAG, "onCreate");
    }

    @Override
    protected void initViewData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.INSTANCE.e(TAG, "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.INSTANCE.e(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.INSTANCE.e(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.INSTANCE.e(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.INSTANCE.e(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.INSTANCE.e(TAG, "onRestart");
    }

}
