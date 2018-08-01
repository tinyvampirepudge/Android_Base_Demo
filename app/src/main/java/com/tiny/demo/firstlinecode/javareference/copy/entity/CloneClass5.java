package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass5 implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CloneClass5 o = null;
        o = (CloneClass5) super.clone();
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass5{" +
                "name='" + name + '\'' +
                '}';
    }
}
