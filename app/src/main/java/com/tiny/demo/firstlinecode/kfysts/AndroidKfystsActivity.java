package com.tiny.demo.firstlinecode.kfysts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.kfysts.chapter01.AndroidKfystsChapter01Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter02.AndroidKfystsChapter02Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter03.AndroidKfystsChapter03Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter04.AndroidKfystsChapter04Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter08.AndroidKfystsChapter08Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter09.AndroidKfystsChapter09Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter10.AndroidKfystsChapter10Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter11.AndroidKfystsChapter11Activity;
import com.tiny.demo.firstlinecode.kfysts.chapter12.AndroidKfystsChapter12Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: Android开发艺术探索
 * Created by tiny on 2018/2/24 下午3:52
 * Version:
 */
public class AndroidKfystsActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AndroidKfystsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidkfysts);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_chapter_01)
    public void onBtnChapter01Clicked() {
        AndroidKfystsChapter01Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_02)
    public void onBtnChapter02Clicked() {
        AndroidKfystsChapter02Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_03)
    public void onBtnChapter03Clicked() {
        AndroidKfystsChapter03Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_04)
    public void onBtnChapter04Clicked() {
        AndroidKfystsChapter04Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_08)
    public void onBtnChapter08Clicked() {
        AndroidKfystsChapter08Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_09)
    public void onBtnChapter09Clicked() {
        AndroidKfystsChapter09Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_10)
    public void onBtnChapter10Clicked() {
        AndroidKfystsChapter10Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_11)
    public void onBtnChapter11Clicked() {
        AndroidKfystsChapter11Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_12)
    public void onBtnChapter12Clicked() {
        AndroidKfystsChapter12Activity.actionStart(this);
    }

}
