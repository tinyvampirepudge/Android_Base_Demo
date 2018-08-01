package com.tiny.demo.firstlinecode.stetho;


import com.tiny.demo.firstlinecode.common.base.BaseModel;
import com.tiny.demo.firstlinecode.common.base.BasePresenter;
import com.tiny.demo.firstlinecode.common.base.BaseView;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


/**
 * Created by tiny on 17/3/24.
 */

public interface StethoContract {

    interface IModel extends BaseModel {
        void getHomePageDataCompat(Map<String, String> params, Observer<ResponseBody> observer);
    }

    interface IView extends BaseView {
        void sendResultToView(ResponseBody dataBean, Map<String, String> params);

        void requestFailure(Map<String, String> params);
    }

    interface IPresetner extends BasePresenter {
        void getDataFromNet(Map<String, String> params);
    }
}
