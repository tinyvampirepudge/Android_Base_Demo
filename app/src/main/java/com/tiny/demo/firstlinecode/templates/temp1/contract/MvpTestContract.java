package com.tiny.demo.firstlinecode.templates.temp1.contract;

import com.tiny.demo.firstlinecode.common.base.BaseModel;
import com.tiny.demo.firstlinecode.common.base.BasePresenter;
import com.tiny.demo.firstlinecode.common.base.BaseView;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


public interface MvpTestContract {

    interface IModel extends BaseModel {
        void getServerData(Map<String, String> params, Observer<ResponseBody> observer);
    }

    interface IView extends BaseView {
        void sendResultToView(ResponseBody responseBody, Map<String, String> params);
    }

    interface IPresenter extends BasePresenter {
        void getDataFromNet(Map<String, String> params);
    }
}
