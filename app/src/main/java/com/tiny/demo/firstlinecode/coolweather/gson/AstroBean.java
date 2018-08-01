package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class AstroBean {
    /**
     * mr : 15:10
     * ms : 02:18
     * sr : 04:54
     * ss : 19:00
     */

    @SerializedName("mr")
    public String mr;
    @SerializedName("ms")
    public String ms;
    @SerializedName("sr")
    public String sr;
    @SerializedName("ss")
    public String ss;
}
