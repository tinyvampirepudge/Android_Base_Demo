package com.tiny.demo.firstlinecode.brvah.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/12.
 * Version:
 */

public class Movie {
    public String name;
    public int length;
    public int price;
    public String content;

    public Movie(String name, int length, int price, String content) {
        this.name = name;
        this.length = length;
        this.price = price;
        this.content = content;
    }
}
