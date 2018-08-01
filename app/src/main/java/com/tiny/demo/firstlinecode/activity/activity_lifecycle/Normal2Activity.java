package com.tiny.demo.firstlinecode.activity.activity_lifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

public class Normal2Activity extends BaseActivity {
    private static final String TAG = "Normal2Activity";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LogUtils.e(TAG, "onCreate savedInstanceState");
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_normal2;
    }

    @Override
    protected void buildContentView() {
        LogUtils.e(TAG, "onCreate");
    }

    @Override
    protected void initViewData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e(TAG, "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.e(TAG, "onRestart");
    }

}
