package com.tiny.demo.firstlinecode.javareference.copy.entity;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 无特殊类型，直接调用super即可
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:06
 * @Version
 */

public class CloneClass2 implements Cloneable {
    private String Name2;

    public String getName2() {
        return Name2;
    }

    public void setName2(String name2) {
        Name2 = name2;
    }

    @Override
    public Object clone() {
        CloneClass2 o = null;
        try {
            o = (CloneClass2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "CloneClass2{" +
                "Name2='" + Name2 + '\'' +
                '}';
    }
}
