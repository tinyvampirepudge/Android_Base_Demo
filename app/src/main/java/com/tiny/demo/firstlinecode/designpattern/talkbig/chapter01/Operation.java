package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter01;

/**
 * Desc:
 * Created by tiny on 2017/10/24.
 * Version:
 */

public abstract class Operation {
    private double numberA = 0;
    private double numberB = 0;

    public double getNumberA() {
        return numberA;
    }

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }

    public abstract double getResult();
}
