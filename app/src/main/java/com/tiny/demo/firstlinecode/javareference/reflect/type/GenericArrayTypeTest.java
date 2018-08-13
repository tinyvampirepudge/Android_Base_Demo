package com.tiny.demo.firstlinecode.javareference.reflect.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * desc GenericArrayType test
 * 泛型数组类型
 * http://loveshisong.cn/%E7%BC%96%E7%A8%8B%E6%8A%80%E6%9C%AF/2016-02-16-Type%E8%AF%A6%E8%A7%A3.html
 * <p>
 * {@link java.lang.reflect.GenericArrayType}
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 6:54 PM
 */
public class GenericArrayTypeTest<T> {

    /**
     * 范型数组,组成数组的元素中有范型则实现了该接口;
     * 它的组成元素是ParameterizedType或TypeVariable类型,它只有一个方法:
     * 1、Type getGenericComponentType(): 返回数组的组成对象, 即被JVM编译后实际的对象
     * 第一个参数List<String>[]的组成元素List<String>是ParameterizedType类型, 打印结果为true
     * 第二个参数T[]的组成元素T是TypeVariable类型, 打印结果为true
     * 第三个参数List<String>不是数组, 打印结果为false
     * 第四个参数String[]的组成元素String是普通对象, 没有范型, 打印结果为false
     * 第五个参数int[] pTypeArray的组成元素int是原生类型, 也没有范型, 打印结果为false
     */
    public static void main(String[] args) {
        Method method = Test.class.getDeclaredMethods()[0];
        // public void com.tiny.demo.firstlinecode.javareference.reflect.type.Test.show(java.util.List[],java.lang.Object[],java.util.List,java.lang.String[],int[])
        System.out.println(method);
        Type[] types = method.getGenericParameterTypes();
        // 这是Method中的方法
        for (Type type : types) {
            System.out.println(type instanceof GenericArrayType);
        }
        /**
         * true
         true
         false
         false
         false
         */
    }
}
