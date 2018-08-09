package com.tiny.demo.firstlinecode.kotlin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;

/**
 * @Description: kotlin入口
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/8/8 9:42 PM
 */
public class KotlinEntryActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, KotlinEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin_entry);
    }
}
