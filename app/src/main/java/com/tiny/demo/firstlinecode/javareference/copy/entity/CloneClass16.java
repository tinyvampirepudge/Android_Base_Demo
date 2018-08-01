package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass16 {
    private String name;

    public CloneClass16(String name) {
        this.name = name;
    }

    public CloneClass16 clone() {
        return new CloneClass16(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CloneClass16{" +
                "name='" + name + '\'' +
                '}';
    }
}
