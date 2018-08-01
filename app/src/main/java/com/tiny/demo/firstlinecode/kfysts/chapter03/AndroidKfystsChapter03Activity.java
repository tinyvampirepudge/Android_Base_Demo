package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: Android开发艺术探索 第三章 View事件体系
 *
 * @author tiny
 * @date 2018/3/19 下午9:10
 */
public class AndroidKfystsChapter03Activity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AndroidKfystsChapter03Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter03);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_01)
    public void onBtnTest01Clicked() {
        Chapter0301Activity.actionStart(AndroidKfystsChapter03Activity.this);
    }

    @OnClick(R.id.btn_test_02)
    public void onBtnTest02Clicked() {
        Chapter0302Activity.actionStart(AndroidKfystsChapter03Activity.this);
    }

    @OnClick(R.id.btn_test_03)
    public void onBtnTest03Clicked() {
        Chapter030201Activity.actionStart(AndroidKfystsChapter03Activity.this);
    }

    @OnClick(R.id.btn_test_04)
    public void onBtnTest04Clicked() {
        Chapter0303Activity.actionStart(AndroidKfystsChapter03Activity.this);
    }

    @OnClick(R.id.btn_test_05)
    public void onBtnTest05Clicked() {
        Chapter0304Activity.actionStart(AndroidKfystsChapter03Activity.this);
    }

    @OnClick(R.id.btn_test_06)
    public void onBtnTest06Clicked() {
        Chapter0305Activity.actionStart(AndroidKfystsChapter03Activity.this);
    }
}
