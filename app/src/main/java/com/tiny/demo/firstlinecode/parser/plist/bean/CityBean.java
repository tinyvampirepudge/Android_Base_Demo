package com.tiny.demo.firstlinecode.parser.plist.bean;

/**
 * Created by tiny on 17/7/7.
 */

public class CityBean {
    public String city;
    public String lat;
    public String lon;

    @Override
    public String toString() {
        return "CityBean{" +
                "city='" + city + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
