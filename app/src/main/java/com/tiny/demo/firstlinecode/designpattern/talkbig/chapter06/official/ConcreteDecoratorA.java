package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.official;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class ConcreteDecoratorA extends Decorator {
    private String addedState;//本类独有的功能

    @Override
    public void Operate() {
        super.Operate();//首先执行原Component的Operate的功能，再执行本类的功能。
        addedState = "New State";
        LogUtils.e("具体装饰类A的操作");
    }
}
