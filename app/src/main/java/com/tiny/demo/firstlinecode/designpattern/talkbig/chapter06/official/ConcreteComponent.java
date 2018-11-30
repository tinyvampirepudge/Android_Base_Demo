package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.official;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:    定义了一个具体的对象，也可以给这个对象添加一些职责。
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class ConcreteComponent extends Component {
    @Override
    public void Operate() {
        LogUtils.e("具体对象的操作：" + getClass().getSimpleName() + " Operate");
    }
}
