package com.tiny.demo.firstlinecode.rxjava2.blog1.myretrofit;


import com.tiny.demo.firstlinecode.common.bean.ResBean;
import com.tiny.demo.firstlinecode.rxjava2.blog1.myretrofit.bean.KingRegionBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Desc:
 * Created by tiny on 2017/12/27.
 * Version:
 */

public interface MyApi {
    @GET(UrlConfig.URL_KING_REGION)
    Observable<ResBean<KingRegionBean>> getKingRegionData(@QueryMap Map<String, String> para);
}
