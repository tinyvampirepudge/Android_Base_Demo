package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class NowWindBean {
    /**
     * deg : 91
     * dir : 东风
     * sc : 4-5
     * spd : 29
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
