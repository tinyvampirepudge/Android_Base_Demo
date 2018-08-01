package com.tiny.demo.firstlinecode.designpattern.chapter01;

/**
 * Desc:
 * Created by tiny on 2017/10/24.
 * Version:
 */

public class SimpleOperationFactory {
    public static Operation createOperate(String operate) {
        Operation oper = null;
        switch (operate) {
            case "+":
                oper = new OperationAdd();
                break;
            case "-":
                oper = new OperationSub();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            default:
                break;
        }
        return oper;
    }
}
