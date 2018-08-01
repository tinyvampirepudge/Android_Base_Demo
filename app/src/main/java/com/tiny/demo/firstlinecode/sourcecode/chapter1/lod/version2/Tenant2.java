package com.tiny.demo.firstlinecode.sourcecode.chapter1.lod.version2;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:    租户
 * Created by tiny on 2017/10/6.
 * Version:
 */

public class Tenant2 {
    public float roomArea;
    public float roomPrice;
    public static final float diffPrice = 100.00001f;
    public static final float diffArea = 0.00001f;

    public void rentRoom(Mediator2 mediator) {
        LogUtils.e("租到房啦！ --> " + mediator.rentOut(roomArea, roomPrice).toString());
    }
}