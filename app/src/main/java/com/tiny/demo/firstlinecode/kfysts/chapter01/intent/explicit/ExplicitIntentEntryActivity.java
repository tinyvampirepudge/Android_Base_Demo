package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.explicit;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.test.view.TestActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 显式Intent启动实例
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 09:19
 * @Version TODO
 */
public class ExplicitIntentEntryActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ExplicitIntentEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent_entry);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_intent_test1)
    public void onBtnIntentTest1Clicked() {
        // 显式Intent启动Activity——Intent构造方法
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test2)
    public void onBtnIntentTest2Clicked() {
        // 显式Intent启动Activity
        ComponentName componentName = new ComponentName(this, TestActivity.class);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test3)
    public void onBtnIntentTest3Clicked() {
        // 显式Intent启动Activity
        Intent intent = new Intent();
        intent.setClass(this, TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test4)
    public void onBtnIntentTest4Clicked() {
        Intent intent = new Intent();
        //context, String
        intent.setClassName(this, "com.tiny.demo.firstlinecode.test.view.TestActivity");
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test5)
    public void onBtnIntentTest5Clicked() {
        Intent intent = new Intent();
        //String, String
        intent.setClassName("com.tiny.demo.firstlinecode", "com.tiny.demo.firstlinecode.test.view.TestActivity");
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test6)
    public void onBtnIntentTest6Clicked() {
        // 启动其他应用的Activity，目标Activity需要设置android:exported="true"
        Intent intent = new Intent();
        //String, String
        intent.setClassName("com.tinytongtong.dividerviewdemo", "com.tinytongtong.dividerviewdemo.TestExplicitIntentActivity");
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test7)
    public void onBtnIntentTest7Clicked() {
        // 启动其他应用的Activity，目标Activity需要设置一个不相关的Intent-Filter
        Intent intent = new Intent();
        //String, String
        intent.setClassName("com.tinytongtong.dividerviewdemo", "com.tinytongtong.dividerviewdemo.TestExplicitIntent1Activity");
        startActivity(intent);
    }
}
