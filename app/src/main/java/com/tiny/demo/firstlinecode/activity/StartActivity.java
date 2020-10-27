package com.tiny.demo.firstlinecode.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

public class StartActivity extends BaseActivity {
    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, StartActivity.class);
        intent.putExtra("data1", data1);
        intent.putExtra("data2", data2);
        context.startActivity(intent);
    }

    /**
     * 直接利用Bundle传递参数。
     *
     * @param context
     * @param args
     */
    public static void actionStart(Context context, Bundle args) {
        Intent intent = new Intent(context, StartActivity.class);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void buildContentView() {
        String data1 = getIntent().getStringExtra("data1");
        String data2 = getIntent().getStringExtra("data2");
        LogUtils.INSTANCE.e("data1 --> " + data1 + ",data2 --> " + data2);
    }

    @Override
    protected void initViewData() {

    }
}
