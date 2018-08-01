package com.tiny.demo.firstlinecode.sourcecode.chapter1.lod.version2;

/**
 * Desc:    房间
 * Created by tiny on 2017/10/6.
 * Version:
 */

public class Room2 {
    public float area;
    public float price;

    public Room2(float area, float price) {
        this.area = area;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room2{" +
                "area=" + area +
                ", price=" + price +
                '}';
    }
}
