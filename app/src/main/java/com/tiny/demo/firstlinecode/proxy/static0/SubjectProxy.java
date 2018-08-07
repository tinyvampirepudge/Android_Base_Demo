package com.tiny.demo.firstlinecode.proxy.static0;

/**
 * 代理类，RealSubject的代理类
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）$version$
 * @date 2018/8/7 12:14
 * modify by:
 * modify date:
 * modify content:
 */
public class SubjectProxy implements Subject {
    private Subject subject;

    public SubjectProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("PreProcess");
        subject.request();
        System.out.println("PostProcess");
    }
}
