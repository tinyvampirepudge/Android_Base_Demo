package com.tiny.demo.firstlinecode.kfysts.chapter04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

/**
 * Desc: 继承View重写onDraw方法
 *
 * @author tiny
 * @date 2018/4/15 下午7:47
 */
public class Chapter0401Activity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter0401Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter0401);
    }
}
