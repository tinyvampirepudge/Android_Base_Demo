package com.tiny.demo.firstlinecode.brvah.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Desc:
 * Created by tiny on 2017/12/12.
 * Version:
 */

public class ClickEntity implements MultiItemEntity {
    public static final int CLICK_ITEM_VIEW = 1;
    public static final int CLICK_ITEM_CHILD_VIEW = 2;
    public static final int LONG_CLICK_ITEM_VIEW = 3;
    public static final int LONG_CLICK_ITEM_CHILD_VIEW = 4;
    public static final int NEST_CLICK_ITEM_CHILD_VIEW = 5;

    public int Type;

    public ClickEntity(int type) {
        Type = type;
    }

    @Override
    public int getItemType() {
        return Type;
    }
}
