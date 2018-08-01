package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass13 {
    private String name;

    public CloneClass13(String name) {
        this.name = name;
    }

    public CloneClass13 clone() {
        return new CloneClass13(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CloneClass13{" +
                "name='" + name + '\'' +
                '}';
    }
}
