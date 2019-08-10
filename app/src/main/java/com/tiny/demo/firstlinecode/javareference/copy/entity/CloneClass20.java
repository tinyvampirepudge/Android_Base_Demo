package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.Serializable;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:04
 * @Version
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
