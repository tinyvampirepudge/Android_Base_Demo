package com.tiny.demo.firstlinecode.designpattern.chapter06.practice;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

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
        LogUtils.e(name +"开始穿衣服了：\n");
    }
}
