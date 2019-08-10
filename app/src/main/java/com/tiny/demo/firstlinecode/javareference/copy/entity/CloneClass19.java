package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:11
 * @Version TODO
 */
public class CloneClass19 implements Serializable{
    private String name;
    private HashMap<String, CloneClass20> map;

    public CloneClass19() {
    }

    public CloneClass19(String name, HashMap<String, CloneClass20> map) {
        this.name = name;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, CloneClass20> getMap() {
        return map;
    }

    public void setMap(HashMap<String, CloneClass20> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "CloneClass19{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}
