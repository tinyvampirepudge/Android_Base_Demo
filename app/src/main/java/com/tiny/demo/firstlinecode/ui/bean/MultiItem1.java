package com.tiny.demo.firstlinecode.ui.bean;

import com.tiny.demo.firstlinecode.ui.adapter.MultiItemAdapter1;

/**
 * Desc:
 * Created by tiny on 2017/12/13.
 * Version:
 */

public class MultiItem1 {
    public String name;
    public String gender;
    public String imgUrl;
    public String type;

    public int getType() {
        switch (type) {
            case "1":
                return MultiItemAdapter1.TypeEnum.TYPE2.ordinal();
            case "2":
                return MultiItemAdapter1.TypeEnum.TYPE3.ordinal();
            case "0":
            default:
                return MultiItemAdapter1.TypeEnum.TYPE1.ordinal();
        }
    }
}
