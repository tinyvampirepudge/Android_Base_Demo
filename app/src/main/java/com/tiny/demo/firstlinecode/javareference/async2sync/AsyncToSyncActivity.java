package com.tiny.demo.firstlinecode.javareference.async2sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: Java中异步转同步的几种方法
 * @Author wangjianzhou@qding.me
 * @Date 2019-05-22 21:57
 * @Version
 */
public class AsyncToSyncActivity extends AppCompatActivity {
    public static final String TAG = AsyncToSyncActivity.class.getSimpleName();

    @BindView(R.id.btn_java_count_down_latch)
    Button btnJavaCountDownLatch;
    @BindView(R.id.btn_java_future_task)
    Button btnJavaFutureTask;
    @BindView(R.id.btn_java_rxjava)
    Button btnJavaRxjava;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AsyncToSyncActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_to_sync);
        ButterKnife.bind(this);
    }

    CountDownLatch countDownLatch;

    @OnClick(R.id.btn_java_count_down_latch)
    public void onBtnJavaCountDownLatchClicked() {
        /**
         * Task2和Task3都执行完了以后，才执行Task2
         */
        countDownLatch = new CountDownLatch(2);

        Runnable Task1 = () -> {
            LogUtils.e(TAG, "开始执行Task1");
            ThreadUtils.logCurrThreadName(TAG + " Task1");
            try {
                // 注意这里是await方法，不是wait方法，不要问我为什么，难受。
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.e(TAG, "Task1执行完毕");
        };

        new Thread(Task1).start();
        new Thread(new CustomRunnable(countDownLatch, "Task2", 3)).start();
        new Thread(new CustomRunnable(countDownLatch, "Task3", 5)).start();

    }

    private static class CustomRunnable implements Runnable {

        private CountDownLatch countDownLatch;
        private String name;
        private int delayTime;

        public CustomRunnable(CountDownLatch countDownLatch, String name, int delayTime) {
            this.countDownLatch = countDownLatch;
            this.name = name;
            this.delayTime = delayTime;
        }

        @Override
        public void run() {
            LogUtils.e(TAG, "开始执行" + name);
            ThreadUtils.logCurrThreadName(TAG + " " + name);
            try {
                TimeUnit.SECONDS.sleep(delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.e(TAG, name + "执行完毕");
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }


    @OnClick(R.id.btn_java_future_task)
    public void onBtnJavaFutureTaskClicked() {
        FutureTaskActivity.actionStart(this);
    }

    @OnClick(R.id.btn_java_rxjava)
    public void onBtnJavaRxjavaClicked() {
        /**
         * 等待多个任务完成后再执行，不保证顺序
         */
        Observable o1 = Observable.create((ObservableOnSubscribe<String>) e -> {
            ThreadUtils.logCurrThreadName(TAG + " Observable1");
            TimeUnit.SECONDS.sleep(5);
            e.onNext("第一个Observable");
            e.onComplete();
        }).subscribeOn(Schedulers.newThread());

        Observable o2 = Observable.create((ObservableOnSubscribe<String>) e -> {
            ThreadUtils.logCurrThreadName(TAG + " Observable2");
            TimeUnit.SECONDS.sleep(3);
            e.onNext("第二个Observable");
            e.onComplete();
        }).subscribeOn(Schedulers.newThread());

        Observable o3 = Observable.create((ObservableOnSubscribe<String>) e -> {
            ThreadUtils.logCurrThreadName(TAG + " Observable3");
            TimeUnit.SECONDS.sleep(1);
            e.onNext("第三个Observable");
            e.onComplete();
        }).subscribeOn(Schedulers.newThread());

        /**
         * 三个任务都发送了onComplete事件后，订阅者的onComplete方法才会调用。
         */
        Observable.merge(o1, o2, o3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtils.e(TAG, "onNext: " + o);
                        ThreadUtils.logCurrThreadName(TAG + " onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }
}
