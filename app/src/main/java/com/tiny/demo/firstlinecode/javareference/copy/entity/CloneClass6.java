package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Desc:
 * Created by tiny on 2017/12/17.
 * Version:
 */

public class CloneClass6 implements Cloneable {
    private String name;
    private Map<String, CloneClass7> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, CloneClass7> getMap() {
        return map;
    }

    public void setMap(Map<String, CloneClass7> map) {
        this.map = map;
    }

    @Override
    public Object clone() {
        CloneClass6 o = null;
        try {
            o = (CloneClass6) super.clone();
            HashMap<String, CloneClass7> class7HashMap = new HashMap<>(o.getMap() == null ? 0 : o.getMap().size());
            Iterator<Map.Entry<String, CloneClass7>> it = o.getMap().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, CloneClass7> entry = it.next();
                class7HashMap.put(entry.getKey(), (CloneClass7) entry.getValue().clone());
            }
            o.setMap(class7HashMap);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass6{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}
