package com.tiny.demo.firstlinecode.kfysts.chapter04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:   自定义View示例
 *
 * @author tiny
 * @date 2018/4/15 下午7:44
 */
public class AndroidKfystsChapter04Activity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AndroidKfystsChapter04Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter04);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_chapter_01)
    public void onBtnChapter01Clicked() {
        Chapter0401Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_02)
    public void onBtnChapter02Clicked() {
        Chapter0402Activity.actionStart(this);
    }

    @OnClick(R.id.btn_chapter_03)
    public void onBtnChapter03Clicked() {
    }

    @OnClick(R.id.btn_chapter_04)
    public void onBtnChapter04Clicked() {
    }
}
