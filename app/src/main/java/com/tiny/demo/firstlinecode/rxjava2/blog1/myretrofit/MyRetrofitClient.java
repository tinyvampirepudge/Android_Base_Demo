package com.tiny.demo.firstlinecode.rxjava2.blog1.myretrofit;


import com.tiny.demo.firstlinecode.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Desc:
 * Created by tiny on 2017/12/27.
 * Version:
 */

public class MyRetrofitClient {
    private MyRetrofitClient() {

    }

    private static Retrofit create() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return new Retrofit.Builder().baseUrl(UrlConfig.BASE_URL_ONLINE)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public enum RetrofitEnum {
        RetrofitInstance;

        private Retrofit instance = null;

        RetrofitEnum() {
            instance = create();
        }

        public Retrofit getInstance() {
            return instance;
        }
    }
}
