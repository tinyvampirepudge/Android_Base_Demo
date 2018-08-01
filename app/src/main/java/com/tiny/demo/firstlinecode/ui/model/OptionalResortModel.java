package com.tiny.demo.firstlinecode.ui.model;


import com.tiny.demo.firstlinecode.stetho.httphelper.HttpHelper;
import com.tiny.demo.firstlinecode.ui.contract.OptionalResortContract;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


/**
 * Created by tiny on 17/6/5.
 */

public class OptionalResortModel implements OptionalResortContract.IModel {

    @Override
    public void getNetData(Map<String, String> params, Observer<ResponseBody> observer) {
        HttpHelper.getInstance().getOptionalResortData(params, observer);
    }
}
