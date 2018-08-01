package com.tiny.demo.firstlinecode.coolweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

public class CoolWeatherActivity extends BaseActivity {

    //允许xml布局文件中的某个view的background使用vector
    static{
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CoolWeatherActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_cool_weather;
    }

    @Override
    protected void buildContentView() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("weather", null) != null) {
            Intent intent = new Intent(this, WeatherDetailActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void initViewData() {

    }
}
