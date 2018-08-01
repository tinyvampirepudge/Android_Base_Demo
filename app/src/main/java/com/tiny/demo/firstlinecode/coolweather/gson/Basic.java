package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class Basic {

    /**
     * city : 苏州
     * cnty : 中国
     * id : CN101190401
     * lat : 31.29937935
     * lon : 120.61958313
     * update : {"loc":"2017-06-04 13:50","utc":"2017-06-04 05:50"}
     */

    @SerializedName("city")
    public String city;
    @SerializedName("cnty")
    public String cnty;
    @SerializedName("id")
    public String id;
    @SerializedName("lat")
    public String lat;
    @SerializedName("lon")
    public String lon;
    @SerializedName("update")
    public UpdateBean update;
}
