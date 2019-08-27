package com.tiny.demo.firstlinecode.concurrentprogram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: Java并发编程的艺术
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-27 10:08
 * @Version
 */
public class JavaArtOfConcurrentProgramActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, JavaArtOfConcurrentProgramActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_art_of_concurrent_program);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_chapter_01)
    public void onBtnChapter01Clicked() {
    }

    @OnClick(R.id.btn_chapter_02)
    public void onBtnChapter02Clicked() {
    }

    @OnClick(R.id.btn_chapter_03)
    public void onBtnChapter03Clicked() {
    }

    @OnClick(R.id.btn_chapter_04)
    public void onBtnChapter04Clicked() {
    }

    @OnClick(R.id.btn_chapter_05)
    public void onBtnChapter05Clicked() {
    }

    @OnClick(R.id.btn_chapter_06)
    public void onBtnChapter06Clicked() {
    }

    @OnClick(R.id.btn_chapter_07)
    public void onBtnChapter07Clicked() {
    }

    @OnClick(R.id.btn_chapter_08)
    public void onBtnChapter08Clicked() {
    }

    @OnClick(R.id.btn_chapter_09)
    public void onBtnChapter09Clicked() {
    }

    @OnClick(R.id.btn_chapter_10)
    public void onBtnChapter10Clicked() {
    }

    @OnClick(R.id.btn_chapter_11)
    public void onBtnChapter11Clicked() {
    }
}
