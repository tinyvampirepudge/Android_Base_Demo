package com.tiny.demo.firstlinecode.kotlin.primer.project03;

/**
 * desc 实现
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/9 10:57 AM
 */
public class A implements AInterface {

    public static A a = new A();

    @Override
    public void putNumber(int num) {
        System.out.println("int");
    }

    @Override
    public void putNumber(Integer num) {
        System.out.println("Integer");
    }
}
