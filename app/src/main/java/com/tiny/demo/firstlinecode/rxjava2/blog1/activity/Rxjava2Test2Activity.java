package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.bean.ResBean;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.rxjava2.blog1.myretrofit.MyApi;
import com.tiny.demo.firstlinecode.rxjava2.blog1.myretrofit.MyRetrofitClient;
import com.tiny.demo.firstlinecode.rxjava2.blog1.myretrofit.bean.KingRegionBean;
import com.tiny.demo.firstlinecode.storage.greendao.GreenDaoHelper;
import com.tiny.demo.firstlinecode.storage.greendao.bean.User;
import com.tinytongtong.tinyutils.ThreadUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程控制
 */
public class Rxjava2Test2Activity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        //默认线程
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                ThreadUtils.logCurrThreadName("Observable thread");
                LogUtils.e("e.onNext(1)");
                e.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                ThreadUtils.logCurrThreadName("onNext thread");
                LogUtils.e("onNext --> " + integer);
            }
        };

        observable.subscribe(consumer);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        //子线程发送，主线程接收
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                ThreadUtils.logCurrThreadName("Observable thread");
                LogUtils.e("e.onNext(2)");
                e.onNext(2);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                ThreadUtils.logCurrThreadName("onNext thread");
                LogUtils.e("onNext --> " + integer);
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * 简单的来说, subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
     * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
     * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
     * 举个例子：
     */
    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                ThreadUtils.logCurrThreadName("Observable thread");
                LogUtils.e("e.onNext(3)");
                e.onNext(3);
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ThreadUtils.logCurrThreadName("onNext thread");
                        LogUtils.e("onNext --> " + integer);
                    }
                });
        /**
         * 运行结果为：
         * E/tiny_rxjava2: Observable thread --- sub Thread,name --> RxNewThreadScheduler-1
         * E/tiny_rxjava2: e.onNext(3)
         * E/tiny_rxjava2: onNext thread --- sub Thread,name --> RxCachedThreadScheduler-2
         * E/tiny_rxjava2: onNext --> 3
         */

        /**
         * 上游虽然指定了两次线程, 但只有第一次指定的有效, 依然是在RxNewThreadScheduler 线程中,
         * 而下游则跑到了RxCachedThreadScheduler 中, 这个CacheThread其实就是IO线程池中的一个.
         */
    }

    /**
     * 为了更清晰的看到下游的线程切换过程, 我们加点log:
     */
    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                ThreadUtils.logCurrThreadName("Observable thread");
                LogUtils.e("e.onNext(4)");
                e.onNext(4);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e("doOnNext --> " + integer);
                        ThreadUtils.logCurrThreadName("After observeOn(AndroidSchedulers.mainThread())");
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e("doOnNext --> " + integer);
                        ThreadUtils.logCurrThreadName("After observeOn(Schedulers.io())");
                    }
                })
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e("doOnNext --> " + integer);
                        ThreadUtils.logCurrThreadName("After observeOn(Schedulers.newThread())");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ThreadUtils.logCurrThreadName("accept thread");
                        LogUtils.e("onNext --> " + integer);
                    }
                });
        /**
         * 可以看到, 每调用一次observeOn() 线程便会切换一次, 因此如果我们有类似的需求时, 便可知道如何处理了.
         */

        /**
         * 在RxJava中, 已经内置了很多线程选项供我们选择, 例如有
         * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
         * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
         * Schedulers.newThread() 代表一个常规的新线程
         * AndroidSchedulers.mainThread() 代表Android的主线程
         * 这些内置的Scheduler已经足够满足我们开发的需求, 因此我们应该使用内置的这些选项,
         * 在RxJava内部使用的是线程池来维护这些线程, 所有效率也比较高.
         */
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        /**
         * retorfit的实战
         */

        MyApi myApi = MyRetrofitClient.RetrofitEnum.RetrofitInstance.getInstance().create(MyApi.class);
        Map<String, String> param = new HashMap<>();
        myApi.getKingRegionData(param)
                .subscribeOn(Schedulers.io())//在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())//回到主线程去处理请求结果
                .subscribe(new Observer<ResBean<KingRegionBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        //回调执行之前时调用
                        ThreadUtils.logCurrThreadName("onSubscribe");
                    }

                    @Override
                    public void onNext(ResBean<KingRegionBean> value) {
                        ThreadUtils.logCurrThreadName("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ThreadUtils.logCurrThreadName("onError");
                    }

                    @Override
                    public void onComplete() {
                        //回调执行完成之后调用
                        ThreadUtils.logCurrThreadName("onComplete");
                    }
                });
    }

    private Disposable disposable;

    /**
     * 看似很完美, 但我们忽略了一点, 如果在请求的过程中Activity已经退出了, 这个时候如果回到主线程去更新UI,
     * 那么APP肯定就崩溃了, 怎么办呢, 上一节我们说到了Disposable , 说它是个开关, 调用它的dispose()方法时就会切断水管,
     * 使得下游收不到事件, 既然收不到事件, 那么也就不会再去更新UI了. 因此我们可以在Activity中将这个Disposable
     * 保存起来, 当Activity退出时, 切断它即可.
     */
    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        /**
         *  绑定activity生命周期，单个请求
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 100000; j++) {
                    e.onNext(j);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e("onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.e("onNext --> " + value);
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
     * 那如果有多个Disposable 该怎么办呢, RxJava中已经内置了一个容器CompositeDisposable, 每当我们得到一个
     * Disposable时就调用CompositeDisposable.add()将它添加到容器中, 在退出的时候,
     * 调用CompositeDisposable.clear() 即可切断所有的水管.
     */

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        /**
         *  绑定activity生命周期，多个请求
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 100000; j++) {
                    e.onNext(j);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e("Integer onSubscribe");
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.e("Integer onNext --> " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("Integer onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("Integer onComplete");
                    }
                });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int j = 0; j < 100000; j++) {
                    e.onNext("猫了个咪啊 --> " + j);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e("String onSubscribe");
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(String value) {
                        LogUtils.e("String onNext --> " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("String onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("String onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test8)
    public void onBtnTest8Clicked() {
        readAllUsers().subscribe(new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                ThreadUtils.logCurrThreadName("onSubscribe");
            }

            @Override
            public void onNext(List<User> value) {
                ThreadUtils.logCurrThreadName("onNext");
                LogUtils.e("onNext value --> " + value);
            }

            @Override
            public void onError(Throwable e) {
                ThreadUtils.logCurrThreadName("onError");
            }

            @Override
            public void onComplete() {
                ThreadUtils.logCurrThreadName("onComplete");
            }
        });
    }

    //将greenDao中存储的user列表返回
    public Observable<List<User>> readAllUsers() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                ThreadUtils.logCurrThreadName("readAllUsers");
                List<User> userList = GreenDaoHelper.getDaoSession().getUserDao().loadAll();
                e.onNext(userList);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy");
        if (disposable != null) {
            disposable.dispose();
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
