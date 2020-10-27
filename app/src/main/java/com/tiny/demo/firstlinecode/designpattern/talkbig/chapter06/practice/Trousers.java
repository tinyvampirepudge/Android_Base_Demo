package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class Trousers extends Clothes {
    @Override
    public void show() {
        super.show();
        LogUtils.INSTANCE.e("穿裤子.\n");
    }
}
