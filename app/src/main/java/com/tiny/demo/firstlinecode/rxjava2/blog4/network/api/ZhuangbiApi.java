package com.tiny.demo.firstlinecode.rxjava2.blog4.network.api;

import com.tiny.demo.firstlinecode.rxjava2.blog4.model.ZhuangbiImage;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/6/17 下午6:22
 */
public interface ZhuangbiApi {

    @GET("search")
    Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
