package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 针对Object类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:04
 * @Version TODO
 */
public class CloneClass1 implements Cloneable {
    private String name;
    private CloneClass2 cloneClass2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloneClass2 getCloneClass2() {
        return cloneClass2;
    }

    public void setCloneClass2(CloneClass2 cloneClass2) {
        this.cloneClass2 = cloneClass2;
    }

    @Override
    public Object clone() {
        CloneClass1 o = null;
        try {
            o = (CloneClass1) super.clone();
            o.cloneClass2 = (CloneClass2) o.cloneClass2.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass1{" +
                "name='" + name + '\'' +
                ", cloneClass2=" + cloneClass2 +
                '}';
    }
}
