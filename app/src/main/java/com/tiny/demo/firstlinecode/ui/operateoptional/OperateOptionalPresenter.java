package com.tiny.demo.firstlinecode.ui.operateoptional;


import com.tiny.demo.firstlinecode.common.bean.ResBean;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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

public class OperateOptionalPresenter implements OperateOptionalContract.IPresenter {
    private OperateOptionalContract.IView iView;
    private OperateOptionalContract.IModel iModel;

    public OperateOptionalPresenter(OperateOptionalContract.IView iView) {
        this.iView = iView;
        this.iModel = new OperateOptionalModel();
    }

    @Override
    public void addZiXuanFromNet(final Map<String, String> params) {
        iModel.addZiXuanData(params, new Observer<ResBean>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResBean resBean) {
                if (resBean.isSuccess()) {
                    iView.sendAddZixuanToView(params);
                } else {
                    iView.showErrorMsg(resBean.getMsg());
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void delZiXuanFromNet(final Map<String, String> params) {
        iModel.delZiXuanData(params, new Observer<ResBean>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResBean resBean) {
                if (resBean.isSuccess()) {
                    iView.delZiXuanToView(params);
                } else {
                    iView.showErrorMsg(resBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void checkZiXuanFromNet(Map<String, String> params) {
        iModel.checkZiXuanData(params, new Observer<ResBean<String>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResBean<String> resBean) {
                if (resBean.isSuccess()) {
                    iView.checkZiXuanToView((String) resBean.getData());
                } else {
                    iView.showErrorMsg(resBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
