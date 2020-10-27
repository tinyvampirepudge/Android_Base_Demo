package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: IntentFilter匹配规则实战
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-05 21:31
 * @Version
 */
public class ImplicitIntentEntryActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ImplicitIntentEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_intent_test1)
    public void onBtnIntentTest1Clicked() {
        // 启动当前应用的Activity
        Intent intent = new Intent();
        //action
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit.action.a");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test2)
    public void onBtnIntentTest2Clicked() {
        // 启动其他应用的Activity
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.dividerviewdemo.action.a");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test3)
    public void onBtnIntentTest3Clicked() {
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

    @OnClick(R.id.btn_intent_test4)
    public void onBtnIntentTest4Clicked() {
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

    @OnClick(R.id.btn_intent_test5)
    public void onBtnIntentTest5Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit.action.b");
        intent.addCategory("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit.category.b");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test6)
    public void onBtnIntentTest6Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit.action.c");
        // data，由于目标Activity只设置了mimeType，没有设置URI，所以我们这里需要使用默认uri。
        // 默认uri的scheme为file和content.

        // 下面这段在api大于24的版本上会报错FileUriExposedException，需要将file替换为content
//        intent.setDataAndType(Uri.parse("file://maolegemi"), "image/jpeg");

        intent.setDataAndType(Uri.parse("content://maolegemi"), "image/jpeg");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test7)
    public void onBtnIntentTest7Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit.action.d");
        //data
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/abcdefg"), null);
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_intent_test8)
    public void onBtnIntentTest8Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit.action.e");
        //data
        intent.setDataAndType(Uri.parse("http://www.tiny.com:8080/abcdefg"), "text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }
}
