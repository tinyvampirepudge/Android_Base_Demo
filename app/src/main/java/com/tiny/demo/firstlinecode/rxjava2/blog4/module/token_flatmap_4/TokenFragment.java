package com.tiny.demo.firstlinecode.rxjava2.blog4.module.token_flatmap_4;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.rxjava2.BaseRxFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.FakeThing;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.FakeToken;
import com.tiny.demo.firstlinecode.rxjava2.blog4.network.NetWork;
import com.tiny.demo.firstlinecode.rxjava2.blog4.network.api.FakeApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/6/18 上午2:00
 */
public class TokenFragment extends BaseRxFragment {
    @BindView(R.id.tokenTv)
    TextView tokenTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token, container, false);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setRefreshing(false);
        return view;
    }

    @OnClick(R.id.requestBt)
    void upload() {
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        FakeApi fakeApi = NetWork.getFakeApi();
        disposable = fakeApi.getFakeToken("fake_quth_code")
                .flatMap(new Function<FakeToken, ObservableSource<FakeThing>>() {
                    @Override
                    public ObservableSource<FakeThing> apply(FakeToken fakeToken) throws Exception {
                        return fakeApi.getFakeData(fakeToken);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FakeThing>() {
                    @Override
                    public void accept(FakeThing fakeThing) throws Exception {
                        ThreadUtils.logCurrThreadName("onNext");
                        swipeRefreshLayout.setRefreshing(false);
                        tokenTv.setText(getString(R.string.got_data, fakeThing.id, fakeThing.name));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ThreadUtils.logCurrThreadName("onError");
                        swipeRefreshLayout.setRefreshing(false);
                        ToastUtils.showSingleToast(getString(R.string.loading_failed));
                    }
                });
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_token;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_token;
    }
}
