package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 在clone方法中new 一个全新对象出来，实现java深度克隆
 * 针对List类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:07
 * @Version TODO
 */

public class CloneClass11 {
    private String name;
    private List<CloneClass12> class12List;

    public CloneClass11(String name, List<CloneClass12> class12List) {
        this.name = name;
        this.class12List = class12List;
    }

    @Override
    public CloneClass11 clone() {
        List<CloneClass12> list = new ArrayList<>(class12List == null ? 0 : class12List.size());
        Iterator<CloneClass12> it = class12List.iterator();
        while (it.hasNext()) {
            list.add(it.next().clone());
        }
        return new CloneClass11(this.name, list);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CloneClass12> getClass12List() {
        return class12List;
    }

    public void setClass12List(List<CloneClass12> class12List) {
        this.class12List = class12List;
    }

    @Override
    public String toString() {
        return "CloneClass11{" +
                "name='" + name + '\'' +
                ", class12List=" + class12List +
                '}';
    }
}
