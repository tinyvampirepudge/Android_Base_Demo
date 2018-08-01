package com.tiny.demo.firstlinecode.rxjava2.blog1.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/29.
 * Version:
 */

public class UserInfo {
    UserBaseInfoResponse mBaseInfo;
    UserExtraInfoResponse mExtraInfol;

    public UserInfo(UserBaseInfoResponse mBaseInfo, UserExtraInfoResponse mExtraInfol) {
        this.mBaseInfo = mBaseInfo;
        this.mExtraInfol = mExtraInfol;
    }
}
