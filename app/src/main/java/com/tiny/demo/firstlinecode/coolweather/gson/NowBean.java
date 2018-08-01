package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class NowBean {

    /**
     * cond : {"code":"101","txt":"多云"}
     * fl : 27
     * hum : 45
     * pcpn : 0
     * pres : 1007
     * tmp : 28
     * vis : 7
     * wind : {"deg":"91","dir":"东风","sc":"4-5","spd":"29"}
     */

    @SerializedName("cond")
    public NowCondBean cond;
    @SerializedName("fl")
    public String fl;
    @SerializedName("hum")
    public String hum;
    @SerializedName("pcpn")
    public String pcpn;
    @SerializedName("pres")
    public String pres;
    @SerializedName("tmp")
    public String tmp;
    @SerializedName("vis")
    public String vis;
    @SerializedName("wind")
    public NowWindBean wind;
}
