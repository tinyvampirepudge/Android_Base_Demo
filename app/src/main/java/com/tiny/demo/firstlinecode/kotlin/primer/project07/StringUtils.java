package com.tiny.demo.firstlinecode.kotlin.primer.project07;



/**
 * @Description: Java中的工具类
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 3:33 PM
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static void main(String[] args) {
        System.out.println(isEmpty(""));    // true
        System.out.println(isEmpty(null));  // true
        System.out.println(isEmpty("abc")); // false

        //调用kotlin中jvmStatic注解的
        System.out.println(StringUtils1.isEmpty1(""));

        // 调用kotlin中的伴生对象
        System.out.println(StringUtils2.Companion.isEmpty(""));
    }

}
