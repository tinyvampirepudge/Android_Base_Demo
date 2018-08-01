package com.tiny.demo.firstlinecode.designpattern.chapter01;

/**
 * Desc:    减法操作类
 * Created by tiny on 2017/10/24.
 * Version:
 */

public class OperationSub extends Operation {
    @Override
    public double getResult() {
        double result = 0;
        result = getNumberA() - getNumberB();
        return result;
    }
}
