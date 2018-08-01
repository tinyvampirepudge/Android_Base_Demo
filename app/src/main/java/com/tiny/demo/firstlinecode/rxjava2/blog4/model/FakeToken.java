// (c)2016 Flipboard Inc, All Rights Reserved.

package com.tiny.demo.firstlinecode.rxjava2.blog4.model;

public class FakeToken {
    public String token;
    public boolean expired;

    public FakeToken() {
    }

    public FakeToken(boolean expired) {
        this.expired = expired;
    }
}
