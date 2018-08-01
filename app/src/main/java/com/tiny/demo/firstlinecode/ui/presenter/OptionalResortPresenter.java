package com.tiny.demo.firstlinecode.ui.presenter;


import com.tiny.demo.firstlinecode.BuildConfig;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ResUtils;
import com.tiny.demo.firstlinecode.ui.contract.OptionalResortContract;
import com.tiny.demo.firstlinecode.ui.model.OptionalResortModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


/**
 * Created by tiny on 17/6/5.
 */

public class OptionalResortPresenter implements OptionalResortContract.IPresenter {
    OptionalResortContract.IView iView;
    OptionalResortContract.IModel iModel;

    public OptionalResortPresenter(OptionalResortContract.IView iView) {
        this.iView = iView;
        iModel = new OptionalResortModel();
    }

    @Override
    public void getDataFromNet(final Map<String, String> params) {
        iModel.getNetData(params, new Observer<ResponseBody>() {

            @Override
            public void onSubscribe(Disposable d) {
                iView.showLoadingDialog();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                iView.sendResultToView(responseBody, params);
                iView.dismissLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                if (e != null) {
                    e.printStackTrace();
                }
                iView.dismissLoadingDialog();
                iView.showErrorMsg(BuildConfig.DEBUG ? e.toString()
                        : ResUtils.getStringResource(R.string.request_data_error));
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
