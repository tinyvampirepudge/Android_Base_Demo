package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class UpdateBean {
    /**
     * loc : 2017-06-04 13:50
     * utc : 2017-06-04 05:50
     */

    @SerializedName("loc")
    public String loc;
    @SerializedName("utc")
    public String utc;
}
