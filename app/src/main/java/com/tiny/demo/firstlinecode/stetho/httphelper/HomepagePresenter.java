package com.tiny.demo.firstlinecode.stetho.httphelper;

import com.tiny.demo.firstlinecode.stetho.HomepageModel;
import com.tiny.demo.firstlinecode.stetho.StethoContract;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tiny on 17/3/24.
 */

public class HomepagePresenter implements StethoContract.IPresetner {
    private StethoContract.IModel iModel;
    private StethoContract.IView iView;

    public HomepagePresenter(StethoContract.IView iView) {
        this.iView = checkNotNull(iView, "HomepageContract.IView cannot be null");
        this.iModel = new HomepageModel();
    }

    @Override
    public void getDataFromNet(final Map<String, String> params) {
        iModel.getHomePageDataCompat(params, new Observer<ResponseBody>() {

            @Override
            public void onSubscribe(Disposable d) {
                iView.showLoadingDialog();
            }

            @Override
            public void onNext(ResponseBody resBean) {
                iView.sendResultToView(resBean, params);
            }

            @Override
            public void onError(Throwable e) {
                iView.requestFailure(params);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
