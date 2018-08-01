package com.tiny.demo.firstlinecode.rxjava2.blog2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Desc: 条件操作符
 *
 * @author tiny
 * @date 2018/6/10 下午8:28
 */
public class Rxjava2Test15Activity extends AppCompatActivity {
    public static final String TAG = Rxjava2Test15Activity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Test15Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test15);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test12)
    public void onBtnTest12Clicked() {
        /**
         * 判断事件序列是否全部满足某个事件，如果都满足则返回 true，反之则返回 false。
         */
        Observable.just(1, 2, 3, 4)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 5;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.e(TAG, "all accept:" + aBoolean);
                    }
                });
        /**
         * 输出结果：
         * all accept:true
         */
    }

    @OnClick(R.id.btn_test13)
    public void onBtnTest13Clicked() {
        /**
         * 可以设置条件，当某个数据满足条件时就会发送该数据，反之则不发送。
         */
        Observable.just(1, 2, 3, 4)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 3;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "takeWhile accept:" + integer);
                    }
                });
        /**
         * 输出结果：
         * takeWhile accept:1
         takeWhile accept:2
         */
    }

    @OnClick(R.id.btn_test14)
    public void onBtnTest14Clicked() {
        /**
         * 可以设置条件，当某个数据满足条件时不发送该数据，反之则发送。
         */
        Observable.just(1, 2, 3, 4)
                .skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 3;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "skipWhile accept:" + integer);
                    }
                });
        /**
         * 输出结果：
         * skipWhile accept:3
         skipWhile accept:4
         */
    }

    @OnClick(R.id.btn_test15)
    public void onBtnTest15Clicked() {
        /**
         * 可以设置条件，当事件满足此条件时，下一次的事件就不会被发送了。
         */
        Observable.just(1, 2, 3, 4, 5, 6)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 3;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "takeUntil accept:" + integer);
                    }
                });
        /**
         * 输出结果：
         * takeUntil accept:1
         takeUntil accept:2
         takeUntil accept:3
         takeUntil accept:4
         */
    }

    @OnClick(R.id.btn_test16)
    public void onBtnTest16Clicked() {
        /**
         * 当 skipUntil() 中的 Observable 发送事件了，
         * 原来的 Observable 才会发送事件给观察者。
         */
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                .skipUntil(Observable.intervalRange(6, 5, 3, 1, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long integer) {
                        LogUtils.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
        /**
         * 输出结果：
         * onSubscribe
         onNext:4
         onNext:5
         onComplete
         */
    }

    @OnClick(R.id.btn_test17)
    public void onBtnTest17Clicked() {
        /**
         * 判断两个 Observable 发送的事件是否相同。
         */
        Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2, 3))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.e(TAG, "sequenceEqual accept:" + aBoolean);
                    }
                });
        /**
         * 输出结果：
         * sequenceEqual accept:true
         */
    }

    @OnClick(R.id.btn_test18)
    public void onBtnTest18Clicked() {
        /**
         * 判断事件序列中是否含有某个元素，如果有则返回 true，如果没有则返回 false。
         */
        Observable.just(1, 2, 3)
                .contains(3)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.e(TAG, "contains accept:" + aBoolean);
                    }
                });
        /**
         * 输出结果：
         * contains accept:true
         */
    }

    @OnClick(R.id.btn_test19)
    public void onBtnTest19Clicked() {
        /**
         * 判断事件序列是否为空。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onComplete();
            }
        })
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.e(TAG, "isEmpty accept:" + aBoolean);
                    }
                });
        /**
         * 输出结果：
         * isEmpty accept:true
         */
    }

    @OnClick(R.id.btn_test20)
    public void onBtnTest20Clicked() {
        /**
         * amb() 要传入一个 Observable 集合，
         * 但是只会发送最先发送事件的 Observable 中的事件，
         * 其余 Observable 将会被丢弃。
         */
        ArrayList<Observable<Long>> list = new ArrayList<>();
        list.add(Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS));
        list.add(Observable.intervalRange(6, 5, 0, 1, TimeUnit.SECONDS));
        Observable.amb(list)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.e(TAG, "amb accept:" + aLong);
                    }
                });
        /**
         * 输出结果：
         * amb accept:6
         amb accept:7
         amb accept:8
         amb accept:9
         amb accept:10
         */
    }

    @OnClick(R.id.btn_test21)
    public void onBtnTest21Clicked() {
        /**
         * 如果观察者只发送一个 onComplete() 事件，则可以利用这个方法发送一个值。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onComplete();
            }
        })
                .defaultIfEmpty(666)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "defaultIfEmpty accept:" + integer);
                    }
                });
        /**
         * 输出结果：
         * defaultIfEmpty accept:666
         */
    }
}
