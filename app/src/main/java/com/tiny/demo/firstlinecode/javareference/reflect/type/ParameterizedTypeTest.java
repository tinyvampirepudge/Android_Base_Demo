package com.tiny.demo.firstlinecode.javareference.reflect.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * desc ParameterizedType Test
 * 参数化类型
 * http://loveshisong.cn/%E7%BC%96%E7%A8%8B%E6%8A%80%E6%9C%AF/2016-02-16-Type%E8%AF%A6%E8%A7%A3.html
 * <p>
 * {@link java.lang.reflect.ParameterizedType}
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 6:22 PM
 */
public class ParameterizedTypeTest {
    /**
     * 具体的泛型类型, 如Map<String, String>
     * 有如下方法:
     * <p>
     * Type getRawType(): 返回承载该泛型信息的对象, 如上面那个Map<String, String>承载范型信息的对象是Map
     * Type[] getActualTypeArguments(): 返回实际泛型类型列表, 如上面那个Map<String, String>实际范型列表中有两个元素, 都是String
     * Type getOwnerType(): 返回是谁的member.(上面那两个最常用)
     *
     * @param args
     */
    Map<String, String> map;

    public static void main(String[] args) throws Exception {
        Field f = ParameterizedTypeTest.class.getDeclaredField("map");
        System.out.println(f.getGenericType());                                     // java.util.Map<java.lang.String, java.lang.String>
        System.out.println(f.getGenericType() instanceof ParameterizedType);        // true
        ParameterizedType pType = (ParameterizedType) f.getGenericType();           // interface java.util.Map
        System.out.println(pType.getRawType());                                     // [Ljava.lang.reflect.Type;@3f99bd52
        System.out.println(pType.getActualTypeArguments());
        for (Type type : pType.getActualTypeArguments()) {                          // 打印两遍 class java.lang.String
            System.out.println(type);
        }
        System.out.println(pType.getOwnerType());                                   // null
    }
}
