package com.tiny.demo.firstlinecode.ui.bean;

/**
 * @Description:
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 16:02
 * @Version
 */
public class GroupItemBean {
    private String cityName;
    private String provinceName;

    public GroupItemBean(String cityName, String provinceName) {
        this.cityName = cityName;
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "GroupItemBean{" +
                "cityName='" + cityName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
