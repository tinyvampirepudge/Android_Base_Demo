package com.tiny.demo.firstlinecode.ui.view;

/**
 * Desc:
 * Created by tiny on 2017/10/26.
 * Version:
 */

public interface ItemTouchHelperCallback {
    void onItemDelete(int position);//侧拉删除

    void onMove(int fromPosition, int toPosition);//move
}
