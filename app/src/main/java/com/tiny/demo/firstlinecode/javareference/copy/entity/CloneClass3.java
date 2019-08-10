package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 针对List类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:06
 * @Version TODO
 */
public class CloneClass3 implements Cloneable {
    private String name;
    private List<CloneClass4> class4List;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CloneClass4> getClass4List() {
        return class4List;
    }

    public void setClass4List(List<CloneClass4> class4List) {
        this.class4List = class4List;
    }

    @Override
    public Object clone() {
        CloneClass3 o = null;
        try {
            o = (CloneClass3) super.clone();
            List<CloneClass4> c4List = new ArrayList<>(o.getClass4List() == null ? 0 : o.getClass4List().size());
            Iterator<CloneClass4> it = o.getClass4List().iterator();
            while (it.hasNext()) {
                c4List.add((CloneClass4) it.next().clone());
            }
            o.setClass4List(c4List);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass3{" +
                "name='" + name + '\'' +
                ", class4List=" + class4List +
                '}';
    }
}
