package com.tiny.demo.firstlinecode.kfysts.chapter01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.kfysts.chapter01.activity.allowtaskreparenting.AllowTaskReparentingTestActivity;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    启动模式入口
 * Created by tiny on 2018/2/24 下午4:03
 * Version:
 */

public class LauncherModeEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_mode_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test0)
    public void onBtnTest0Clicked() {
        AllowTaskReparentingTestActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        startActivity(new Intent(this, LauncherModeSecondActivity.class));
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        Intent intent = new Intent();
        intent.setClass(this, LauncherModeSecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        startActivity(new Intent(this, ActivityStackAActivity.class));
        startActivity(new Intent(this, ActivityStackBActivity.class));
        startActivity(new Intent(this, ActivityStackCActivity.class));
        startActivity(new Intent(this, ActivityStackDActivity.class));
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        Intent intent = new Intent();
        intent.setClass(this, LauncherModeSecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        LogUtils.e("single Top clicked");
        startActivity(new Intent(this, LaunchModeSingleTopTestActivity.class));
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        for (int i = 0; i < 5; i++) {
            startActivity(new Intent(this, LaunchModeSingleTopTestActivity.class));
        }
    }
}
