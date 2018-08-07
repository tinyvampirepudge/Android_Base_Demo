package com.tiny.demo.firstlinecode.proxy.static0;

/**
 * 委托对象
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）$version$
 * @date 2018/8/7 12:01
 * modify by:
 * modify date:
 * modify content:
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("request");
    }
}
