package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class SuggUvBean {
    /**
     * brf : 弱
     * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
     */

    @SerializedName("brf")
    public String brf;
    @SerializedName("txt")
    public String txt;
}
