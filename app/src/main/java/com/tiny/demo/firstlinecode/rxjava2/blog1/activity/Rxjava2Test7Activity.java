package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Rxjava2Test7Activity extends AppCompatActivity {

    private Subscription subscription;
    private Subscription subscription1;
    private Subscription subscription3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test7);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onViewBtn1Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 1000; j++) {
                    LogUtils.INSTANCE.e("emit " + j);
                    e.onNext(j);
                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.INSTANCE.e("Throwable: " + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e("onComplete");
                    }
                });
    }

    /**
     * 解决Observable发送消息太快的问题
     * BackpressureStrategy.DROP： 就是直接把存不下的事件丢弃
     * BackpressureStrategy.LATEST： Latest就是只保留最新的事件
     */

    @OnClick(R.id.btn_test2)
    public void onViewBtn2Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 1000000; j++) {
                    e.onNext(j);
                }
            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                        subscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.INSTANCE.e("onError:" + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e("onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test3)
    public void onViewBtn3Clicked() {
        if (subscription != null) {
            subscription.request(128);
        }
    }

    @OnClick(R.id.btn_test4)
    public void onViewBtn4Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 1000000; j++) {
                    e.onNext(j);
                }
            }
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                        subscription1 = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.INSTANCE.e("onError:" + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e("onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test5)
    public void onViewBtn5Clicked() {
        if (subscription1 != null) {
            subscription1.request(128);
        }
    }

    @OnClick(R.id.btn_test6)
    public void onViewBtn6Clicked() {
        Flowable.interval(1, TimeUnit.MICROSECONDS)
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                        subscription3 = s;
                        subscription3.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.INSTANCE.e("onNext:" + aLong);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.INSTANCE.e("onError:" + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e("onComplete");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription3 != null) {
            subscription3.cancel();
        }
    }
}
