package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class Hat extends Clothes {
    @Override
    public void show() {
        super.show();
        LogUtils.e("戴帽子。\n");
    }
}
