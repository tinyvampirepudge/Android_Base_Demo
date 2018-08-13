package com.tiny.demo.firstlinecode.javareference.reflect.type;

import junit.framework.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * desc TypeVariable test
 * http://loveshisong.cn/%E7%BC%96%E7%A8%8B%E6%8A%80%E6%9C%AF/2016-02-16-Type%E8%AF%A6%E8%A7%A3.html
 * <p>
 * {@link java.lang.reflect.TypeVariable}
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 6:32 PM
 */
public class TypeVariableTest<K extends Comparable & Serializable, V> {
    /**
     * 类型变量, 泛型信息在编译时会被转换为一个特定的类型, 而TypeVariable就是用来反映在JVM编译该泛型前的信息.
     * 它的声明是这样的: public interface TypeVariable<D extends GenericDeclaration> extends Type
     * 也就是说它跟GenericDeclaration有一定的联系, 我是这么理解的:
     * TypeVariable是指在GenericDeclaration中声明的<T>、<C extends Collection>这些东西中的那个变量T、C; 它有如下方法:
     * <p>
     * Type[] getBounds(): 获取类型变量的上边界, 若未明确声明上边界则默认为Object
     * D getGenericDeclaration(): 获取声明该类型变量实体
     * String getName(): 获取在源码中定义时的名字
     * 注意:
     * <p>
     * 类型变量在定义的时候只能使用extends进行(多)边界限定, 不能用super;
     * 为什么边界是一个数组? 因为类型变量可以通过&进行多个上边界限定，因此上边界有多个
     *
     * @param args
     */


    K key;
    V value;

    public static void main(String[] args) throws Exception {
        // 获取字段的类型
        Field fk = TypeVariableTest.class.getDeclaredField("key");
        Field fv = TypeVariableTest.class.getDeclaredField("value");
        TypeVariable keyType = (TypeVariable) fk.getGenericType();
        TypeVariable valueType = (TypeVariable) fv.getGenericType();

        //getName方法
        System.out.println(keyType.getName());                      // K
        System.out.println(valueType.getName());                    // V

        //getGenericDeclaration
        System.out.println(keyType.getGenericDeclaration());        // class com.tiny.demo.firstlinecode.javareference.reflect.type.TypeVariableTest
        System.out.println(valueType.getGenericDeclaration());      // class com.tiny.demo.firstlinecode.javareference.reflect.type.TypeVariableTest

        // getBounds
        System.out.println("K 的上界：");
        for (Type type : keyType.getBounds()) {
            System.out.println(type);                               // interface java.lang.Comparable  interface java.io.Serializable
        }

        System.out.println("V 的上界：");
        for (Type type : valueType.getBounds()) {                   // 没明确声明上界的, 默认上界是 Object
            System.out.println(type);                               // class java.lang.Object
        }
    }
}
