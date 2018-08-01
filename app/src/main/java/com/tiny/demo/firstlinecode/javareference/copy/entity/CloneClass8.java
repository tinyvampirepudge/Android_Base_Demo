package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass8 implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() {
        CloneClass8 o = null;
        try {
            o = (CloneClass8) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass8{" +
                "name='" + name + '\'' +
                '}';
    }
}
