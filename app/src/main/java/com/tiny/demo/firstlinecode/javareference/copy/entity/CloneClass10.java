package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * @Description: 在clone方法中 new一个全新对象出来，实现java深度克隆
 * 无特殊类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:07
 * @Version TODO
 */

public class CloneClass10 {
    private String name;

    public CloneClass10(String name) {
        this.name = name;
    }

    @Override
    public CloneClass10 clone() {
        return new CloneClass10(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CloneClass10{" +
                "name='" + name + '\'' +
                '}';
    }
}
