package com.tiny.demo.firstlinecode.coolweather.db;

import org.litepal.crud.LitePalSupport;

/**
 * Created by 87959 on 2017/6/1.
 */

public class County extends LitePalSupport {
    private int id;
    private String countyName;
    private String weatherId;
    private int cityId;

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
