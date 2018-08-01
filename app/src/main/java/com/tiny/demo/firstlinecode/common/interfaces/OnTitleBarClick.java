package com.tiny.demo.firstlinecode.common.interfaces;

import android.view.View;

/**
 * 标题栏响应接口
 * <p/>
 * Created by wuhenzhizao on 2014/12/23.
 * ydl rename
 */
public interface OnTitleBarClick {

    /**
     * @param type :
     * @param v
     */
    void onLeftClicked(int type, View v);

    void onRightClicked(int type, View v);
}
