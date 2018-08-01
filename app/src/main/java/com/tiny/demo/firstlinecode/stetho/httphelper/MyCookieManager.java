package com.tiny.demo.firstlinecode.stetho.httphelper;

import com.tiny.demo.firstlinecode.app.FLCApplication;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by tiny on 17/3/10.
 * ok3的cookie管理器。
 */

public class MyCookieManager implements CookieJar {
    private final MyPersistentCookieStore cookieStore = new MyPersistentCookieStore(FLCApplication.instance());

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                cookieStore.add(url, cookie);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        //返回的List<Cookie>会被添加进请求。
        List<Cookie> cookies = cookieStore.get(url);
        if (cookies == null) {
            cookies = new ArrayList<>();
        }
        return cookies;
    }
}
