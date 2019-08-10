package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 无特殊类型，直接调用super即可
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:07
 * @Version TODO
 */
public class CloneClass8 implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() {
        CloneClass8 o = null;
        try {
            o = (CloneClass8) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass8{" +
                "name='" + name + '\'' +
                '}';
    }
}
