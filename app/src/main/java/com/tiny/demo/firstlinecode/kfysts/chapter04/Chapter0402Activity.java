package com.tiny.demo.firstlinecode.kfysts.chapter04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

/**
 * Desc: 继承ViewGroup派生特殊的Layout
 *
 * @author tiny
 * @date 2018/4/15 下午10:37
 */
public class Chapter0402Activity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter0402Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter0402);
    }
}
