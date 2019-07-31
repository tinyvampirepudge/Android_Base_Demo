package com.tiny.demo.firstlinecode.screenadaptive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: Android屏幕适配
 * 1、
 * 一种极低成本的Android屏幕适配方式  by 字节跳动技术团队
 * https://mp.weixin.qq.com/s?__biz=MzI1MzYzMjE0MQ==&mid=2247484502&idx=2&sn=a60ea223de4171dd2022bc2c71e09351
 * <p>
 * 2、
 * Android 目前稳定高效的UI适配方案
 * https://mp.weixin.qq.com/s/X-aL2vb4uEhqnLzU5wjc4Q
 * @Author wangjianzhou@qding.me
 * @Date 2019-07-31 14:13
 * @Version
 */
public class ScreenAdaptiveEntryActivity extends AppCompatActivity {
    public static final String TAG = ScreenAdaptiveEntryActivity.class.getSimpleName();
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ScreenAdaptiveEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_adaptive_entry);
        ButterKnife.bind(this);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float heightPixels = displayMetrics.heightPixels;
        float widthPixels = displayMetrics.widthPixels;
        float density = displayMetrics.density;
        int densityDpi = displayMetrics.densityDpi;
        // 屏幕尺寸，单位inch
        double screenSize = Math.sqrt((Math.pow(heightPixels, 2) + Math.pow(widthPixels, 2))) / densityDpi;

        tv1.setText(String.format(Locale.getDefault(), "heightPixels: %.2fpx", heightPixels));
        tv2.setText(String.format(Locale.getDefault(), "widthPixels: %.2fpx", widthPixels));
        tv3.setText(String.format(Locale.getDefault(), "density: %.2fpx", density));
        tv4.setText(String.format(Locale.getDefault(), "densityDpi: %dpx", densityDpi));
        tv5.setText(String.format(Locale.getDefault(), "screenSize: %.2finch", screenSize));
    }
}
