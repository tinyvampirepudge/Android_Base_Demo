package com.tiny.demo.firstlinecode.templates.template5.presenter;

import com.tiny.demo.firstlinecode.templates.template5.contract.MvpContract;
import com.tiny.demo.firstlinecode.templates.template5.model.MvpModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


public class MvpPresenter implements MvpContract.IPresenter {

    MvpContract.IModel iModel;
    MvpContract.IView iView;

    public MvpPresenter(MvpContract.IView iView) {
        this.iView = iView;
        iModel = new MvpModel();
    }

    @Override
    public void getDataFromNet(Map<String, String> params) {
        iModel.getServerData(params, new Observer<ResponseBody>() {

            @Override
            public void onSubscribe(Disposable d) {
                iView.showLoadingDialog();
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
                iView.dismissLoadingDialog();
            }
        });
    }
}
