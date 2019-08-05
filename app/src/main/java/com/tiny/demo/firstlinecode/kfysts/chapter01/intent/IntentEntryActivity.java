package com.tiny.demo.firstlinecode.kfysts.chapter01.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve.ImplicitIntentResolvedActivity;
import com.tiny.demo.firstlinecode.test.view.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    Intent相关的入口
 * Created by tiny on 2018/2/24 上午10:46
 * Version:
 */
public class IntentEntryActivity extends AppCompatActivity {

    @BindView(R.id.btn_intent_test1)
    Button btnIntentTest1;
    @BindView(R.id.btn_intent_test2)
    Button btnIntentTest2;
    @BindView(R.id.btn_intent_test3)
    Button btnIntentTest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_intent_test0)
    public void onBtnIntentTest0Clicked() {
        ImplicitIntentResolvedActivity.actionStart(this);
    }

    @OnClick(R.id.btn_intent_test1)
    public void onBtnIntentTest1Clicked() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test2)
    public void onBtnIntentTest2Clicked() {
        ComponentName componentName = new ComponentName(this, TestActivity.class);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test3)
    public void onBtnIntentTest3Clicked() {
        Intent intent = new Intent();
        intent.setClass(this, TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test4)
    public void onBtnIntentTest4Clicked() {
        Intent intent = new Intent();
        //context, String
//        intent.setClassName(this, "com.tiny.demo.firstlinecode.test.view.TestActivity");
        //String, String
        intent.setClassName("com.tiny.demo.firstlinecode", "com.tiny.demo.firstlinecode.test.view.TestActivity");
        startActivity(intent);
    }

    @OnClick(R.id.btn_intent_test5)
    public void onBtnIntentTest5Clicked() {
        Intent intent = new Intent();
        //action
//        intent.setAction("com.wjz.intent.action.a");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        intent.addCategory("com.wjz.intent.category.a");
        //Data在AndroidManifest.xml中可不添加，相应的，在隐式调用时也不用添加。
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/abcdefg"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test6)
    public void onBtnIntentTest6Clicked() {
        //Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "猫了个咪");
        sendIntent.setType("text/plain");

        //Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    @OnClick(R.id.btn_intent_test7)
    public void onBtnIntentTest7Clicked() {
        //选择器
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "猫了个咪啊");
        sendIntent.setType("text/plain");
        //Always use string resources for UI text.
        //This says something like "Share this photo with"
        String title = getResources().getString(R.string.chooser_title);
        //Create intent to show the chooser dialog
        Intent chooser = Intent.createChooser(sendIntent, title);

        //Verify the original intent will resolve to at least one activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
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
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
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
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
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
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
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
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
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
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
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
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
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
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
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
//            LogUtils.e("match success");
//            startActivity(intent);
//        } else {
//            LogUtils.e("match failure");
//        }

        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
        }
    }
}
