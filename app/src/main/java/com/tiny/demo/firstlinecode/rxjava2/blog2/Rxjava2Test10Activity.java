package com.tiny.demo.firstlinecode.rxjava2.blog2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Desc: RxJava 只看这一篇文章就够了 (上)
 * https://mp.weixin.qq.com/s?__biz=MzIwMTAzMTMxMg==&mid=2649492706&idx=1&sn=d7d213a1db9c8ae3a5b0525d45863518&chksm=8eec871db99b0e0bc4d4d1aa2b7ed5d7c32e5299aee0f0818a798c2deb2996f40f8971c7a6a2&scene=38#wechat_redirect
 *  创建操作符
 * @author tiny
 * @date 2018/6/6 下午3:04
 */
public class Rxjava2Test10Activity extends AppCompatActivity {
    public static final String TAG = Rxjava2Test10Activity.class.getSimpleName();

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.btn_test3)
    Button btnTest3;
    @BindView(R.id.btn_test4)
    Button btnTest4;
    @BindView(R.id.btn_test5)
    Button btnTest5;
    @BindView(R.id.btn_test6)
    Button btnTest6;
    @BindView(R.id.btn_test7)
    Button btnTest7;
    @BindView(R.id.btn_test8)
    Button btnTest8;
    @BindView(R.id.btn_test9)
    Button btnTest9;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Test10Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test10);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        //1、创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e(TAG, "subscribe");
                ThreadUtils.logCurrThreadName(TAG);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //2、创建观察者
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                LogUtils.e(TAG, "onNext:" + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "onComplete");
            }
        };
        //3、订阅
        observable.subscribe(observer);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        //创建一个被观察者
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello World!");
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                LogUtils.e(TAG, "onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "onComplete");
            }
        });
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        //发送的事件不可以超过10个以上
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        //这个方法和 just() 类似，只不过 fromArray 可以传入多于10个的变量，
        // 并且可以传入一个数组。
        Integer[] array = new Integer[20];
        for (int j = 0; j < 20; j++) {
            array[j] = j + 1;
        }
        Observable.fromArray(array)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        /**
         * 这里的 Callable 是 java.util.concurrent 中的 Callable，
         * Callable 和 Runnable 的用法基本一致，只是它会返回一个结果值，
         * 这个结果值就是发给观察者的。
         */
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "onComplete");
            }
        });
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        /**
         * 参数中的 Future 是 java.util.concurrent 中的 Future，
         * Future 的作用是增加了 cancel() 等方法操作 Callable，
         * 它可以通过 get() 方法来获取 Callable 返回的值。
         */
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                LogUtils.e(TAG, "CallableDemo is Running");
                return "返回结果";
            }
        });
        Observable.fromFuture(futureTask)
                //doOnSubscribe() 的作用就是只有订阅时才会发送事件
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        futureTask.run();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.e(TAG, "accept:" + s);
            }
        });
    }

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        /**
         * 直接发送一个 List 集合数据给观察者
         */
        List<String> list = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            list.add("list:" + (j + 1));
        }
        Observable.fromIterable(list)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e(TAG, "onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    private Integer i = 100;

    @OnClick(R.id.btn_test8)
    public void onBtnTest8Clicked() {
        /**
         * 直到被观察者被订阅后才会创建被观察者。
         */
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        i = 200;
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "onComplete");
            }
        };
        observable.subscribe(observer);
        i = 300;
        observable.subscribe(observer);
    }

    @OnClick(R.id.btn_test9)
    public void onBtnTest9Clicked() {
        /**
         * 当到指定时间后就会发送一个 0L 的值给观察者。
         */
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.e(TAG, "onNext:" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test10)
    public void onBtnTest10Clicked() {
        /**
         * 每隔一段时间就会发送一个事件，这个事件是从0开始，不断增1的数字。
         */
        Observable.interval(4, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.e(TAG, "onNext:" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });

        /**
         * 从时间就可以看出每隔4秒就会发出一次数字递增1的事件。
         * 这里说下 interval() 第三个方法的 initialDelay 参数，
         * 这个参数的意思就是 onSubscribe 回调之后，再次回调 onNext 的间隔时间。
         */
    }

    @OnClick(R.id.btn_test11)
    public void onBtnTest11Clicked() {
        /**
         * 可以指定发送事件的开始值和数量，其他与 interval() 的功能一样。
         */
        Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.e(TAG, "onNext:" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test12)
    public void onBtnTest12Clicked() {
        /**
         * 同时发送一定范围的事件序列。
         */
        Observable.range(2, 10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test13)
    public void onBtnTest13Clicked() {
        /**
         * 作用与 range() 一样，只是数据类型为 Long
         */
        Observable.rangeLong(10L, 20L)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.e(TAG, "onNext:" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test14)
    public void onBtnTest14Clicked() {
        /**
         * empty() ： 直接发送 onComplete() 事件
         * never()：不发送任何事件
         * error()：发送 onError() 事件
         */
        Observable.empty()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtils.e(TAG, "onNext:" + o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test15)
    public void onBtnTest15Clicked() {
        Observable.never()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtils.e(TAG, "onNext:" + o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test16)
    public void onBtnTest16Clicked() {
        Observable.error(new Exception("Error Exception"))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtils.e(TAG, "onNext:" + o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }
}
