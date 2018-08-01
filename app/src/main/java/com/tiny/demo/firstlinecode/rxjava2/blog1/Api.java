package com.tiny.demo.firstlinecode.rxjava2.blog1;


import com.tiny.demo.firstlinecode.rxjava2.blog1.entity.LoginRequest;
import com.tiny.demo.firstlinecode.rxjava2.blog1.entity.LoginResponse;
import com.tiny.demo.firstlinecode.rxjava2.blog1.entity.RegisterRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Desc:
 * Created by tiny on 2017/12/29.
 * Time: 22:13
 * Version:
 */

public interface Api {
    @GET
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET
    Observable<RegisterRequest> register(@Body RegisterRequest registerRequest);
}
