package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Rxjava2Test8Activity extends AppCompatActivity {

    @BindView(R.id.tv_file)
    TextView tvFile;
    private Subscription subscription;
    private Subscription subscription1;
    private Subscription subscription2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test8);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emit) throws Exception {
                for (int j = 1; j < 4; j++) {
                    LogUtils.INSTANCE.e("emit 1");
                    emit.onNext(j);
                }
                LogUtils.INSTANCE.e("emit complete");
                emit.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                        subscription = s;
                        subscription.request(1);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext: " + integer);
                        subscription.request(1);
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

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emit) throws Exception {
                LogUtils.INSTANCE.e("before emit, requested: " + emit.requested());
                for (int j = 1; j < 4; j++) {
                    LogUtils.INSTANCE.e("emit 1");
                    emit.onNext(j);
                    LogUtils.INSTANCE.e("after emit, requested: " + emit.requested());
                }

                LogUtils.INSTANCE.e("emit complete");
                emit.onComplete();
                LogUtils.INSTANCE.e("after emit, requested: " + emit.requested());
            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                        s.request(10);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext: " + integer);
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
    public void onBtnTest3Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emit) throws Exception {
                LogUtils.INSTANCE.e("before emit, requested: " + emit.requested());
//                for (int j = 1; j < 4; j++) {
//                    LogUtils.INSTANCE.e("emit 1");
//                    emit.onNext(j);
//                    LogUtils.INSTANCE.e("after emit, requested: " + emit.requested());
//                }
//
//                LogUtils.INSTANCE.e("emit complete");
//                emit.onComplete();
//                LogUtils.INSTANCE.e("after emit, requested: " + emit.requested());
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                        s.request(1000);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext: " + integer);
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

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                LogUtils.INSTANCE.e("First requested = " + e.requested());
                boolean flag;
                for (int j = 0; ; j++) {
                    flag = false;
                    while (e.requested() == 0) {
                        if (!flag) {
                            LogUtils.INSTANCE.e("Oh no! I can't emit value!");
                            flag = true;
                        }
                    }
                    e.onNext(j);
                    LogUtils.INSTANCE.e("emit " + j + " , requested = " + e.requested());
                }
            }
        }, BackpressureStrategy.ERROR)
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
    public void onBtnTest5Clicked() {
        if (subscription1 != null) {
            subscription1.request(96);
        }
    }

    /**
     * 读取文件
     */
    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
//                String fileName = "regions_list.json";
                String fileName = "test.txt";
                AssetManager assetManager = getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        assetManager.open(fileName)));
                String line;
                while ((line = bf.readLine()) != null && !e.isCancelled()) {
                    while (e.requested() == 0) {
                        if (e.isCancelled()) {
                            break;
                        }
                    }
                    e.onNext(line);
                }

                bf.close();
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                        subscription2 = s;
                        subscription2.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.INSTANCE.e("onNext: " + s);
                        tvFile.append(s + "\n");
                        subscription2.request(1);
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
}
