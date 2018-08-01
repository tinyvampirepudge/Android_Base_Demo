package com.tiny.demo.firstlinecode.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 87959 on 2017/6/4.
 */

public class DailyForecastBean {

    /**
     * astro : {"mr":"15:10","ms":"02:18","sr":"04:54","ss":"19:00"}
     * cond : {"code_d":"302","code_n":"306","txt_d":"雷阵雨","txt_n":"中雨"}
     * date : 2017-06-05
     * hum : 65
     * pcpn : 28.6
     * pop : 100
     * pres : 1010
     * tmp : {"max":"24","min":"21"}
     * uv : 3
     * vis : 16
     * wind : {"deg":"106","dir":"东南风","sc":"4-5","spd":"17"}
     */

    @SerializedName("astro")
    public AstroBean astro;
    @SerializedName("cond")
    public CondBean cond;
    @SerializedName("date")
    public String date;
    @SerializedName("hum")
    public String hum;
    @SerializedName("pcpn")
    public String pcpn;
    @SerializedName("pop")
    public String pop;
    @SerializedName("pres")
    public String pres;
    @SerializedName("tmp")
    public TmpBean tmp;
    @SerializedName("uv")
    public String uv;
    @SerializedName("vis")
    public String vis;
    @SerializedName("wind")
    public WindBean wind;
}
