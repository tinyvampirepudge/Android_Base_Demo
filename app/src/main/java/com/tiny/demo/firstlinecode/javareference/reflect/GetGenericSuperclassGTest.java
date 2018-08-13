package com.tiny.demo.firstlinecode.javareference.reflect;

/**
 * desc getSuperclass和getGenericSuperclass的区别
 * https://www.cnblogs.com/maokun/p/6773203.html
 * {@link Class#getSuperclass()}
 * {@link Class#getGenericSuperclass()}
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 5:43 PM
 */
public class GetGenericSuperclassGTest {

    public static void main(String[] args) {
        //interface, primitive type, void or class, 还有数组

        // 接口
        System.out.println("Interface.class.getGenericSuperclass()\t"
                + TestInterface.class.getGenericSuperclass());
        System.out.println("Interface.class.getSuperclass()\t"
                + TestInterface.class.getSuperclass());

        // Object
        System.out.println("Object.class.getGenericSuperclass()\t"
                + Object.class.getGenericSuperclass());
        System.out.println("Object.class.getSuperclass()\t"
                + Object.class.getSuperclass());

        // void
        System.out.println("void.class.getSuperclass()\t"
                + void.class.getSuperclass());
        System.out.println("void.class.getGenericSuperclass()\t"
                + void.class.getGenericSuperclass());

        // 基本数据类型
        System.out.println("int.class.getGenericSuperclass()\t"
                + int.class.getGenericSuperclass());
        System.out.println("int.class.getSuperclass()\t"
                + int.class.getSuperclass());

        // 包装数据类型
        System.out.println("Integer.class.getGenericSuperclass()\t"
                + Integer.class.getGenericSuperclass());
        System.out.println("Integer.class.getSuperclass()\t"
                + Integer.class.getSuperclass());

        // 数组
        System.out.println("int[].class.getSuperclass()\t"
                + int[].class.getSuperclass());
        System.out.println("int[].class.getGenericSuperclass()\t"
                + int[].class.getGenericSuperclass());


        // 普通类
        System.out.println("GenericClassTest.class.getSuperclass()\t"
                + GenericClassTest.class.getSuperclass());
        System.out.println("GenericClassTest.class.getGenericSuperclass()\t"
                + GenericClassTest.class.getGenericSuperclass());

        // 泛型的类
        System.out.println("Student.class.getSuperclass()\t"
                + Student.class.getSuperclass());
        System.out.println("Student.class.getGenericSuperclass()\t"
                + Student.class.getGenericSuperclass());
    }
}
