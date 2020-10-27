package com.tiny.demo.firstlinecode.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.coolweather.gson.DailyForecastBean;
import com.tiny.demo.firstlinecode.coolweather.gson.WeatherInfo;
import com.tiny.demo.firstlinecode.coolweather.service.AutoUpdateService;
import com.tiny.demo.firstlinecode.coolweather.util.HttpUtil;
import com.tiny.demo.firstlinecode.coolweather.util.Utility;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherDetailActivity extends BaseActivity {
    public DrawerLayout drawerLayout;
    private Button navButton;
    public SwipeRefreshLayout swipeRefresh;
    private ImageView bingPicImg;
    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;

    //允许xml布局文件中的某个view的background使用vector
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_weather_detail;
    }

    @Override
    public void setTheme() {
        super.setTheme();
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void buildContentView() {
        //初始化各控件
        drawerLayout = findViewById(R.id.drawer_layout);
        navButton = findViewById(R.id.nav_button);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        bingPicImg = findViewById(R.id.bing_pic_img);
        weatherLayout = findViewById(R.id.weather_layout);
        titleCity = findViewById(R.id.title_city);
        titleUpdateTime = findViewById(R.id.title_update_time);
        degreeText = findViewById(R.id.degree_text);
        weatherInfoText = findViewById(R.id.weather_info_text);
        forecastLayout = findViewById(R.id.forecast_layout);
        aqiText = findViewById(R.id.aqi_text);
        pm25Text = findViewById(R.id.pm25_text);
        comfortText = findViewById(R.id.comfort_text);
        carWashText = findViewById(R.id.car_wash_text);
        sportText = findViewById(R.id.sport_text);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        final String weatherId;
        if (weatherString != null) {
            //有缓存时直接解析天气数据
            WeatherInfo weatherInfo = Utility.handleWeatherResponse(weatherString);
            weatherId = weatherInfo.basic.id;
            showWeatherInfo(weatherInfo);
        } else {
            //无缓存时去服务器查询天气
            weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

        swipeRefresh.setOnRefreshListener(() -> requestWeather(weatherId));

        navButton.setOnClickListener((v) -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        String bigPic = prefs.getString("bing_pic", null);
        if (bigPic != null) {
            Glide.with(this).load(bigPic).into(bingPicImg);
        } else {
            loadBingPic();
        }
    }

    /**
     * 加载每日必应一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherDetailActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(() -> {
                    Glide.with(WeatherDetailActivity.this).load(bingPic).into(bingPicImg);
                });
            }
        });
    }

    public void requestWeather(String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId
                + "&key=765fcd9294f6463d8aea1b2f44a41e09";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    ToastUtils.showSingleToast(getString(R.string.string_get_weather_failed));
                    swipeRefresh.setRefreshing(false);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                LogUtils.INSTANCE.e("responseText --> " + responseText);
                WeatherInfo weatherInfo = Utility.handleWeatherResponse(responseText);
                runOnUiThread(() -> {
                    if (weatherInfo != null && "ok".equals(weatherInfo.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherDetailActivity.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                        showWeatherInfo(weatherInfo);
                        //开启自动更新服务
                        Intent intent = new Intent(mContext, AutoUpdateService.class);
                        startService(intent);
                    } else {
                        ToastUtils.showSingleToast(getString(R.string.string_get_weather_failed));
                    }
                    swipeRefresh.setRefreshing(false);
                });
            }
        });
    }

    private void showWeatherInfo(WeatherInfo weatherInfo) {
        String cityName = weatherInfo.basic.city;
        String updateTime = weatherInfo.basic.update.loc;
        String degree = weatherInfo.now.tmp + getString(R.string.string_sheshidu);
        String weatherInfoStr = weatherInfo.now.cond.txt;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfoStr);
        forecastLayout.removeAllViews();
        for (DailyForecastBean forecast : weatherInfo.daily_forecast) {
            View view = LayoutInflater.from(WeatherDetailActivity.this).inflate(R.layout.forecast_item,
                    forecastLayout, false);
            TextView dateText = view.findViewById(R.id.date_text);
            TextView infoText = view.findViewById(R.id.info_text);
            TextView maxText = view.findViewById(R.id.max_text);
            TextView minText = view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.cond.txtD);
            maxText.setText(forecast.tmp.max);
            minText.setText(forecast.tmp.min);
            forecastLayout.addView(view);
        }
        if (weatherInfo.aqi != null) {
            aqiText.setText(weatherInfo.aqi.city.aqi);
            pm25Text.setText(weatherInfo.aqi.city.pm25);
        }
        String comfort = getString(R.string.string_comfort) + weatherInfo.suggestion.comf.txt;
        String carwash = getString(R.string.string_carwash) + weatherInfo.suggestion.cw.txt;
        String sport = getString(R.string.string_sport) + weatherInfo.suggestion.sport.txt;
        comfortText.setText(comfort);
        carWashText.setText(carwash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initViewData() {

    }
}
