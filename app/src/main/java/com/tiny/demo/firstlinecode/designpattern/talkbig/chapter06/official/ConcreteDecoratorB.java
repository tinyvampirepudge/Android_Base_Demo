package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.official;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class ConcreteDecoratorB extends Decorator {
    @Override
    public void Operate() {
        super.Operate();
        addedBehavior();
        LogUtils.e("具体装饰对象B的操作");
    }

    private void addedBehavior() {
        LogUtils.e(getClass().getSimpleName() + " --> addedBehavior()");
    }
}
