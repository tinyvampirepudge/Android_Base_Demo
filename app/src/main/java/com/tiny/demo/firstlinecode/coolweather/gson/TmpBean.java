package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class TmpBean {
    /**
     * max : 24
     * min : 21
     */

    @SerializedName("max")
    public String max;
    @SerializedName("min")
    public String min;
}
