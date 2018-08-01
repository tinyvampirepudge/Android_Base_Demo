package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.rxjava2.blog1.Api;
import com.tiny.demo.firstlinecode.rxjava2.blog1.RetrofitProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Desc:    操作符 map，flatmap
 * Created by tiny on 2017/12/29.
 * Version:
 */
public class Rxjava2Test3Activity extends AppCompatActivity {

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
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test3);
        ButterKnife.bind(this);

        api = RetrofitProvider.get().create(Api.class);

    }

    /**
     * 普通的注册登录逻辑，注册成功后自动登录
     */
    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {

        //register
//        api.register(new RegisterRequest())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<RegisterRequest>() {
//                    @Override
//                    public void accept(RegisterRequest registerRequest) throws Exception {
//                        LogUtils.e("注册成功");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        LogUtils.e("注册失败");
//                    }
//                });
//
//        //login
//        api.login(new LoginRequest())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<LoginResponse>() {
//                    @Override
//                    public void accept(LoginResponse loginResponse) throws Exception {
//                        LogUtils.e("登录成功");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        LogUtils.e("登录失败");
//                    }
//                });
    }

    /**
     * map 操作符。
     */
    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {

                return "This is result " + integer;
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return "Hello, Seattle! " + s;
            }
        })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.e(s);
                    }
                });
    }

    /**
     * FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，
     * 然后将它们发射的事件合并后放进一个单独的Observable里.
     * 这里需要注意的是, flatMap并不保证事件的顺序.
     * flatmap -- 无序
     */
    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    list.add("I am value " + j + "," + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override

            public void accept(String s) throws Exception {
                LogUtils.e("accept s --> " + s);
            }
        });
    }

    /**
     * concatMap和flatMap的作用几乎一模一样, 只是它的结果是严格按照上游发送的顺序来发送的
     */
    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    list.add("I am value " + j + "," + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.e("concatMap accept --> " + s);
            }
        });
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
    }
}
