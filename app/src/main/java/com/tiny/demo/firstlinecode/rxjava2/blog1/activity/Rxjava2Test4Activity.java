package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tinytongtong.tinyutils.ThreadUtils;

import java.io.InterruptedIOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class Rxjava2Test4Activity extends AppCompatActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;

    //rxava2的坑
    static {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof InterruptedIOException) {
                    LogUtils.e("Io interrupted");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test4);
        ButterKnife.bind(this);
    }

    /**
     * Zip通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件.
     * 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。
     */
    @OnClick(R.id.btn_test1)
    public void onViewBtn1Clicked() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emit) throws Exception {
                LogUtils.e("emit 1");
                emit.onNext(1);
                LogUtils.e("emit 2");
                emit.onNext(2);
                LogUtils.e("emit 3");
                emit.onNext(3);
                LogUtils.e("emit 4");
                emit.onNext(4);
                LogUtils.e("emit complete1");
                emit.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emit) throws Exception {
                LogUtils.e("emit A");
                emit.onNext("A");
                LogUtils.e("emit B");
                emit.onNext("B");
                LogUtils.e("emit C");
                emit.onNext("D");
                LogUtils.e("emit complete2");
                emit.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                LogUtils.e("onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError");
            }

            @Override
            public void onComplete() {
                LogUtils.e("onComplete");
            }
        });
    }

    /**
     * 同一个线程中执行是有序的。默认是主线程
     */
    @OnClick(R.id.btn_test2)
    public void onViewBtn2Clicked() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emit) throws Exception {
                ThreadUtils.logCurrThreadName("observable1");
                LogUtils.e("emit 1");
                emit.onNext(1);
                Thread.sleep(1000);

                LogUtils.e("emit 2");
                emit.onNext(2);
                Thread.sleep(1000);

                LogUtils.e("emit 3");
                emit.onNext(3);
                Thread.sleep(1000);

                LogUtils.e("emit 4");
                emit.onNext(4);
                Thread.sleep(1000);

                LogUtils.e("emit complete1");
                emit.onComplete();
            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emit) throws Exception {
                ThreadUtils.logCurrThreadName("observable2");
                LogUtils.e("emit A");
                emit.onNext("A");
                Thread.sleep(1000);

                LogUtils.e("emit B");
                emit.onNext("B");
                Thread.sleep(1000);

                LogUtils.e("emit C");
                emit.onNext("D");
                Thread.sleep(1000);

                LogUtils.e("emit complete2");
                emit.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                ThreadUtils.logCurrThreadName("subscribe");
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                LogUtils.e("onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError");
            }

            @Override
            public void onComplete() {
                LogUtils.e("onComplete");
            }
        });
    }

    /**
     * 不同线程中操作。
     */
    @OnClick(R.id.btn_test3)
    public void onViewBtn3Clicked() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emit) throws Exception {
                ThreadUtils.logCurrThreadName("observable1");
                LogUtils.e("emit 1");
                emit.onNext(1);
                Thread.sleep(1000);

                LogUtils.e("emit 2");
                emit.onNext(2);
                Thread.sleep(1000);

                LogUtils.e("emit 3");
                emit.onNext(3);
                Thread.sleep(1000);

                LogUtils.e("emit 4");
                emit.onNext(4);
                Thread.sleep(1000);

                LogUtils.e("emit complete1");
                emit.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emit) throws Exception {
                ThreadUtils.logCurrThreadName("observable2");
                LogUtils.e("emit A");
                emit.onNext("A");
                Thread.sleep(1000);

                LogUtils.e("emit B");
                emit.onNext("B");
                Thread.sleep(1000);

                LogUtils.e("emit C");
                emit.onNext("D");
                Thread.sleep(1000);

                LogUtils.e("emit complete2");
                emit.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                ThreadUtils.logCurrThreadName("subscribe");
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                LogUtils.e("onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError");
            }

            @Override
            public void onComplete() {
                LogUtils.e("onComplete");
            }
        });
    }
}
