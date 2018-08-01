package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass15 {
    private String name;
    private Map<String, CloneClass16> map;

    public CloneClass15(String name, Map<String, CloneClass16> map) {
        this.name = name;
        this.map = map;
    }

    public CloneClass15 clone() {
        Map<String, CloneClass16> cloneClass16Map = new HashMap<>(this.map == null ? 0 : this.map.size());
        Iterator<Map.Entry<String, CloneClass16>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, CloneClass16> entry = it.next();
            cloneClass16Map.put(entry.getKey(), entry.getValue().clone());
        }
        return new CloneClass15(this.name, cloneClass16Map);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, CloneClass16> getMap() {
        return map;
    }

    public void setMap(Map<String, CloneClass16> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "CloneClass15{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}
