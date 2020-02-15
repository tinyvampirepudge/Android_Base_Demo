package com.tiny.demo.firstlinecode.javareference.resume.question04;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2020-01-07 20:14
 * @Version TODO
 */
public class Question4 {
    public static void main(String[] args) {
        SuperClass superClass = new SubClass();
        printHello(superClass);

        Type type = new TypeToken<Collection<Integer>>(){}.getType();
    }

    public static void printHello(SuperClass superClass) {
        System.out.println("Hello " + superClass.getName());
    }

    public static void printHello(SubClass subClass) {
        System.out.println("Hello " + subClass.getName());
    }
}
