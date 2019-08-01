package com.tiny.demo.firstlinecode.javareference.async2sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.tinytongtong.tinyutils.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
import io.reactivex.functions.Consumer;
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

        // 创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(3);

        // 执行任务。
        executors.submit(Task1);
        executors.submit(new CustomRunnable(countDownLatch, "Task2", 3));
        executors.submit(new CustomRunnable(countDownLatch, "Task3", 5));

        // 任务完成后关闭线程池
        executors.shutdown();

    }

    /**
     * 自定义Runnable
     */
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

    @OnClick(R.id.btn_java_cyclic_barrier)
    public void onBtnJavaCyclicBarrierClicked() {
        /**
         * CyclicBarrier是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            LogUtils.e(TAG, "所有任务都执行完毕了");
            ThreadUtils.logCurrThreadName(TAG + " barrierAction");
        });
        List<Runnable> runnables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                LogUtils.e(TAG, "当前是第" + finalI + "个任务，开始执行");
                ThreadUtils.logCurrThreadName(TAG + " 子任务");
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtils.e(TAG, "当前是第" + finalI + "个任务，执行完毕");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            };
            runnables.add(runnable);
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Runnable r : runnables) {
            executorService.submit(r);
        }
        executorService.shutdown();
    }

    @OnClick(R.id.btn_java_future_task)
    public void onBtnJavaFutureTaskClicked() {
        FutureTaskActivity.actionStart(this);
    }

    /**
     * 比如说注册成功后直接登录
     */
    @OnClick(R.id.btn_java_rxjava_callback)
    public void onBtnJavaRxjavaCallbackClicked() {
        RegisterReqBean registerReqBean = new RegisterReqBean("猫了个咪", "13333333333", "123456");
        Observable registerRequest = Observable.create(new ObservableOnSubscribe<RegisterReqBean>() {
            @Override
            public void subscribe(ObservableEmitter<RegisterReqBean> emitter) throws Exception {
                LogUtils.e(TAG, "注册请求 registerReqBean:" + registerReqBean);
                ThreadUtils.logCurrThreadName(TAG + " 注册请求成功");
                emitter.onNext(registerReqBean);
                LogUtils.e(TAG, "发送注册请求");
                emitter.onComplete();
                LogUtils.e(TAG, "发送注册请求完成");
            }
        });

        // 模拟注册响应。将RegisterReqBean转化为RegisterRespBean。
        Function mockRegisterResp = new Function<RegisterReqBean, RegisterRespBean>() {
            @Override
            public RegisterRespBean apply(RegisterReqBean registerReqBean) throws Exception {
                RegisterRespBean registerRespBean = new RegisterRespBean(registerReqBean.getName(),
                        registerReqBean.getPwd(), "恭喜您注册成功");
                LogUtils.e(TAG, "注册响应 registerRespBean:" + registerRespBean);
                TimeUnit.SECONDS.sleep(5);// 模拟网络延迟
                ThreadUtils.logCurrThreadName(TAG + " 注册响应成功");
                return registerRespBean;
            }
        };

        // 模拟登录请求。将RegisterRespBean转化为LoginReqBean。
        Function mockLoginReq = new Function<RegisterRespBean, LoginReqBean>() {
            @Override
            public LoginReqBean apply(RegisterRespBean registerRespBean) throws Exception {
                LoginReqBean loginReqBean = new LoginReqBean(registerRespBean.getName() + " 我要登陆", registerRespBean.getPwd());
                LogUtils.e(TAG, "登录请求 loginReqBean:" + loginReqBean);
                ThreadUtils.logCurrThreadName(TAG + " 登录请求成功");
                return loginReqBean;
            }
        };

        // 模拟登录响应。将LoginReqBean转化为LoginRespBean。
        Function mockLoginResp = new Function<LoginReqBean, LoginRespBean>() {
            @Override
            public LoginRespBean apply(LoginReqBean loginReqBean) throws Exception {
                LoginRespBean loginRespBean = new LoginRespBean(loginReqBean.getName(),
                        loginReqBean.getPwd(), "恭喜您登录成功");
                LogUtils.e(TAG, "登录响应 loginRespBean:" + loginRespBean);
                TimeUnit.SECONDS.sleep(5);// 模拟网络延迟
                ThreadUtils.logCurrThreadName(TAG + " 登录响应成功");
                return loginRespBean;
            }

        };

        // 模拟接受登录响应结果。
        Observer<LoginRespBean> resultObserver = new Observer<LoginRespBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(LoginRespBean loginRespBean) {
                LogUtils.e(TAG, "onNext 登录成功，可以更新UI了。 loginRespBean:" + loginRespBean);
                ThreadUtils.logCurrThreadName(TAG + " ");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onError e:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "onComplete");
            }
        };

        registerRequest.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(mockRegisterResp)
                .observeOn(Schedulers.io())
                .map(mockLoginReq)
                .observeOn(Schedulers.newThread())
                .map(mockLoginResp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultObserver);
    }

    @OnClick(R.id.btn_java_rxjava_merge)
    public void onBtnJavaRxjavaMergeClicked() {
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
