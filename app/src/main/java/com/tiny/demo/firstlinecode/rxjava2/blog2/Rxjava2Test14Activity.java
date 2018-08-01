package com.tiny.demo.firstlinecode.rxjava2.blog2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

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
 * Desc: 过滤操作符
 *
 * @author tiny
 * @date 2018/6/10 下午3:29
 */
public class Rxjava2Test14Activity extends AppCompatActivity {

    public static final String TAG = Rxjava2Test14Activity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Test14Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test14);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        /**
         * 通过一定逻辑来过滤被观察者发送的事件，如果返回 true 则会发送事件，否则不会发送。
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
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
         onNext:2
         onNext:4
         onNext:6
         onNext:8
         onNext:10
         onComplete
         */
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        /**
         * 可以过滤不符合该类型事件
         */
        Observable.just(1, 2, 3, "55", "66")
                .ofType(Integer.class)
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
                        LogUtils.e(TAG, "onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        /**
         * 跳过正序某些事件，count 代表跳过事件的数量
         */
        Observable.just(1, 2, 3, 4, 5)
                .skip(2)
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
         onNext:3
         onNext:4
         onNext:5
         onComplete
         */
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        /**
         * 过滤事件序列中的重复事件。
         */
        Observable.just(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
                .distinct()
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
         onNext:1
         onNext:2
         onNext:3
         onNext:4
         onNext:5
         onComplete
         */
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        /**
         * 过滤掉连续重复的事件
         */
        Observable.just(1, 2, 3, 3, 2, 1)
                .distinctUntilChanged()
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
         onNext:1
         onNext:2
         onNext:3
         onNext:2
         onNext:1
         onComplete
         */
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        /**
         * 控制观察者接收的事件的数量。
         */
        Observable.just(1, 2, 3, 4, 5, 6)
                .take(3)
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
         onNext:1
         onNext:2
         onNext:3
         onComplete
         */
    }

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        /**
         * 如果两件事件发送的时间间隔小于设定的时间间隔, 则前一件事件就不会发送给观察者。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(900);
                emitter.onNext(2);
                emitter.onComplete();
            }
        })
                .debounce(1, TimeUnit.SECONDS)
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
         onNext:2
         onComplete
         */
    }

    @OnClick(R.id.btn_test8)
    public void onBtnTest8Clicked() {
        /**
         * firstElement() 取事件序列的第一个元素，
         * lastElement() 取事件序列的最后一个元素。
         */
        Observable.just(1, 2, 3, 4)
                .firstElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "firstElement accept:" + integer);
                    }
                });
        /**
         * 输出结果：
         *firstElement accept:1
         */
    }

    @OnClick(R.id.btn_test9)
    public void onBtnTest9Clicked() {
        /**
         * lastElement() 取事件序列的最后一个元素。
         */
        Observable.just(1, 2, 3, 4)
                .lastElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "lastElement accept:" + integer);
                    }
                });
        /**
         * 输出结果：
         *lastElement accept:4
         */
    }

    @OnClick(R.id.btn_test10)
    public void onBtnTest10Clicked() {
        /**
         * elementAt() 可以指定取出事件序列中事件，但是输入的 index 超出事件序列的总数的话就不会出现任何结果。
         * 这种情况下，你想发出异常信息的话就用 elementAtOrError()
         */
        Observable.just(1, 2, 3)
                .elementAt(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "elementAt accept:" + integer);
                    }
                });
    }

    @OnClick(R.id.btn_test11)
    public void onBtnTest11Clicked() {
        Observable.just(1, 2, 3)
                .elementAtOrError(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(TAG, "elementAtOrError accept:" + integer);
                    }
                });
    }
}
