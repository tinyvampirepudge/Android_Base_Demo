package com.tiny.demo.firstlinecode.sourcecode.chapter1.lod.version1;

/**
 * Desc:    房间
 * Created by tiny on 2017/10/6.
 * Version:
 */

public class Room1 {
    public float area;
    public float price;

    public Room1(float area, float price) {
        this.area = area;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room1{" +
                "area=" + area +
                ", price=" + price +
                '}';
    }
}
