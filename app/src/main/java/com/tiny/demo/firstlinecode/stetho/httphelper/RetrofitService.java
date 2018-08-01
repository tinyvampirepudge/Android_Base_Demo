package com.tiny.demo.firstlinecode.stetho.httphelper;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Tww on 2017/02/07.
 * retrofit代理
 */
public interface RetrofitService {

    //使用ResponseBody格式自己来解析，兼容不同的数据格式。
    @GET(UrlConfig.Business.URL_HOME_PAGE)
    Observable<ResponseBody> getHomepageDataCompat(@QueryMap Map<String, String> params);

    @GET(UrlConfig.Optional.URL_OPTIONAL_RESORT)
    Observable<ResponseBody> getOptionalResortData(@QueryMap Map<String, String> params);

    @GET("path")
    Call<ResponseBody> getData();
}
