package com.tiny.demo.firstlinecode.rxjava2.blog5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Desc: rxbinding
 *
 * @author tiny
 * @date 2018/6/18 下午5:02
 */
public class Rxjava2Blog5EntryActivity extends AppCompatActivity {

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

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Blog5EntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_blog5_entry);
        ButterKnife.bind(this);

        /**
         * 默认实现，点击一次触发一次
         */
        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.INSTANCE.e("我被点击了");
            }
        });

        /**
         * 一秒内只能触发一次。
         * 利用操作符throttleFirst取时间间隔内第一次点击事件。
         * 同样利用操作符throttleLast、debounce也可以实现。
         */
        RxView.clicks(btnTest1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        LogUtils.INSTANCE.e("button clicked");
                    }
                });


        /**
         * 点击的多次监听
         * RxBinding文档中有这么一段话：

         Mapping an observable to an Android event (e.g., view clicks) is a direct mapping.
         The library is not responsible for supporting multiple observables bound to the same view.
         Multiple listeners to the same view events can be achieved through operators like publish(),
         share(), or replay(). Consult the RxJava documentation for which is appropriate for the
         behavior that you want.

         大意是说：Android是不能多次监听同一个点击事件。但利用RxJava的操作符，例如publish, share或replay可以实现。
         而RxBinding恰好支持对点击事件的多次监听。
         */
        Observable<Object> observable = RxView.clicks(btnTest2).share();

        Disposable disposable1 = observable.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                LogUtils.INSTANCE.e("第一次");
            }
        });
        CompositeDisposable mCompositeSubscription = new CompositeDisposable();
        mCompositeSubscription.add(disposable1);

        Disposable disposable2 = observable.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                LogUtils.INSTANCE.e("第二次");
            }
        });

        mCompositeSubscription.add(disposable2);
    }
}
