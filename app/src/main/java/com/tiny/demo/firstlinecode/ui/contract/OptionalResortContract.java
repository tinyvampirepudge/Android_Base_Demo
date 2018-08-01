package com.tiny.demo.firstlinecode.ui.contract;


import com.tiny.demo.firstlinecode.common.base.BaseModel;
import com.tiny.demo.firstlinecode.common.base.BasePresenter;
import com.tiny.demo.firstlinecode.common.base.BaseView;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


/**
 * Desc:    自选股编辑页面，重新排序接口。
 * Created by tiny on 2017/10/27.
 * Version:
 */

public interface OptionalResortContract {
    interface IModel extends BaseModel {
        void getNetData(Map<String, String> params, Observer<ResponseBody> observer);
    }

    interface IView extends BaseView {
        void sendResultToView(ResponseBody responseBody, Map<String, String> params);
    }

    interface IPresenter extends BasePresenter {
        void getDataFromNet(Map<String, String> params);
    }
}
