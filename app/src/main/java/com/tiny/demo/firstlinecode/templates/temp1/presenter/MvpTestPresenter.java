package com.tiny.demo.firstlinecode.templates.temp1.presenter;

import com.tiny.demo.firstlinecode.templates.temp1.contract.MvpTestContract;
import com.tiny.demo.firstlinecode.templates.temp1.model.MvpTestModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


public class MvpTestPresenter implements MvpTestContract.IPresenter {

    MvpTestContract.IModel iModel;
    MvpTestContract.IView iView;

    public MvpTestPresenter(MvpTestContract.IView iView) {
        this.iView = iView;
        iModel = new MvpTestModel();
    }

    @Override
    public void getDataFromNet(Map<String, String> params) {
        iModel.getServerData(params, new Observer<ResponseBody>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                iView.sendResultToView(responseBody, params);
            }
            @Override
            public void onError(Throwable e) {
                iView.dismissLoadingDialog();
                iView.showErrorMsg(e.toString());
            }

            @Override
            public void onComplete() {

            }

        });
    }
}
