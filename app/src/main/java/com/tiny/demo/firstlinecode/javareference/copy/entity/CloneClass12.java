package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass12 {
    private String name;
    private List<CloneClass13> class13List;

    public CloneClass12(String name, List<CloneClass13> class13List) {
        this.name = name;
        this.class13List = class13List;
    }

    public CloneClass12 clone() {
        List<CloneClass13> list = new ArrayList<>(class13List == null ? 0 : class13List.size());
        Iterator<CloneClass13> it = class13List.iterator();
        while (it.hasNext()) {
            list.add(it.next().clone());
        }
        return new CloneClass12(this.name, list);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CloneClass13> getClass13List() {
        return class13List;
    }

    public void setClass13List(List<CloneClass13> class13List) {
        this.class13List = class13List;
    }

    @Override
    public String toString() {
        return "CloneClass12{" +
                "name='" + name + '\'' +
                ", class13List=" + class13List +
                '}';
    }
}
