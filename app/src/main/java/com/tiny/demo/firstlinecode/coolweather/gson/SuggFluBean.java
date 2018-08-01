package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class SuggFluBean {
    /**
     * brf : 少发
     * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
     */

    @SerializedName("brf")
    public String brf;
    @SerializedName("txt")
    public String txt;
}
