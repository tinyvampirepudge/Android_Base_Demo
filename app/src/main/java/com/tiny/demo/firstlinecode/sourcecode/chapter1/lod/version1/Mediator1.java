package com.tiny.demo.firstlinecode.sourcecode.chapter1.lod.version1;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:    中介
 * Created by tiny on 2017/10/6.
 * Version:
 */

public class Mediator1 {
    List<Room1> mRoom1s = new ArrayList<>();

    public Mediator1() {
        for (int j = 0; j < 5; j++) {
            mRoom1s.add(new Room1(14 + j, (14 + j) * 150));
        }
    }

    public List<Room1> getAllRooms() {
        return mRoom1s;
    }
}
