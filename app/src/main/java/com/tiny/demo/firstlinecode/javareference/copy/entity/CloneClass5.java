package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 无特殊类型，直接调用super即可
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:06
 * @Version TODO
 */
public class CloneClass5 implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CloneClass5 o = null;
        o = (CloneClass5) super.clone();
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass5{" +
                "name='" + name + '\'' +
                '}';
    }
}
