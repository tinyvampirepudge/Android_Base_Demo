package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.official;

/**
 * Desc:    装饰抽象类，继承了Component，从外类来扩展Component类的功能，
 * 但对于Component来说，是无需知道它的存在的。
 * Created by tiny on 2017/10/18.
 * Version:
 */

public abstract class Decorator extends Component {
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void Operate() {
        if (component != null) {
            component.Operate();
        }
    }
}
