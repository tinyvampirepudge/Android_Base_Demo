package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter01;

import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

/**
 * Desc:    出发操作类
 * Created by tiny on 2017/10/24.
 * Version:
 */

public class OperationDiv extends Operation {
    @Override
    public double getResult() {
        double result = 0;
        if (getNumberB() == 0) {
            ToastUtils.showSingleToast("除数为0！");
            return 0;
        }
        result = getNumberA() / getNumberB();
        return result;
    }
}
