package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class HourlyCondBean {
    /**
     * code : 100
     * txt : 晴
     */

    @SerializedName("code")
    public String code;
    @SerializedName("txt")
    public String txt;
}
