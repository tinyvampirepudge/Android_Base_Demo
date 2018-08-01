package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: View基础知识
 *
 * @author tiny
 * @date 2018/3/19 下午9:19
 */
public class Chapter0301Activity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter0301Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter0301);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_01)
    public void onBtnTest01Clicked() {
    }

    @OnClick(R.id.btn_test_02)
    public void onBtnTest02Clicked() {
    }

    @OnClick(R.id.btn_test_03)
    public void onBtnTest03Clicked() {
    }
}
