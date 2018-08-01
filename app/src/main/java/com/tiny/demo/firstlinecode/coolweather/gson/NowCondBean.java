package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class NowCondBean {
    /**
     * code : 101
     * txt : 多云
     */

    @SerializedName("code")
    public String code;
    @SerializedName("txt")
    public String txt;
}
