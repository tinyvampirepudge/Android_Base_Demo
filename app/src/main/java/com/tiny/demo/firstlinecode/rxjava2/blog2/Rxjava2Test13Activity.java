package com.tiny.demo.firstlinecode.rxjava2.blog2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ThreadUtils;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Desc: 功能操作符
 *
 * @author tiny
 * @date 2018/6/9 上午1:25
 */
public class Rxjava2Test13Activity extends AppCompatActivity {
    public static final String TAG = Rxjava2Test13Activity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Test13Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test13);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test14)
    public void onBtnTest14Clicked() {
        /**
         * 延迟一段事件发送事件。
         */
        Observable.just(1, 2, 3)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test15)
    public void onBtnTest15Clicked() {
        /**
         * Observable 每发送一件事件之前都会先回调这个方法。
         * onNext()、onComplete()、onError()方法之前都会调用。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
//                emitter.onError(new NumberFormatException("你愁啥"));
                emitter.onComplete();
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnEach:" + integerNotification.getValue());
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });

        /**
         * 输出结果：
         * onSubscribe
         * doOnEach:1
         * onNext:1
         * doOnEach:2
         * onNext:2
         * doOnEach:3
         * onNext:3
         * doOnEach:null
         * onComplete
         */

        /**
         * 从结果就可以看出每发送一个事件之前都会回调 doOnEach 方法，
         * 并且可以取出 onNext() 发送的值。
         */
    }

    @OnClick(R.id.btn_test16)
    public void onBtnTest16Clicked() {
        /**
         * Observable 每发送 onNext() 之前都会先回调这个方法。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnNext:" + integer);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * onSubscribe
         doOnNext:1
         onNext:1
         doOnNext:2
         onNext:2
         doOnNext:3
         onNext:3
         onComplete
         */
    }

    @OnClick(R.id.btn_test17)
    public void onBtnTest17Clicked() {
        /**
         * Observable 每发送 onNext() 之后都会回调这个方法。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.INSTANCE.e(TAG, "doAfterNext:" + integer);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });

        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         doAfterNext:1
         onNext:2
         doAfterNext:2
         onNext:3
         doAfterNext:3
         onComplete
         */
    }

    @OnClick(R.id.btn_test18)
    public void onBtnTest18Clicked() {
        /**
         * Observable 每发送 onComplete() 之前都会回调这个方法。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnComplete");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         doOnComplete
         onComplete
         */
    }

    @OnClick(R.id.btn_test19)
    public void onBtnTest19Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new NumberFormatException("你好肤浅"));
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnError");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         *onSubscribe
         onNext:1
         onNext:2
         onNext:3
         doOnError
         onError:java.lang.NumberFormatException: 你好肤浅
         */
    }

    @OnClick(R.id.btn_test20)
    public void onBtnTest20Clicked() {
        /**
         * Observable 每发送 onSubscribe() 之前都会回调这个方法。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnSubscribe");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * doOnSubscribe
         onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onComplete
         */
    }

    @OnClick(R.id.btn_test21)
    public void onBtnTest21Clicked() {
        /**
         * 当调用 Disposable 的 dispose() 之后回调该方法。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnDispose()");
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
                this.disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
                disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         doOnDispose()
         */
    }

    @OnClick(R.id.btn_test22)
    public void onBtnTest22Clicked() {
        /**
         * 在回调 onSubscribe 之前回调该方法的第一个参数的回调方法，
         * 可以使用该回调方法决定是否取消订阅。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnLifecycle(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnLifecycle accept");
//                disposable.dispose();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnLifecycle Action");
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnDispose Action");
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
                this.disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
                disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * doOnLifecycle accept
         onSubscribe
         onNext:1
         doOnDispose Action
         doOnLifecycle Action
         */
        /**
         * 可以看到当在 onNext() 方法进行取消订阅操作后，doOnDispose() 和 doOnLifecycle() 都会被回调。
         */

        /**
         * 如果使用 doOnLifecycle 进行取消订阅，来看看打印结果：
         *doOnLifecycle accept
         onSubscribe
         */
    }

    @OnClick(R.id.btn_test23)
    public void onBtnTest23Clicked() {
        /**
         * doOnTerminate 是在 onError 或者 onComplete 发送之前回调，
         * 而 doAfterTerminate 则是 onError 或者 onComplete 发送之后回调。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
//                emitter.onError(new NumberFormatException("你选择遗忘的，是我最不舍得。"));
                emitter.onComplete();
            }
        }).doOnTerminate(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnTerminate Action");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         doOnTerminate Action
         onError:java.lang.NumberFormatException: 你选择遗忘的，是我最不舍得。
         */
    }

    @OnClick(R.id.btn_test24)
    public void onBtnTest24Clicked() {
        /**
         * doOnTerminate 是在 onError 或者 onComplete 发送之前回调，
         * 而 doAfterTerminate 则是 onError 或者 onComplete 发送之后回调。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new NumberFormatException("纸短情长"));
                emitter.onComplete();
            }
        }).doAfterTerminate(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doAfterTerminate Action");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         *onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onError:java.lang.NumberFormatException: 纸短情长
         doAfterTerminate Action
         */
    }

    @OnClick(R.id.btn_test25)
    public void onBtnTest25Clicked() {
        /**
         * 在所有事件发送完毕之后回调该方法。
         *
         * 这里可能你会有个问题，那就是 doFinally() 和 doAfterTerminate() 到底有什么区别？
         * 区别就是在于取消订阅，如果取消订阅之后 doAfterTerminate() 就不会被回调，
         * 而 doFinally() 无论怎么样都会被回调，且都会在事件序列的最后。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doFinally Action");
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doOnDispose Action");
            }
        }).doAfterTerminate(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.INSTANCE.e(TAG, "doAfterTerminate Action");
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
                this.d = d;
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
//                d.dispose();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });

        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         doOnDispose Action
         doFinally Action
         */

        /**
         * 可以看到如果调用了 dispose() 方法，doAfterTerminate() 不会被回调。
         * 现在试试把 dispose() 注释掉看看，看看打印结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onComplete
         doAfterTerminate Action
         doFinally Action
         */

        /**
         * doAfterTerminate() 已经成功回调，doFinally() 还是会在事件序列的最后。
         */
    }

    @OnClick(R.id.btn_test26)
    public void onBtnTest26Clicked() {
        /**
         * 当接受到一个 onError() 事件之后回调，返回的值会回调 onNext() 方法，
         * 并正常结束该事件序列。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new NumberFormatException("宝宝不开心"));
                emitter.onNext(4);
                emitter.onComplete();
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                LogUtils.INSTANCE.e(TAG, "onErrorReturn apply throwable:" + throwable);
                return 404;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onErrorReturn apply throwable:java.lang.NumberFormatException: 宝宝不开心
         onNext:404
         onComplete
         */
    }

    @OnClick(R.id.btn_test27)
    public void onBtnTest27Clicked() {
        /**
         * 当接收到 onError() 事件时，返回一个新的 Observable，并正常结束事件序列。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new NumberFormatException("我们一起学喵叫，一起喵喵喵喵喵"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                LogUtils.INSTANCE.e(TAG, "onErrorResumeNext apply throwable:" + throwable);
                return Observable.just(4, 5, 6);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });

        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onErrorResumeNext apply throwable:java.lang.NumberFormatException: 我们一起学喵叫，一起喵喵喵喵喵
         onNext:4
         onNext:5
         onNext:6
         onComplete
         */
    }

    @OnClick(R.id.btn_test28)
    public void onBtnTest28Clicked() {
        /**
         * 与 onErrorResumeNext() 作用基本一致，但是这个方法只能捕捉 Exception。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
//                emitter.onError(new Error("404"));//无法捕捉到
                emitter.onError(new Exception("505"));
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(333);
                observer.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });
        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onNext:333
         onComplete
         */
    }

    @OnClick(R.id.btn_test29)
    public void onBtnTest29Clicked() {
        /**
         * 如果出现错误事件，则会重新发送所有事件序列。times 是代表重新发的次数。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Exception("王蛋蛋想吃鸡腿"));
            }
        })
                .retry(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e(TAG, "onComplete");
                    }
                });

        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onNext:1
         onNext:2
         onNext:3
         onNext:1
         onNext:2
         onNext:3
         onError:java.lang.Exception: 王蛋蛋想吃鸡腿
         */
    }

    @OnClick(R.id.btn_test30)
    public void onBtnTest30Clicked() {
        /**
         * 出现错误事件之后，可以通过此方法判断是否继续发送事件。
         */
        final int[] i = {0};
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Exception("404"));
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onNext(6);
                emitter.onComplete();
            }
        })
                .retryUntil(new BooleanSupplier() {
                    /**
                     *
                     * @return false表示继续从头发送事件序列，true表示不继续，然后调用onError方法。
                     * @throws Exception
                     */
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        LogUtils.INSTANCE.e(TAG, "retryUntil getAsBoolean i[0]:" + i[0]);
                        if (6 == i[0]) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        i[0] += integer;
                        LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e(TAG, "onComplete");
                    }
                });

        /**
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onError:java.lang.Exception: 404
         */
    }

    @OnClick(R.id.btn_test31)
    public void onBtnTest31Clicked() {
        /**
         * 当被观察者接收到异常或者错误事件时会回调该方法，这个方法会返回一个新的被观察者。
         * 如果返回的被观察者发送 Error 事件则之前的被观察者不会继续发送事件，
         * 如果发送正常事件则之前的被观察者会继续不断重试发送事件。
         */

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("chan");
                emitter.onNext("ze");
                emitter.onNext("de");
                emitter.onError(new Exception("404"));
                emitter.onNext("haha");
            }
        })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                if (!throwable.getMessage().equals("404")) {
                                    return Observable.just("可以忽略的异常");
                                } else {
                                    return Observable.just(new Throwable("终止啦"));
                                }
                            }
                        });
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.INSTANCE.e(TAG, "onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e(TAG, "onComplete");
                    }
                });

        /**
         * 输出结果：
         *
         */
    }

    @OnClick(R.id.btn_test32)
    public void onBtnTest32Clicked() {
        /**
         * 重复发送被观察者的事件，times 为发送次数。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        })
                .repeat(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e(TAG, "onComplete");
                    }
                });

        /**
         * 输出结果：
         * onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onNext:1
         onNext:2
         onNext:3
         onComplete
         */
    }

    @OnClick(R.id.btn_test33)
    public void onBtnTest33Clicked() {
        /**
         * 这个方法可以会返回一个新的被观察者设定一定逻辑来决定是否重复发送事件。
         *
         * 这里分三种情况，如果新的被观察者返回 onComplete 或者 onError 事件，
         * 则旧的被观察者不会继续发送事件。如果被观察者返回其他事件，则会重复发送事件。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
//                return Observable.empty();
//                return Observable.error(new Exception("404"));
                return Observable.just(4);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "onComplete");
            }
        });

        /**
         * return Observable.empty();
         输出结果：
         onSubscribe
         onComplete

         return Observable.error(new Exception("404"));
         输出结果：
         onSubscribe
         onError:java.lang.Exception: 404

         return Observable.just(4);
         输出结果：
         onSubscribe
         onNext:1
         onNext:2
         onNext:3
         onComplete
         */
    }

    @OnClick(R.id.btn_test34)
    public void onBtnTest34Clicked() {
        /**
         * 指定被观察者的线程，要注意的时，如果多次调用此方法，只有第一次有效。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                ThreadUtils.INSTANCE.logCurrThreadName(TAG);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e(TAG, "onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e(TAG, "onComplete");
                    }
                });

        /**
         * onSubscribe
         Rxjava2Test13Activity: sub Thread,name --> RxComputationThreadPool-1
         onNext:1
         onNext:2
         onNext:3
         onComplete
         */
    }

    @OnClick(R.id.btn_test35)
    public void onBtnTest35Clicked() {
        /**
         * 指定观察者的线程，每指定一次就会生效一次。
         */
        Observable.just(1, 2, 3)
                .observeOn(Schedulers.newThread())
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        ThreadUtils.INSTANCE.logCurrThreadName("flatMap");
                        return Observable.just("chan" + integer);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.INSTANCE.e(TAG, "onNext:" + s);
                        ThreadUtils.INSTANCE.logCurrThreadName("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e(TAG, "onComplete");
                    }
                });
        /**
         * 输出结果：
         * onSubscribe
         flatMap: sub Thread,name --> RxNewThreadScheduler-4
         flatMap: sub Thread,name --> RxNewThreadScheduler-4
         flatMap: sub Thread,name --> RxNewThreadScheduler-4
         onNext:chan1
         onNext: main Thread,name --> main
         onNext:chan2
         onNext: main Thread,name --> main
         onNext:chan3
         onNext: main Thread,name --> main
         onComplete
         */
    }
}
