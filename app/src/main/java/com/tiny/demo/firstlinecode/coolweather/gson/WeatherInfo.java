package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 87959 on 2017/6/4.
 */

public class WeatherInfo {
    @SerializedName("status")
    public String status;
    @SerializedName("aqi")
    public Aqi aqi;
    @SerializedName("basic")
    public Basic basic;
    @SerializedName("now")
    public NowBean now;
    @SerializedName("daily_forecast")
    public List<DailyForecastBean> daily_forecast;
    @SerializedName("hourly_forecast")
    public List<HourlyForecastBean> hourly_forecast;
    @SerializedName("suggestion")
    public Suggesstion suggestion;
}
