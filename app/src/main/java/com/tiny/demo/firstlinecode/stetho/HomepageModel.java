package com.tiny.demo.firstlinecode.stetho;

import com.tiny.demo.firstlinecode.stetho.httphelper.HttpHelper;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


/**
 * Created by tiny on 17/3/24.
 */

public class HomepageModel implements StethoContract.IModel {
    @Override
    public void getHomePageDataCompat(Map<String, String> params,
                                      Observer<ResponseBody> observer) {
        HttpHelper.getInstance().getHomePageDataCompat(params, observer);
    }
}
