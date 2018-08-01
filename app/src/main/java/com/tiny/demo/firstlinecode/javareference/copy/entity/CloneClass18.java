package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass18 implements Serializable {
    private String name;
    private List<CloneClass19> class19List;

    public CloneClass18() {
    }

    public CloneClass18(String name, List<CloneClass19> class19List) {
        this.name = name;
        this.class19List = class19List;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CloneClass19> getClass19List() {
        return class19List;
    }

    public void setClass19List(List<CloneClass19> class19List) {
        this.class19List = class19List;
    }

    @Override
    public String toString() {
        return "CloneClass18{" +
                "name='" + name + '\'' +
                ", class19List=" + class19List +
                '}';
    }
}
