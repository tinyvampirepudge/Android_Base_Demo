package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class CondBean {
    /**
     * code_d : 302
     * code_n : 306
     * txt_d : 雷阵雨
     * txt_n : 中雨
     */

    @SerializedName("code_d")
    public String codeD;
    @SerializedName("code_n")
    public String codeN;
    @SerializedName("txt_d")
    public String txtD;
    @SerializedName("txt_n")
    public String txtN;
}
