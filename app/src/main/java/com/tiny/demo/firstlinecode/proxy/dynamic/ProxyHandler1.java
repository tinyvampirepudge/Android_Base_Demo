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
    private Object obj;

    public ProxyHandler1(Object obj) {
        this.obj = obj;
    }

    /**
     * 这个函数是在代理对象调用任何一个方法时都会调用的，方法不同会导致第二个参数method不同，
     * 第一个参数是代理对象（表示哪个代理对象调用了method方法），
     * 第二个参数是 Method 对象（表示哪个方法被调用了），
     * 第三个参数是指定调用方法的参数。
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=========before=========");
        Object result = method.invoke(obj, args);
        System.out.println("=========after=========");
        return result;
    }
}
