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
public class CloneClass4 implements Cloneable {
    private String name;
    private List<CloneClass5> class5List;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CloneClass5> getClass5List() {
        return class5List;
    }

    public void setClass5List(List<CloneClass5> class5List) {
        this.class5List = class5List;
    }

    @Override
    public Object clone() {
        CloneClass4 o = null;
        try {
            o = (CloneClass4) super.clone();
            List<CloneClass5> c5List = new ArrayList<>(o.getClass5List() == null ? 0 : o.getClass5List().size());
            Iterator<CloneClass5> it = o.getClass5List().iterator();
            while (it.hasNext()) {
                c5List.add((CloneClass5) it.next().clone());
            }
            o.setClass5List(c5List);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass4{" +
                "name='" + name + '\'' +
                ", class5List=" + class5List +
                '}';
    }
}
