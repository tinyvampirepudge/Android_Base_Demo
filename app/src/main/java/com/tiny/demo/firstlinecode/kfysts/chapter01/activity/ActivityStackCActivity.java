package com.tiny.demo.firstlinecode.kfysts.chapter01.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

public class ActivityStackCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_c);
        LogUtils.e("ActivityStackCActivity", "onCreate");
    }
}
