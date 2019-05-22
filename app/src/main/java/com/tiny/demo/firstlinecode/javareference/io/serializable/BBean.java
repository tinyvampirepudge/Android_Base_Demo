package com.tiny.demo.firstlinecode.javareference.io.serializable;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2019/5/13 2:49 PM
 * @Version TODO
 */
public class BBean implements Serializable {
    private String name;
    private int age;
    private boolean gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ABean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
