package com.tiny.demo.firstlinecode.designpattern.practice.observer;

/**
 * @Description: 被观察者，单个View的输入状态，true or false
 * 参考 {@link java.util.Observable}
 * @Author wangjianzhou@qding.me
 * @Date 2018/11/29 2:06 PM
 * @Version v4.4
 */
public class InputStatusObservable {
    private boolean ready;
    private InputStatusObserver observer;

    public InputStatusObservable(boolean ready, InputStatusObserverImpl observer) {
        this.ready = ready;
        this.observer = observer;
    }

    public InputStatusObservable(InputStatusObserverImpl observer) {
        this.observer = observer;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
        observer.update();
    }
}
