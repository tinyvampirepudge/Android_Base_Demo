package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class SuggDrsgBean {
    /**
     * brf : 热
     * txt : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
     */

    @SerializedName("brf")
    public String brf;
    @SerializedName("txt")
    public String txt;
}
