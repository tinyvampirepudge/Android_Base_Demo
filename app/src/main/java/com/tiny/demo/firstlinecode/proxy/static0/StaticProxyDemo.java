package com.tiny.demo.firstlinecode.proxy.static0;

/**
 * 静态代理
 * <p>
 * https://www.jianshu.com/p/6f6bb2f0ece9
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）$version$
 * @date 2018/8/7 12:16
 * modify by:
 * modify date:
 * modify content:
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        RealSubject subject = new RealSubject();
        SubjectProxy proxy = new SubjectProxy(subject);
        proxy.request();
    }

}
