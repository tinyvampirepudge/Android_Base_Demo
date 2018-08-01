package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass17 implements Serializable{
    private String name;
    private CloneClass18 class18;

    public CloneClass17() {
    }

    public CloneClass17(String name, CloneClass18 class18) {
        this.name = name;
        this.class18 = class18;
    }

    public Object deepClone() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        //从流里读出来
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bais);
        return (oi.readObject());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloneClass18 getClass18() {
        return class18;
    }

    public void setClass18(CloneClass18 class18) {
        this.class18 = class18;
    }

    @Override
    public String toString() {
        return "CloneClass17{" +
                "name='" + name + '\'' +
                ", class18=" + class18 +
                '}';
    }
}
