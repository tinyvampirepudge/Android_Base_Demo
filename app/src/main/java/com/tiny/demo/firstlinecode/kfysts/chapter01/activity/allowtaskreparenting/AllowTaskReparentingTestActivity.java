package com.tiny.demo.firstlinecode.kfysts.chapter01.activity.allowtaskreparenting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 启动模式和AllowTaskReparenting配合使用
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 11:24
 * @Version
 */
public class AllowTaskReparentingTestActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AllowTaskReparentingTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_task_reparenting_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_intent_test8)
    public void onBtnIntentTest8Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.c");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.c");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/abcdefg"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test9)
    public void onBtnIntentTest9Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.d");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.d");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/猫了个咪"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test10)
    public void onBtnIntentTest10Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.e");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.e");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/啊哈哈哈哈"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test11)
    public void onBtnIntentTest11Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.f");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.f");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/阿西吧"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test12)
    public void onBtnIntentTest12Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.g");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.g");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/阿西吧"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test13)
    public void onBtnIntentTest13Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.h");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.h");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/阿西吧"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test14)
    public void onBtnIntentTest14Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.i");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.i");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/阿西吧"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test15)
    public void onBtnIntentTest15Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.j");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.tinytongtong.dividerviewdemo.category.j");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/阿西吧"), "text/plain");
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            LogUtils.INSTANCE.e("match success");
//            startActivity(intent);
//        } else {
//            LogUtils.INSTANCE.e("match failure");
//        }

        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }
}
