package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class HourlyForecastBean {

    /**
     * cond : {"code":"100","txt":"晴"}
     * date : 2017-06-04 16:00
     * hum : 40
     * pop : 0
     * pres : 1007
     * tmp : 28
     * wind : {"deg":"103","dir":"东南风","sc":"3-4","spd":"19"}
     */

    @SerializedName("cond")
    public HourlyCondBean cond;
    @SerializedName("date")
    public String date;
    @SerializedName("hum")
    public String hum;
    @SerializedName("pop")
    public String pop;
    @SerializedName("pres")
    public String pres;
    @SerializedName("tmp")
    public String tmp;
    @SerializedName("wind")
    public HourlyWindBean wind;
}
