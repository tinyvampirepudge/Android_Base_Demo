package com.tiny.demo.firstlinecode.stetho.httphelper;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tww on 2017/02/07.
 * 网络请求帮助类
 */
public class HttpHelper {
    private static volatile HttpHelper singleton;

    private HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (singleton == null) {
            synchronized (HttpHelper.class) {
                if (singleton == null) {
                    singleton = new HttpHelper();
                }
            }
        }
        return singleton;
    }


    /**
     * 得到retrofit代理接口
     *
     * @return
     */
    private RetrofitService getRetrofitService() {
        return getRetrofit().create(RetrofitService.class);
    }

    /**
     * 得到retrofit对象
     *
     * @return
     */
    @NonNull
    private Retrofit getRetrofit() {
        return new Retrofit
                .Builder()
                //配置基础的url
                .baseUrl(UrlConfig.Base.getBaseUrl())
                //配置提交或者返回的参数的造型方式为gson
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                //返回值可以使用Obserable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用https时需要配置
                .client(OkHttpClientUtils.getInstance())
                .build();
    }

    /**
     * business method
     */

    /**
     * 请求首页的数据的数据
     *
     * @param params
     * @param observer
     */
    public void getHomePageDataCompat(Map<String, String> params, Observer<ResponseBody> observer) {
        getRetrofitService()
                .getHomepageDataCompat(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 自选编辑页面，重新排序接口
     *
     * @param params
     * @param observer
     */
    public void getOptionalResortData(Map<String, String> params, Observer<ResponseBody> observer) {
        getRetrofitService()
                .getOptionalResortData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
