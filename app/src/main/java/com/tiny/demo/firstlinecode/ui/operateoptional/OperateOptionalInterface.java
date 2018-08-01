package com.tiny.demo.firstlinecode.ui.operateoptional;

/**
 * Desc:
 * ①添加、删除自选股
 * ②该股票是否已添加进自选股。
 * Created by tiny on 2017/10/16.
 * Version:
 */

public interface OperateOptionalInterface {
    void addOptionalSuccess();//自选股添加成功

    void deleteOptionalSuccess();//自选股删除成功

    void optionAdded(boolean isAdded);//该股票是否已添加进自选股。

    void showErrorMsg(String errorMsg);//展示错误信息
}
