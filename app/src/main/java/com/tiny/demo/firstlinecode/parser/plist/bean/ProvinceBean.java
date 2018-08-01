package com.tiny.demo.firstlinecode.parser.plist.bean;

import java.util.List;

/**
 * Created by tiny on 17/7/7.
 */

public class ProvinceBean {
    public String state;
    public List<CityBean> cities;

    @Override
    public String toString() {
        return "ProvinceBean{" +
                "state='" + state + '\'' +
                ", cities=" + cities +
                '}';
    }
}
