package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class CityBean {

    /**
     * aqi : 54
     * co : 0
     * no2 : 22
     * o3 : 156
     * pm10 : 57
     * pm25 : 19
     * qlty : 良
     * so2 : 9
     */

    @SerializedName("aqi")
    public String aqi;
    @SerializedName("co")
    public String co;
    @SerializedName("no2")
    public String no2;
    @SerializedName("o3")
    public String o3;
    @SerializedName("pm10")
    public String pm10;
    @SerializedName("pm25")
    public String pm25;
    @SerializedName("qlty")
    public String qlty;
    @SerializedName("so2")
    public String so2;
}
