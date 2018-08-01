package com.tiny.demo.firstlinecode.parser.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tiny on 17/7/6.
 */

public class CityListBean {

    /**
     * province : 北京市
     * name : 市辖区
     * id : 110100
     */

    @SerializedName("province")
    public String province;
    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public String id;

    @Override
    public String toString() {
        return "CityListBean{" +
                "province='" + province + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
