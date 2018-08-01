package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class SuggComfBean {
    /**
     * brf : 较舒适
     * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
     */

    @SerializedName("brf")
    public String brf;
    @SerializedName("txt")
    public String txt;
}
