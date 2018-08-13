package com.tiny.demo.firstlinecode.javareference.reflect.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * @Description: WildcardType Test
 * http://loveshisong.cn/%E7%BC%96%E7%A8%8B%E6%8A%80%E6%9C%AF/2016-02-16-Type%E8%AF%A6%E8%A7%A3.html
 * {@link java.lang.reflect.WildcardType}
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/13 10:51 PM
 */
public class WildcardTypeTest {
    /**
     * 该接口表示通配符泛型, 比如? extends Number 和 ? super Integer 它有如下方法:
     * <p>
     * Type[] getUpperBounds(): 获取泛型变量的上界
     * Type[] getLowerBounds(): 获取泛型变量的下界
     * 注意:
     * <p>
     * 现阶段通配符只接受一个上边界或下边界, 返回数组是为了以后的扩展, 实际上现在返回的数组的大小是1
     */

    // a没有下界, 取下界会抛出ArrayIndexOutOfBoundsException
    private List<? extends Number> a;

    private List<? super String> b;

    public static void main(String[] args) throws Exception {
        Field fieldA = WildcardTypeTest.class.getDeclaredField("a");
        Field fieldB = WildcardTypeTest.class.getDeclaredField("b");

        // 先拿到泛型类型
        ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
        ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();

        // 再从泛型里拿到通配符类型
        WildcardType wTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
        WildcardType wTypeB = (WildcardType) pTypeB.getActualTypeArguments()[0];

        //方法测试
        System.out.println(wTypeA.getUpperBounds()[0]);// class java.lang.Number
        System.out.println(wTypeB.getLowerBounds()[0]);// class java.lang.String

        // 查看通配符到底是什么
        System.out.println(wTypeA); // 打印结果：? extends java.lang.Number
    }

    /**
     * 再写几个边界的例子:

     List<? extends Number>, 上界为class java.lang.Number, 属于Class类型
     List<? extends List<T>>, 上界为java.util.List<T>, 属于ParameterizedType类型
     List<? extends List<String>>, 上界为java.util.List<java.lang.String>, 属于ParameterizedType类型
     List<? extends T>, 上界为T, 属于TypeVariable类型
     List<? extends T[]>, 上界为T[], 属于GenericArrayType类型
     它们最终统一成Type作为数组的元素类型

     */
}
