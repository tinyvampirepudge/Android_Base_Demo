package com.tiny.demo.firstlinecode.common.base;

/**
 * Created by 87959 on 2017/9/18.
 */

public interface BaseView {
    void showLoadingDialog();

    void dismissLoadingDialog();

    void showErrorMsg(String errorMsg);
}
