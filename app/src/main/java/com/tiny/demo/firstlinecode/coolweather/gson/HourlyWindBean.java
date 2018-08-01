package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class HourlyWindBean {
    /**
     * deg : 103
     * dir : 东南风
     * sc : 3-4
     * spd : 19
     */

    @SerializedName("deg")
    public String deg;
    @SerializedName("dir")
    public String dir;
    @SerializedName("sc")
    public String sc;
    @SerializedName("spd")
    public String spd;
}
