package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * @Description: 在clone方法中new 一个全新对象出来，实现java深度克隆
 * 无特殊类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:07
 * @Version TODO
 */

public class CloneClass16 {
    private String name;

    public CloneClass16(String name) {
        this.name = name;
    }

    @Override
    public CloneClass16 clone() {
        return new CloneClass16(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CloneClass16{" +
                "name='" + name + '\'' +
                '}';
    }
}
