package com.tiny.demo.firstlinecode.designpattern.practice.observer;

import java.util.ArrayList;

/**
 * @Description: 观察者实现，监听多个InputStatusObservable的状态。
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/11/29 2:06 PM
 * @Version v4.4
 */
public class InputStatusObserverImpl implements InputStatusObserver {
    private ArrayList<InputStatusObservable> observableList = new ArrayList<>();
    private OnObservablesReadyListener onObservablesReadyListener;

    public InputStatusObserverImpl(OnObservablesReadyListener onObservablesReadyListener) {
        this.onObservablesReadyListener = onObservablesReadyListener;
    }

    public void add(InputStatusObservable observable) {
        observableList.add(observable);
    }

    public void remove(InputStatusObservable observable) {
        observableList.remove(observable);
    }

    /**
     * 所有InputStatusObservable的状态是否都已经Ok了
     *
     * @return
     */
    public boolean isObservablesReady() {
        for (InputStatusObservable observable : observableList) {
            if (!observable.isReady()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void update() {
        if (onObservablesReadyListener != null) {
            onObservablesReadyListener.ready(isObservablesReady());
        }
    }

    public interface OnObservablesReadyListener {
        void ready(boolean isReady);
    }
}
