package com.tiny.demo.firstlinecode.rxjava2.blog4.module.token_adavnced_retrywhen_5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.rxjava2.BaseRxFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.FakeThing;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.FakeToken;
import com.tiny.demo.firstlinecode.rxjava2.blog4.network.NetWork;
import com.tiny.demo.firstlinecode.rxjava2.blog4.network.api.FakeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Desc:    flatMap高级用法。
 *
 * @author tiny
 * @date 2018/6/18 上午2:22
 */
public class TokenAdvancedFragment extends BaseRxFragment {
    @BindView(R.id.tokenTv)
    TextView tokenTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    final FakeToken cachedFakeToken = new FakeToken(true);
    boolean tokenUpdated;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token_advanced, container, false);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @OnClick(R.id.invalidateTokenBt)
    void invalidateToken() {
        cachedFakeToken.expired = true;
        ToastUtils.showSingleToast(getString(R.string.token_destroyed));
    }

    @OnClick(R.id.requestBt)
    void upload() {
        tokenUpdated = false;
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        FakeApi fakeApi = NetWork.getFakeApi();
        disposable = Observable.just(1)
                .flatMap(new Function<Integer, ObservableSource<FakeThing>>() {
                    @Override
                    public ObservableSource<FakeThing> apply(Integer integer) throws Exception {
                        LogUtils.INSTANCE.e("cachedFakeToken.token == null:" + (cachedFakeToken.token == null));
                        return cachedFakeToken.token == null
                                ? Observable.error(new NullPointerException("Token is null!"))
                                : fakeApi.getFakeData(cachedFakeToken);
                    }
                })
                /**
                 * 当被观察者接收到异常或者错误事件时会回调该方法，这个方法会返回一个新的被观察者。
                 * 如果返回的被观察者发送 Error 事件则之前的被观察者不会继续发送事件，
                 * 如果发送正常事件则之前的被观察者会继续不断重试发送事件。
                 */
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                if (throwable instanceof IllegalArgumentException || throwable instanceof NullPointerException) {
                                    LogUtils.INSTANCE.e("ours throwable:" + throwable.toString());
                                    return fakeApi.getFakeToken("fake_auth_code")
                                            .doOnNext(new Consumer<FakeToken>() {
                                                @Override
                                                public void accept(FakeToken fakeToken) throws Exception {
                                                    LogUtils.INSTANCE.e("doOnNext");
                                                    tokenUpdated = true;
                                                    cachedFakeToken.token = fakeToken.token;
                                                    cachedFakeToken.expired = fakeToken.expired;
                                                }
                                            });
                                }
                                LogUtils.INSTANCE.e("others throwable:" + throwable.toString());
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FakeThing>() {
                    @Override
                    public void accept(FakeThing fakeThing) throws Exception {
                        LogUtils.INSTANCE.e("onNext");
                        swipeRefreshLayout.setRefreshing(false);
                        String token = cachedFakeToken.token;
                        if (tokenUpdated) {
                            token += "(" + getString(R.string.updated) + ")";
                        }
                        tokenTv.setText(getString(R.string.got_token_and_data, token, fakeThing.id, fakeThing.name));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.INSTANCE.e("onError:" + throwable.toString());
                        swipeRefreshLayout.setRefreshing(false);
                        ToastUtils.showSingleToast(getString(R.string.loading_failed));
                    }
                });
    }


    @Override
    protected int getDialogRes() {
        return R.layout.dialog_token_advanced;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_token_advanced;
    }
}
