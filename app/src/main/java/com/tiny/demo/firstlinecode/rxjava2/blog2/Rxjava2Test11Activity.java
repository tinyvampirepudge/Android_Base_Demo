package com.tiny.demo.firstlinecode.rxjava2.blog2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.rxjava2.blog2.entity.Person;
import com.tiny.demo.firstlinecode.rxjava2.blog2.entity.Plan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * Desc:    转换操作符
 *
 * @author tiny
 * @date 2018/6/7 上午12:20
 */
public class Rxjava2Test11Activity extends AppCompatActivity {
    public static final String TAG = Rxjava2Test11Activity.class.getSimpleName();
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
    @BindView(R.id.btn_test10)
    Button btnTest10;
    @BindView(R.id.btn_test11)
    Button btnTest11;
    @BindView(R.id.btn_test12)
    Button btnTest12;
    @BindView(R.id.btn_test13)
    Button btnTest13;
    @BindView(R.id.btn_test14)
    Button btnTest14;
    @BindView(R.id.btn_test15)
    Button btnTest15;
    @BindView(R.id.btn_test16)
    Button btnTest16;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Test11Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test11);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        /**
         * map 可以将被观察者发送的数据类型转变成其他的类型
         */
        //以下代码将 Integer 类型的数据转换成 String
        Observable.just(1, 2, 3, 4, 5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "My index is " + integer;
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
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        /**
         * 这个方法可以将事件序列中的元素进行整合加工，返回一个新的被观察者。
         */
        /**
         * flatMap() 其实与 map() 类似，但是 flatMap() 返回的是一个 Observerable。
         * 现在用一个例子来说明 flatMap() 的用法。
         */
        List<Person> personList = generatePersonList();

        /**
         * 现在有一个需求就是要将 Person 集合中的每个元素中的 Plan 的 action 打印出来。
         * 首先用 map() 来实现这个需求看看：
         */
//        Observable.fromIterable(personList)
//                .map(new Function<Person, List<Plan>>() {
//                    @Override
//                    public List<Plan> apply(Person person) throws Exception {
//                        return person.getPlanList();
//                    }
//                }).subscribe(new Observer<List<Plan>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                LogUtils.INSTANCE.e(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onNext(List<Plan> plans) {
//                LogUtils.INSTANCE.e(TAG, "onNext");
//                for (Plan plan : plans) {
//                    List<String> actions = plan.getActionList();
//                    for (String action : actions) {
//                        LogUtils.INSTANCE.e(TAG, "onNext action:" + action);
//                    }
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtils.INSTANCE.e(TAG, "onComplete");
//            }
//        });
        /**
         * 可以看到 onNext() 用了嵌套 for 循环来实现，如果代码逻辑复杂起来的话，可能需要多重循环才可以实现。
         */

        /**
         * 现在看下使用 flatMap() 实现：
         */
        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        return Observable.fromIterable(person.getPlanList());
                    }
                }).flatMap(new Function<Plan, ObservableSource<String>>() {

            @Override
            public ObservableSource<String> apply(Plan plan) throws Exception {
                return Observable.fromIterable(plan.getActionList());
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
    }

    private List<Person> generatePersonList() {
        List<Person> personList = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            String name = "tiny" + j;
            List<Plan> planList = new ArrayList<>();
            for (int m = 0; m < 2; m++) {
                Plan plan = new Plan("time" + j + m, name + "搞毛啊" + j + m);
                List<String> actions = new ArrayList<>();
                for (int n = 0; n < 2; n++) {
                    actions.add("action" + j + m + n);
                }
                plan.setActionList(actions);
                planList.add(plan);
            }
            Person person = new Person(name, planList);
            personList.add(person);
        }
        return personList;
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        /**
         * concatMap() 和 flatMap() 基本上是一样的，
         * 只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的。
         */
        List<Person> personList = generatePersonList();
//        Observable.fromIterable(personList)
//                .flatMap(new Function<Person, ObservableSource<Plan>>() {
//                    @Override
//                    public ObservableSource<Plan> apply(Person person) throws Exception {
//                        if ("tiny0".equals(person.getName())) {
//                            return Observable.fromIterable(person.getPlanList()).delay(10, TimeUnit.MILLISECONDS);
//                        }
//                        return Observable.fromIterable(person.getPlanList());
//                    }
//                }).subscribe(new Observer<Plan>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                LogUtils.INSTANCE.e(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onNext(Plan plan) {
//                LogUtils.INSTANCE.e(TAG, "onNext:" + plan.getContent());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.INSTANCE.e(TAG, "onError:" + e.toString());
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtils.INSTANCE.e(TAG, "onComplete");
//            }
//        });
        /**
         * 输出结果
         onSubscribe
         onNext:tiny1搞毛啊10
         onNext:tiny1搞毛啊11
         onNext:tiny0搞毛啊00
         onNext:tiny0搞毛啊01
         onComplete
         */
        Observable.fromIterable(personList)
                .concatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        if ("tiny0".equals(person.getName())) {
                            return Observable.fromIterable(person.getPlanList()).delay(10, TimeUnit.MILLISECONDS);
                        }
                        return Observable.fromIterable(person.getPlanList());
                    }
                }).subscribe(new Observer<Plan>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Plan plan) {
                LogUtils.INSTANCE.e(TAG, "onNext:" + plan.getContent());
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
         * 输出结果
         onSubscribe
         onNext:tiny0搞毛啊00
         onNext:tiny0搞毛啊01
         onNext:tiny1搞毛啊10
         onNext:tiny1搞毛啊11
         onComplete
         */
        /**
         * 这就代表 concatMap() 转换后发送的事件序列是有序的了。
         */
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        /**
         * 从需要发送的事件当中获取一定数量的事件，并将这些事件放到缓冲区当中一并发出。
         *
         * buffer 有两个参数，一个是 count，另一个 skip。count 缓冲区元素的数量，
         * skip 就代表缓冲区满了之后，发送下一次事件序列的时候要跳过多少元素。
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .buffer(3, 3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        LogUtils.INSTANCE.e(TAG, "onNext 传递过来的数据容量大小:" + integers.size());
                        for (Integer integer : integers) {
                            LogUtils.INSTANCE.e(TAG, "onNext 元素:" + integer);
                        }
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

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        /**
         * 将发送的数据进行分组，每个分组都会返回一个被观察者。
         *
         * 在 groupBy() 方法返回的参数是分组的名字，每返回一个值，
         * 那就代表会创建一个组，以上的代码就是将1~10的数据分成3组，
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 3;
                    }
                }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.INSTANCE.e(TAG, "GroupedObservable onSubscribe");
            }

            @Override
            public void onNext(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                LogUtils.INSTANCE.e(TAG, "GroupedObservable onNext");
                integerIntegerGroupedObservable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e(TAG, "onNext --> groupName:" + integerIntegerGroupedObservable.getKey() + ",value:" + integer);
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

            @Override
            public void onError(Throwable e) {
                LogUtils.INSTANCE.e(TAG, "GroupedObservable onError");
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e(TAG, "GroupedObservable onComplete");
            }
        });
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        /**
         * 将数据以一定的逻辑聚合起来。
         *
         * 比方说可以很方便的求斐波那契数列。
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        LogUtils.INSTANCE.e(TAG, "apply integer:" + integer);
                        LogUtils.INSTANCE.e(TAG, "apply integer2:" + integer2);
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.INSTANCE.e(TAG, "accept integer:" + integer);
            }
        });
    }

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        /**
         * 发送指定数量的事件时，就将这些事件分为一组。window 中的 count 的参数就是代表指定的数量，
         * 例如将 count 指定为2，那么每发2个数据就会将这2个数据分成一组。
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .window(2)
                .subscribe(new Observer<Observable<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.INSTANCE.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Observable<Integer> integerObservable) {
                        LogUtils.INSTANCE.e(TAG, "onNext");
                        integerObservable.subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                LogUtils.INSTANCE.e(TAG, "integerObservable onSubscribe");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                LogUtils.INSTANCE.e(TAG, "integerObservable onNext:" + integer);
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtils.INSTANCE.e(TAG, "integerObservable onError:" + e.toString());
                            }

                            @Override
                            public void onComplete() {
                                LogUtils.INSTANCE.e(TAG, "integerObservable onComplete");
                            }
                        });
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
    }

    @OnClick(R.id.btn_test9)
    public void onBtnTest9Clicked() {
    }

    @OnClick(R.id.btn_test10)
    public void onBtnTest10Clicked() {
    }

    @OnClick(R.id.btn_test11)
    public void onBtnTest11Clicked() {
    }

    @OnClick(R.id.btn_test12)
    public void onBtnTest12Clicked() {
    }

    @OnClick(R.id.btn_test13)
    public void onBtnTest13Clicked() {
    }

    @OnClick(R.id.btn_test14)
    public void onBtnTest14Clicked() {
    }

    @OnClick(R.id.btn_test15)
    public void onBtnTest15Clicked() {
    }

    @OnClick(R.id.btn_test16)
    public void onBtnTest16Clicked() {
    }
}
