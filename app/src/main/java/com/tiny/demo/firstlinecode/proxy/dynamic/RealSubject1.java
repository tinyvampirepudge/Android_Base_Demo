package com.tiny.demo.firstlinecode.proxy.dynamic;

/**
 * @Description: $desc$
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 7:17 PM
 */
public class RealSubject1 implements Subject1 {
    @Override
    public void request() {
        System.out.println(this.getClass().getSimpleName() + " request");
    }

    @Override
    public void show(String str) {
        System.out.println(this.getClass().getSimpleName() + " show String:" + str);
    }

    @Override
    public void show(Integer integer) {
        System.out.println(this.getClass().getSimpleName() + " show Integer:" + integer);
    }

    @Override
    public void show(int i) {
        System.out.println(this.getClass().getSimpleName() + " show int:" + i);
    }
}
