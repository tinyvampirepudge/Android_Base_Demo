package com.tiny.demo.firstlinecode.rxjava2.blog2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Desc: 组合操作符
 *
 * @author tiny
 * @date 2018/6/7 下午6:13
 */
public class Rxjava2Test12Activity extends AppCompatActivity {
    public static final String TAG = Rxjava2Test12Activity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Test12Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test12);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        /**
         * 可以将多个观察者组合在一起，然后按照之前发送顺序发送事件。
         * 需要注意的是，concat() 最多只可以发送4个事件。
         */

        Observable.concat(Observable.just(1000, 2000),
                Observable.just(300, 400),
                Observable.just(50, 60),
                Observable.just(7, 8))
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

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        /**
         * 与 concat() 作用一样，不过 concatArray() 可以发送多于 4 个被观察者。
         */
        Observable.concatArray(Observable.just(1),
                Observable.just(2),
                Observable.just(3),
                Observable.just(4),
                Observable.just(5),
                Observable.just(6))
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

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        /**
         * 这个方法与 concat() 作用基本一样，知识 concat() 是串行发送事件，而 merge() 并行发送事件。
         */
        Observable.merge(
                Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "A" + aLong;
                    }
                }),
                Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "B" + aLong;
                    }
                })
        ).subscribe(new Observer<String>() {
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
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        /**
         * mergeArray() 与 merge() 的作用是一样的，只是它可以发送4个以上的被观察者，这里就不再赘述了。
         */
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        /**
         * 在 concatArray() 和 mergeArray() 两个方法当中，如果其中有一个被观察者发送了一个 Error 事件，
         * 那么就会停止发送事件，如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，
         * 就可以使用 concatArrayDelayError() 和 mergeArrayDelayError()
         */

        Observable.concatArray(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onError(new NumberFormatException("我故意的，你拿我怎样？"));
                    }
                }),
                Observable.just(2, 3, 4)
        ).subscribe(new Observer<Integer>() {
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
         * 输出结果是
         * E/Rxjava2Test12Activity: onSubscribe
         * E/Rxjava2Test12Activity: onNext:1
         * E/Rxjava2Test12Activity: onError:java.lang.NumberFormatException: 我故意的，你拿我怎样？
         */
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        Observable.mergeArray(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new NumberFormatException("我故意的，你拿我怎样？来啊，咬我啊。"));
                    }
                }),
                Observable.just(2, 3, 4)
        ).subscribe(new Observer<Integer>() {
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

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        /**
         * 在 concatArray() 和 mergeArray() 两个方法当中，
         * 如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件，
         * 如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，
         * 就可以使用 concatArrayDelayError() 和 mergeArrayDelayError()
         */
        Observable.mergeArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new NumberFormatException("我故意的，你拿我怎样？来啊，咬我啊。"));
                    }
                }),
                Observable.just(4, 5, 6)
        ).subscribe(new Observer<Integer>() {
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

    @OnClick(R.id.btn_test8)
    public void onBtnTest8Clicked() {
        /**
         * zip()
         * zip操作符
         * 会将多个被观察者合并，根据各个被观察者发送事件的顺序一个个结合起来，
         * 最终发送的事件数量会与源 Observable 中最少事件的数量一样。
         */
        Observable.zip(Observable.intervalRange(1, 5, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s1 = "A" + aLong;
                                LogUtils.INSTANCE.e(TAG, "A发送的事件" + s1);
                                return s1;
                            }
                        }),
                Observable.intervalRange(1, 5, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s2 = "B" + aLong;
                                LogUtils.INSTANCE.e(TAG, "B发送的事件" + s2);
                                return s2;
                            }
                        }),
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        String res = s + s2;
                        return res;
                    }
                }).subscribe(new Observer<String>() {
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
         * 06-08 11:47:28.305 26843-26843/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onSubscribe
         06-08 11:47:29.306 26843-26963/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件A1
         06-08 11:47:29.307 26843-26964/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的事件B1
         onNext:A1B1
         06-08 11:47:30.306 26843-26963/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件A2
         06-08 11:47:30.308 26843-26964/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的事件B2
         06-08 11:47:30.314 26843-26963/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onNext:A2B2
         06-08 11:47:31.306 26843-26963/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件A3
         06-08 11:47:31.306 26843-26964/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的事件B3
         onNext:A3B3
         06-08 11:47:32.306 26843-26963/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件A4
         06-08 11:47:32.308 26843-26964/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的事件B4
         onNext:A4B4
         06-08 11:47:33.076 32552-32598/? I/update_UpdateChecker: organizeCheckAppsData:[{"package_name":"com.tiny.demo.firstlinecode","version_code":1,"version_md5":"202b751d8f18f5fb197fafcd3438d57b"}]
         06-08 11:47:33.306 26843-26963/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件A5
         06-08 11:47:33.307 26843-26964/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的事件B5
         06-08 11:47:33.308 26843-26964/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onNext:A5B5
         onComplete
         */

        /**
         * 可以发现最终接收到的事件数量是5，那么为什么第二个 Observable 没有发送第6个事件呢？
         * 因为在这之前第一个 Observable 已经发送了 onComplete 事件，所以第二个 Observable 不会再发送事件。
         */
    }

    @OnClick(R.id.btn_test9)
    public void onBtnTest9Clicked() {
        /**
         * combineLatest() 的作用与 zip() 类似，但是 combineLatest() 发送事件的序列是与发送的时间线有关的，
         * 当 combineLatest() 中所有的 Observable 都发送了事件，只要其中有一个 Observable 发送事件，
         * 这个事件就会和其他 Observable 最近发送的事件结合起来发送，这样可能还是比较抽象，看看以下例子代码。
         */
        Observable.combineLatest(Observable.intervalRange(1, 4, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s1 = "A" + aLong;
                                LogUtils.INSTANCE.e(TAG, "A发送的事件:" + s1);
                                return s1;
                            }
                        }),
                Observable.intervalRange(1, 5, 2, 2, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s2 = "B" + aLong;
                                LogUtils.INSTANCE.e(TAG, "B发送的的事件:" + s2);
                                return s2;
                            }
                        }),
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s + s2;
                    }
                }).subscribe(new Observer<String>() {
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
         * 06-08 12:02:26.670 27806-27806/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onSubscribe
         06-08 12:02:27.671 27806-28171/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件:A1
         06-08 12:02:28.672 27806-28171/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件:A2
         06-08 12:02:28.674 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的的事件:B1
         06-08 12:02:28.675 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onNext:A2B1
         06-08 12:02:29.671 27806-28171/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件:A3
         06-08 12:02:29.672 27806-28171/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onNext:A3B1
         06-08 12:02:30.672 27806-28171/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: A发送的事件:A4
         onNext:A4B1
         06-08 12:02:30.674 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的的事件:B2
         onNext:A4B2
         06-08 12:02:32.674 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的的事件:B3
         06-08 12:02:32.675 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onNext:A4B3
         06-08 12:02:34.674 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的的事件:B4
         06-08 12:02:34.675 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onNext:A4B4
         06-08 12:02:36.674 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: B发送的的事件:B5
         06-08 12:02:36.675 27806-28172/com.tiny.demo.firstlinecode E/Rxjava2Test12Activity: onNext:A4B5
         onComplete
         */

        /**
         * 分析上述结果可以知道，当发送 A1 事件之后，因为 B 并没有发送任何事件，所以根本不会发生结合。
         * 当 B 发送了 B1 事件之后，就会与 A 最近发送的事件 A2 结合成 A2B1，这样只有后面一有被观察者发送事件，
         * 这个事件就会与其他被观察者最近发送的事件结合起来了。
         */

        /**
         * 因为 combineLatestDelayError() 就是多了延迟发送 onError() 功能，这里就不再赘述了。
         */
    }

    @OnClick(R.id.btn_test10)
    public void onBtnTest10Clicked() {
        /**
         * 与 scan() 操作符的作用也是将发送数据以一定逻辑聚合起来，
         * 这两个的区别在于 scan() 每处理一次数据就会将事件发送给观察者，
         * 而 reduce() 会将所有数据聚合在一起才会发送事件给观察者。
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        int res = integer + integer2;
                        LogUtils.INSTANCE.e(TAG, "integer:" + integer);
                        LogUtils.INSTANCE.e(TAG, "integer2:" + integer2);
                        LogUtils.INSTANCE.e(TAG, "res:" + res);
                        return res;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.INSTANCE.e(TAG, "accept:" + integer);
            }
        });
    }

    @OnClick(R.id.btn_test11)
    public void onBtnTest11Clicked() {
        /**
         * 将数据收集到数据结构当中。
         */
        Observable.just(1, 2, 3, 4)
                .collect(new Callable<ArrayList<Integer>>() {
                             @Override
                             public ArrayList<Integer> call() throws Exception {
                                 return new ArrayList<>();
                             }
                         },
                        new BiConsumer<ArrayList<Integer>, Integer>() {
                            @Override
                            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                                integers.add(integer);
                            }
                        })
                .subscribe(new Consumer<ArrayList<Integer>>() {
                    @Override
                    public void accept(ArrayList<Integer> integers) throws Exception {
                        LogUtils.INSTANCE.e(TAG, "accept:" + integers);
                    }
                });
    }

    @OnClick(R.id.btn_test12)
    public void onBtnTest12Clicked() {
        /**
         * 在发送事件之前追加事件，startWith() 追加一个事件，
         * startWithArray() 可以追加多个事件。追加的事件会先发出。
         */
        Observable.just(5, 6, 7)
                .startWithArray(2, 3, 4)
                .startWith(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.INSTANCE.e(TAG, "accept:" + integer);
                    }
                });
    }

    @OnClick(R.id.btn_test13)
    public void onBtnTest13Clicked() {
        /**
         * 返回被观察者发送事件的数量。
         */
        Observable.just(1, 2, 3)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.INSTANCE.e(TAG, "accept:" + aLong);
                    }
                });
    }

}
