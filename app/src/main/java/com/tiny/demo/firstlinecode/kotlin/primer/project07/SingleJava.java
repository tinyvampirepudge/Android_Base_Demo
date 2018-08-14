package com.tiny.demo.firstlinecode.kotlin.primer.project07;

/**
 * @Description: Java下的单例
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 4:22 PM
 */
public class SingleJava {
    private SingleJava() {
        System.out.println("SingleJava init");
    }

    private static final class SingleJavaHolder {
        private static final SingleJava INSTANCE = new SingleJava();
    }

    public static SingleJava getInstance() {
        return SingleJavaHolder.INSTANCE;
    }
}
