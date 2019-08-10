package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description: 在clone方法中new 一个全新对象出来，实现java深度克隆
 * 针对Map类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:07
 * @Version TODO
 */

public class CloneClass14 {
    private String name;
    private Map<String, CloneClass15> map;

    public CloneClass14(String name, Map<String, CloneClass15> map) {
        this.name = name;
        this.map = map;
    }

    @Override
    public CloneClass14 clone() {
        Map<String, CloneClass15> cloneClass15Map = new HashMap<>(this.map == null ? 0 : this.map.size());
        Iterator<Map.Entry<String, CloneClass15>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, CloneClass15> entry = it.next();
            cloneClass15Map.put(entry.getKey(), entry.getValue().clone());
        }
        return new CloneClass14(this.name, cloneClass15Map);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, CloneClass15> getMap() {
        return map;
    }

    public void setMap(Map<String, CloneClass15> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "CloneClass14{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}
