package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
/**
 * Desc:    动画实现View的弹性滑动
 *
 * @author tiny
 * @date 2018/3/25 下午1:49
 */
public class Chapter030201Activity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter030201Activity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter030201);
    }
}
