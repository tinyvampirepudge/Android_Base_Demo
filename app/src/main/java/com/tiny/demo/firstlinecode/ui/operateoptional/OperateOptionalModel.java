package com.tiny.demo.firstlinecode.ui.operateoptional;


import com.tiny.demo.firstlinecode.common.bean.ResBean;

import java.util.Map;

import io.reactivex.Observer;


/**
 * ===============================
 * 版本：1.0
 * <p/>
 * 描述：订阅
 * <p/>
 * 修订历史
 * <p/>
 * Created by happhou on 2017/8/27.
 */

public class OperateOptionalModel implements OperateOptionalContract.IModel {

    /**
     * 添加订阅
     */
    @Override
    public void addZiXuanData(Map<String, String> params, Observer<ResBean> observer) {

    }

    /**
     * 删除订阅
     */
    @Override
    public void delZiXuanData(Map<String, String> parmas, Observer<ResBean> observer) {

    }

    /**
     * 检查订阅
     */
    @Override
    public void checkZiXuanData(Map<String, String> parmas, Observer<ResBean<String>> observer) {

    }
}
