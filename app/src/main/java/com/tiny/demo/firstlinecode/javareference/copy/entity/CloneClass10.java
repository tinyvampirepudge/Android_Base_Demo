package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass10 {
    private String name;

    public CloneClass10(String name) {
        this.name = name;
    }

    public CloneClass10 clone() {
        return new CloneClass10(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CloneClass10{" +
                "name='" + name + '\'' +
                '}';
    }
}
