package com.tiny.demo.firstlinecode.designpattern.chapter01;

/**
 * Desc:    加法操作类
 * Created by tiny on 2017/10/24.
 * Version:
 */

public class OperationAdd extends Operation {
    @Override
    public double getResult() {
        double result = 0;
        result = getNumberA() + getNumberB();
        return result;
    }
}
