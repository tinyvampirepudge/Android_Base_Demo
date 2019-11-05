package com.tiny.demo.firstlinecode.rxjava2.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: RxEasyHttp cache FirstRemoteStrategy模式模拟
 * @Author wangjianzhou@qding.me
 * @Date 2019-11-03 20:01
 * @Version
 */
public class RxEasyHttpCacheFirstRemoteStrategyTestActivity extends AppCompatActivity {
    private static final String TAG = RxEasyHttpCacheFirstRemoteStrategyTestActivity.class.getSimpleName().substring(0, 23);

    @BindView(R.id.btn_send_request_success)
    Button btnSendRequestSuccess;
    @BindView(R.id.btn_send_request_failure)
    Button btnSendRequestFailure;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RxEasyHttpCacheFirstRemoteStrategyTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_easy_http_cache_first_remote_strategy_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_send_request_success)
    public void onBtnSendRequestSuccessClicked() {
        Observable<String> stream = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("请求数据1-");
                emitter.onComplete();
            }
        });
        toObservable(stream).subscribeWith(new ResourceObserver<String>() {

            @Override
            protected void onStart() {
                super.onStart();
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onStart");
            }

            @Override
            public void onNext(String s) {
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onNext s:" + s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onError e:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onComplete");
            }
        });
    }

    @OnClick(R.id.btn_send_request_failure)
    public void onBtnSendRequestFailureClicked() {
        Observable<String> stream = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("请求数据2-");
                emitter.onError(new Exception("阿西吧"));
            }
        });
        toObservable(stream).subscribeWith(new ResourceObserver<String>() {

            @Override
            protected void onStart() {
                super.onStart();
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onStart");
            }

            @Override
            public void onNext(String s) {
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onNext s:" + s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onError e:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "onBtnSendRequestSuccessClicked onComplete");
            }
        });
    }

    private <T> Observable<String> toObservable(Observable<String> stream) {
        return stream.map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                LogUtils.e(TAG, "解析数据");
                return s + "111";
            }
        })
                .compose(_io_main())
                .compose(new ObservableTransformer<String, String>() {
                    @Override
                    public ObservableSource<String> apply(Observable<String> upstream) {
                        // 模拟FirstRemoteStrategy转换操作

                        // 访问远端网络操作，失败后，读取缓存操作
                        return upstream.flatMap(new Function<String, ObservableSource<String>>() {
                            @Override
                            public ObservableSource<String> apply(String s) throws Exception {
                                return Observable.create(new ObservableOnSubscribe<String>() {
                                    @Override
                                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                        emitter.onNext("数据存储到本地成功");
                                        emitter.onComplete();
                                    }
                                })
                                        .map(new Function<String, String>() {
                                            @Override
                                            public String apply(@NonNull String s1) throws Exception {
                                                return s1 + "loadRemote";
                                            }
                                        }).onErrorResumeNext(new Function<Throwable, Observable<String>>() {
                                            @Override
                                            public Observable<String> apply(Throwable throwable) throws Exception {

                                                return Observable.create(new ObservableOnSubscribe<String>() {
                                                    @Override
                                                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                                        emitter.onNext("我是缓存信息, 我是错误信息：" + throwable.getMessage());
                                                        emitter.onComplete();
                                                    }
                                                })
                                                        .flatMap(new Function<String, ObservableSource<String>>() {
                                                            @Override
                                                            public ObservableSource<String> apply(String s) throws Exception {
                                                                if (s == null) {
                                                                    return Observable.error(new NullPointerException("Not find the cache!"));
                                                                }
                                                                return Observable.just(s + "，fromCache");
                                                            }
                                                        });
                                            }
                                        });
                            }
                        });

                    }
                });
    }

    public static <T> ObservableTransformer<String, String> _io_main() {
        return new ObservableTransformer<String, String>() {
            @Override
            public ObservableSource<String> apply(@NonNull Observable<String> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<String, String>() {
                            @Override
                            public String apply(String s) throws Exception {
                                LogUtils.e(TAG, "校验返回码是否为200");
                                return s + "222";
                            }
                        })
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                LogUtils.i(TAG, "+++doOnSubscribe+++" + disposable.isDisposed());
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                LogUtils.i(TAG, "+++doFinally+++");
                            }
                        })
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<String>>() {
                            @Override
                            public ObservableSource<String> apply(Throwable throwable) throws Exception {
                                // 对异常进行转换
                                return Observable.error(new ServerException(210086, "异常转换了下"));
                            }
                        });
            }
        };
    }

    Observable<String> loadCache() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("我是缓存信息");
                emitter.onComplete();
            }
        })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        if (s == null) {
                            return Observable.error(new NullPointerException("Not find the cache!"));
                        }
                        return Observable.just(s + "fromCache");
                    }
                });

    }

    //请求成功后：同步保存
    Observable<String> loadRemote(Observable<String> source) {
        return source
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                emitter.onNext("数据存储到本地成功");
                                emitter.onComplete();
                            }
                        })
                                .map(new Function<String, String>() {
                                    @Override
                                    public String apply(@NonNull String s1) throws Exception {
                                        return s1 + "loadRemote";
                                    }
                                }).onErrorReturn(new Function<Throwable, String>() {
                                    @Override
                                    public String apply(@NonNull Throwable throwable) throws Exception {
                                        LogUtils.e(TAG, "save status => " + throwable);
                                        return "loadRemote onErrorReturn";
                                    }
                                });
                    }
                });
    }
}
