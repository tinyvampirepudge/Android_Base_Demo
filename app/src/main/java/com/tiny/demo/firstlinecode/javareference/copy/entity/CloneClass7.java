package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 针对Map类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:07
 * @Version
 */
public class CloneClass7 implements Cloneable {
    private String name;
    private Map<String, CloneClass8> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, CloneClass8> getMap() {
        return map;
    }

    public void setMap(Map<String, CloneClass8> map) {
        this.map = map;
    }

    @Override
    public Object clone() {
        CloneClass7 o = null;
        try {
            o = (CloneClass7) super.clone();
            HashMap<String, CloneClass8> class8HashMap = new HashMap<>(o.getMap() == null ? 0 : o.getMap().size());
            Iterator<Map.Entry<String, CloneClass8>> it = o.getMap().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, CloneClass8> entry = it.next();
                class8HashMap.put(entry.getKey(), (CloneClass8) entry.getValue().clone());
            }
            o.setMap(class8HashMap);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass7{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}
