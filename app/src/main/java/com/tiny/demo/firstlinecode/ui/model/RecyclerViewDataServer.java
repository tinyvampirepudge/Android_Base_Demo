package com.tiny.demo.firstlinecode.ui.model;

import com.tiny.demo.firstlinecode.ui.bean.MultiItem1;
import com.tiny.demo.firstlinecode.ui.bean.MultiItemBravh;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/13.
 * Version:
 */

public class RecyclerViewDataServer {
    public static List<MultiItem1> getMultiItem1List() {
        List<MultiItem1> list = new ArrayList<>();
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 3; n++) {
                MultiItem1 multiItem1 = new MultiItem1();
                int i = n % 3;
                multiItem1.type = String.valueOf(i);
                multiItem1.name = "王蛋蛋" + m + n;
                multiItem1.gender = "小母猫" + m + n;
                list.add(multiItem1);
            }
        }
        return list;
    }

    public static List<MultiItemBravh> getMultiItemBravh(int size) {
        List<MultiItemBravh> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(new MultiItemBravh("王蛋蛋" + j, MultiItemBravh.TYPE1, MultiItemBravh.SPAN_1));
            list.add(new MultiItemBravh("王蛋蛋" + j, MultiItemBravh.TYPE1, MultiItemBravh.SPAN_1));
            list.add(new MultiItemBravh("王蛋蛋" + j, MultiItemBravh.TYPE1, MultiItemBravh.SPAN_1));
            list.add(new MultiItemBravh("王蛋蛋" + (j + 1), MultiItemBravh.TYPE2, MultiItemBravh.SPAN_2));
            list.add(new MultiItemBravh("王蛋蛋" + (j + 1), MultiItemBravh.TYPE1, MultiItemBravh.SPAN_1));
            list.add(new MultiItemBravh("王蛋蛋" + (j + 2), MultiItemBravh.TYPE3, MultiItemBravh.SPAN_3));
        }
        return list;
    }
}
