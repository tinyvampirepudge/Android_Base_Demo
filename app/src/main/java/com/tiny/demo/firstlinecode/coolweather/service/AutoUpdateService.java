package com.tiny.demo.firstlinecode.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.tiny.demo.firstlinecode.coolweather.gson.WeatherInfo;
import com.tiny.demo.firstlinecode.coolweather.util.HttpUtil;
import com.tiny.demo.firstlinecode.coolweather.util.Utility;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e("AutoUpdateService onStartCommand");
        //获取数据并缓存起来
        updateWeather();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int intervalTime = 1 * 60 * 60 * 1000;//1hour
        long triggerTime = SystemClock.elapsedRealtime() + intervalTime;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新天气信息
     */
    private void updateWeather() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = sp.getString("weather", null);
        if (!TextUtils.isEmpty(weatherString)) {
            //有缓存时直接解析天气数据
            WeatherInfo weatherInfo = Utility.handleWeatherResponse(weatherString);
            String weatherId = weatherInfo.basic.id;

            String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId
                    + "&key=765fcd9294f6463d8aea1b2f44a41e09";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    WeatherInfo weatherInfo1 = Utility.handleWeatherResponse(responseText);
                    if (weatherInfo1 != null && "ok".equals(weatherInfo1.status)) {
                        LogUtils.e("AutoUpdateService responseText --> " + responseText);
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                    }
                }
            });
        }
    }

    /**
     * 更新必应每日一图
     */
    private void updateBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }
        });
    }
}
