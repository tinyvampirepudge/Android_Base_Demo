package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * @Description: 在clone方法中 new一个全新对象出来，实现java深度克隆
 * 针对Object类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:07
 * @Version TODO
 */

public class CloneClass9 {
    private String name;
    private CloneClass10 class10;

    public CloneClass9(String name, CloneClass10 class10) {
        this.name = name;
        this.class10 = class10;
    }

    @Override
    public CloneClass9 clone() {
        return new CloneClass9(this.name, this.class10.clone());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloneClass10 getClass10() {
        return class10;
    }

    public void setClass10(CloneClass10 class10) {
        this.class10 = class10;
    }

    @Override
    public String toString() {
        return "CloneClass9{" +
                "name='" + name + '\'' +
                ", class10=" + class10 +
                '}';
    }
}
