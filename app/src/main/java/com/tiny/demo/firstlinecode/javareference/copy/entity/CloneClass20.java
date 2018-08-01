package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.Serializable;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass20 implements Serializable {
    private String name;

    public CloneClass20() {
    }

    public CloneClass20(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CloneClass20{" +
                "name='" + name + '\'' +
                '}';
    }
}
