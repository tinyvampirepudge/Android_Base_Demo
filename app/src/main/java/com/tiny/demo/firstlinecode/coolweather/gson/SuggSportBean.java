package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class SuggSportBean {
    /**
     * brf : 较适宜
     * txt : 天气较好，但因风力稍强，户外可选择对风力要求不高的运动，推荐您进行室内运动。
     */

    @SerializedName("brf")
    public String brf;
    @SerializedName("txt")
    public String txt;
}
