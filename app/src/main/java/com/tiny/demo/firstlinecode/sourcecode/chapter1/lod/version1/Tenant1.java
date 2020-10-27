package com.tiny.demo.firstlinecode.sourcecode.chapter1.lod.version1;

import com.tinytongtong.tinyutils.LogUtils;

import java.util.List;

/**
 * Desc:    租户
 * Created by tiny on 2017/10/6.
 * Version:
 */

public class Tenant1 {
    public float roomArea;
    public float roomPrice;
    public static final float diffPrice = 100.000f;
    public static final float diffArea = 0.00001f;

    public void rentRoom(Mediator1 mediator1) {
        List<Room1> room1s = mediator1.getAllRooms();
        for (Room1 r : room1s) {
            if (isSuitable(r)) {
                LogUtils.INSTANCE.e("租到房间啦！ --> " + r);
            }
        }
    }

    private boolean isSuitable(Room1 room1) {
        return Math.abs(room1.price - roomPrice) < diffPrice && Math.abs(room1.area - roomArea) < diffArea;
    }

    public void setRoomArea(float roomArea) {
        this.roomArea = roomArea;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }
}
