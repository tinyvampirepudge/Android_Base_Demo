package com.tiny.demo.firstlinecode.stetho.httphelper;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tiny on 17/2/22.
 */

public class OkHttpClientUtils {
    private static boolean showLog = false;

    private OkHttpClientUtils() {
    }

    /**
     * 暂时未实现单例。只是简单的封装，为了方便打印log.
     *
     * @return
     */
    public static OkHttpClient getInstance() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                                .addHeader("Accept-Encoding", "gzip, deflate")
//                                .addHeader("Connection", "keep-alive")
//                                .addHeader("Accept", "*/*")
//                                .addHeader("Cookie", "add cookies here")
                                .build();
                        return chain.proceed(request);
                    }
                });

        builder = addParamsToClient(builder);

        //stetho
        builder.addNetworkInterceptor(new StethoInterceptor());

        OkHttpClient httpClient = builder.build();
        return httpClient;
    }

    /**
     * 给okhttp添加一些数据。
     *
     * @param builder
     * @return
     */
    public static OkHttpClient.Builder addParamsToClient(OkHttpClient.Builder builder) {
        return builder.cookieJar(new MyCookieManager());
    }

    public static void setShowLog(boolean showLog) {
        OkHttpClientUtils.showLog = showLog;
    }


    /**
     * log拦截器
     */
    private static class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();

            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }
}
