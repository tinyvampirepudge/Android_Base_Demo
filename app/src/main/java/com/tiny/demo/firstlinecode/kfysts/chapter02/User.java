package com.tiny.demo.firstlinecode.kfysts.chapter02;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/3/5 上午11:55
 */
public class User implements Serializable {
    private int age;
    private String name;
    private boolean gender;

    public User() {
    }

    public User(int age, String name, boolean gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
