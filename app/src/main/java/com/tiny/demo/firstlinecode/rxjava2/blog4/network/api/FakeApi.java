package com.tiny.demo.firstlinecode.rxjava2.blog4.network.api;

import com.tiny.demo.firstlinecode.rxjava2.blog4.model.FakeThing;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.FakeToken;
import com.tinytongtong.tinyutils.ThreadUtils;

import java.util.Random;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/6/17 下午6:22
 */
public class FakeApi {
    Random random = new Random();

    public Observable<FakeToken> getFakeToken(@Nonnull String fakeAuth) {
        return Observable.just(fakeAuth)
                .map(new Function<String, FakeToken>() {
                    @Override
                    public FakeToken apply(String fakeAuth) throws Exception {
                        ThreadUtils.INSTANCE.logCurrThreadName("getFakeToken");
                        //Add some random delay to mock the network delay
                        int fakeNetworkTimeCost = random.nextInt(500) + 500;
                        try {
                            Thread.sleep(fakeNetworkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

//                        if (true) {
//                            throw new NumberFormatException("getFakeToken Failed");
//                        }

                        FakeToken fakeToken = new FakeToken();
                        fakeToken.token = createToken();
                        return fakeToken;
                    }
                });
    }

    private static String createToken() {
        return "fake_token_" + System.currentTimeMillis() % 10000;
    }

    public Observable<FakeThing> getFakeData(FakeToken fakeToken) {
        return Observable.just(fakeToken)
                .map(new Function<FakeToken, FakeThing>() {
                    @Override
                    public FakeThing apply(FakeToken fakeToken) throws Exception {
                        ThreadUtils.INSTANCE.logCurrThreadName("getFakeData");
                        //Add some random delay to mock the network delay
                        int frameNetworkTimeCost = random.nextInt(500) + 500;
                        try {
                            Thread.sleep(frameNetworkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (fakeToken.expired) {
                            throw new IllegalArgumentException("Token expired!");
                        }

//                        if (true) {
//                            throw new NumberFormatException("getFakeData Failed");
//                        }

                        FakeThing fakeThing = new FakeThing();
                        fakeThing.id = (int) (System.currentTimeMillis() % 10000);
                        fakeThing.name = "FAKE_USER_" + fakeThing.id;
                        return fakeThing;
                    }
                });
    }
}
