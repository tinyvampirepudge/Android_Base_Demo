package com.tiny.demo.firstlinecode.ui.operateoptional;


import com.tiny.demo.firstlinecode.common.base.BaseModel;
import com.tiny.demo.firstlinecode.common.base.BasePresenter;
import com.tiny.demo.firstlinecode.common.base.BaseView;
import com.tiny.demo.firstlinecode.common.bean.ResBean;

import java.util.Map;

import io.reactivex.Observer;


/**
 * ===============================
 * 版本：1.0
 * <p/>
 * 描述：添加删除自选，获取自选股状态
 * <p/>
 * 修订历史
 * <p/>
 * Created by happhou on 2017/8/30.
 */

public interface OperateOptionalContract {

    interface IModel extends BaseModel {

        void addZiXuanData(Map<String, String> params, Observer<ResBean> observer);

        void delZiXuanData(Map<String, String> parmas, Observer<ResBean> observer);

        void checkZiXuanData(Map<String, String> parmas, Observer<ResBean<String>> observer);
    }

    interface IView extends BaseView {

        void sendAddZixuanToView(Map<String, String> params);

        void delZiXuanToView(Map<String, String> params);

        void checkZiXuanToView(String data);
    }

    interface IPresenter extends BasePresenter {

        void addZiXuanFromNet(Map<String, String> params);

        void delZiXuanFromNet(Map<String, String> params);

        void checkZiXuanFromNet(Map<String, String> params);
    }
}
