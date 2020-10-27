package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Rxjava2Test5Activity extends AppCompatActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.btn_test3)
    Button btnTest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test5);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        LogUtils.INSTANCE.e("onBtnTest1Clicked");
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                //无限循环发送事件
                for (int j = 0; ; j++) {
                    LogUtils.INSTANCE.e("e.onNext(" + j + ")");
                    e.onNext(j);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + " --> " + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e("onSubscribe");
                    }

                    @Override
                    public void onNext(String value) {
                        LogUtils.INSTANCE.e("onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e("onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e("onComplete");
                    }
                });
    }

    /**
     * 上下游在同一个线程
     */
    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int j = 0; ; j++) {
                    e.onNext(j);
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.INSTANCE.e("onNext --> " + integer);
                Thread.sleep(2000);
            }
        });
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int j = 0; ; j++) {
                    e.onNext(j);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.INSTANCE.e("onNext --> " + integer);
                        Thread.sleep(2000);
                    }
                });
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 1000; j++) {
                    e.onNext(j);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 10 == 0;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.INSTANCE.e("onNext --> " + integer);
                    }
                });
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 1000; j++) {
                    e.onNext(j);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .sample(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.INSTANCE.e("onNext --> " + integer);
                    }
                });
    }
}
