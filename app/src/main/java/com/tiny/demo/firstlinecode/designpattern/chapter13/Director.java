package com.tiny.demo.firstlinecode.designpattern.chapter13;

/**
 * Desc:    指挥者类
 * Created by tiny on 2017/10/24.
 * Version:
 */

public class Director {
    /**
     * 用来指挥构建过程
     *
     * @param builder
     */
    public void constructor(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}
