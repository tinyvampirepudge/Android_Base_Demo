package com.tiny.demo.firstlinecode.network;

/**
 * Created by 87959 on 2017/5/17.
 */

public class AppBean {
    private String id;
    private String name;
    private String version;

    @Override
    public String toString() {
        return "AppBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
