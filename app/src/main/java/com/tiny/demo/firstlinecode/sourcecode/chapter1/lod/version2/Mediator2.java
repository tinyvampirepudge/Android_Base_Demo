package com.tiny.demo.firstlinecode.sourcecode.chapter1.lod.version2;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 中介
 * Created by tiny on 2017/10/6.
 * Version:
 */

public class Mediator2 {
    List<Room2> mRooms = new ArrayList<>();

    public Mediator2() {
        for (int j = 0; j < 5; j++) {
            mRooms.add(new Room2(14 + j, (14 + j) * 150));
        }
    }

    public Room2 rentOut(float area, float price) {
        for (Room2 r : mRooms) {
            if (isSuitable(area, price, r)) {
                return r;
            }
        }
        return null;
    }

    private boolean isSuitable(float area, float price, Room2 room) {
        return Math.abs(room.price - price) < Tenant2.diffPrice && Math.abs(room.area - area) < Tenant2.diffPrice;
    }
}
