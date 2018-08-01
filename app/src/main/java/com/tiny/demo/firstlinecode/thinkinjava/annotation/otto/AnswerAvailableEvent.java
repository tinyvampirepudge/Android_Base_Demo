package com.tiny.demo.firstlinecode.thinkinjava.annotation.otto;

/**
 * Desc:
 * Created by tiny on 2018/1/4.
 * Time: 13:33
 * Version:
 */

public class AnswerAvailableEvent {
    private int num;

    public AnswerAvailableEvent(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
