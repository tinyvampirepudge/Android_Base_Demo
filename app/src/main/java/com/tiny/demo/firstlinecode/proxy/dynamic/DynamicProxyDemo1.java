package com.tiny.demo.firstlinecode.proxy.dynamic;

import com.tiny.demo.firstlinecode.proxy.static0.RealSubject;
import com.tiny.demo.firstlinecode.proxy.static0.Subject;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * https://www.jianshu.com/p/6f6bb2f0ece9
 * <p>
 * https://www.imooc.com/article/21339
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）$version$
 * @date 2018/8/7 13:25
 * modify by:
 * modify date:
 * modify content:
 */
public class DynamicProxyDemo1 {
    public static void main(String[] args) {
        // 1、创建委托对象
        RealSubject1 realSubject = new RealSubject1();
        // 2、创建调用处理器对象
        ProxyHandler1 handler = new ProxyHandler1(realSubject);
        // 3、动态生成代理对象
        Subject1 proxySubject = (Subject1) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);
        // 4、通过代理对象调用方法
        proxySubject.request();
        proxySubject.show("string");
        proxySubject.show(100);
        proxySubject.show(new Integer(10000));
    }
}
