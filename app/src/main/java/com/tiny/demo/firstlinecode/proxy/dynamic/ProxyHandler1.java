package com.tiny.demo.firstlinecode.proxy.dynamic;

import com.tiny.demo.firstlinecode.proxy.static0.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类的调用处理器
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）$version$
 * @date 2018/8/7 13:26
 * modify by:
 * modify date:
 * modify content:
 */
public class ProxyHandler1 implements InvocationHandler {
    private Subject subject;

    public ProxyHandler1(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=========before=========");
        Object result = method.invoke(subject, args);
        System.out.println("=========after=========");
        return result;
    }
}
