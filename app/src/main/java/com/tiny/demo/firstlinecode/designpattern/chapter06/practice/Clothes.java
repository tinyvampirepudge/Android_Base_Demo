package com.tiny.demo.firstlinecode.designpattern.chapter06.practice;

/**
 * Desc:   装饰类的父类
 * Created by tiny on 2017/10/18.
 * Version:
 */

public abstract class Clothes extends Person {
    protected Person person;

    public void decorate(Person person) {
        this.person = person;
    }

    @Override
    public void show() {
        if (person != null) {
            person.show();
        }
    }
}
