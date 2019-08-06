package com.tiny.demo.firstlinecode.ui.bean;

/**
 * Created by 87959 on 2017/3/12.
 */

public class Fruit {
    private String name;
    private int imgId;
    private int type;

    public Fruit() {
    }

    public Fruit(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
