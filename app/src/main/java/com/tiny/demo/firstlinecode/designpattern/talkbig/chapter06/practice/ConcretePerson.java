package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class ConcretePerson extends Person {
    private String name;

    public ConcretePerson(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        LogUtils.INSTANCE.e(name +"开始穿衣服了：\n");
    }
}
