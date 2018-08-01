package com.tiny.demo.firstlinecode.rxjava2.blog4.network.api;

import com.tiny.demo.firstlinecode.rxjava2.blog4.model.GankBeautyResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/6/17 下午6:22
 */
public interface GankApi {

    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties(@Path("number") int number,@Path("page") int page);
}
