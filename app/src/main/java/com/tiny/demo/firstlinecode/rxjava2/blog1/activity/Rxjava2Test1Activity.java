package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 基本使用
 */
public class Rxjava2Test1Activity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        /*上游和下游就分别对应着RxJava中的Observable和Observer，它们之间的连接就对应着subscribe()*/
        //创建一个上游 Observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("observer onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.e("observer onNext --> " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("observer onError");
            }

            @Override
            public void onComplete() {
                LogUtils.e("observer onComplete");
            }
        };
        //建立连接
        observable.subscribe(observer);
        /**
         * 注意，只有当上游和下游简历连接之后，上游才会开始发送时间，也就是调用了subscribe()方法之后才开始发送事件。
         */
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        //引以为傲的链式调用
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onNext(6);
                emitter.onError(new Throwable("猫了个咪啊"));
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("observer onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.e("observer onNext --> " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("observer onError --> " + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e("observer onComplete");
            }
        });
    }

    /**
     * 接下来解释一下其中两个陌生的玩意：ObservableEmitter和Disposable.
     * <p>
     * ObservableEmitter： Emitter是发射器的意思，那就很好猜了，这个就是用来发出事件的，它可以发出三种类型的事件，
     * 通过调用emitter的onNext(T value)、onComplete()和onError(Throwable error)就可以分别发出next事件、complete事件和error事件。
     * <p>
     * 但是，请注意，并不意味着你可以随意乱七八糟发射事件，需要满足一定的规则：
     * <p>
     * 上游可以发送无限个onNext, 下游也可以接收无限个onNext.
     * 当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
     * 当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
     * 上游可以不发送onComplete或onError.
     * 最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError,
     * 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
     * <p>
     * 注: 关于onComplete和onError唯一并且互斥这一点, 是需要自行在代码中进行控制, 如果你的代码逻辑中违背了这个规则,
     * *并不一定会导致程序崩溃. ** 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了,
     * 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.
     */

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        //Observable发送多个onNext；上游不发送onComplete和onError
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int j = 0; j < 100; j++) {
                    e.onNext("next --> " + j);
                }
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(String value) {
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

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        //Observable发送多个onComplete,上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.e("Observable subscribe onNext --> " + 1);
                e.onNext(1);
                LogUtils.e("Observable subscribe onNext --> " + 2);
                e.onNext(2);
                e.onComplete();
                LogUtils.e("Observable subscribe onNext --> " + 3);
                e.onNext(3);
                LogUtils.e("Observable subscribe onNext --> " + 4);
                e.onNext(4);
                e.onComplete();
                LogUtils.e("Observable subscribe onNext --> " + 5);
                e.onNext(5);
                LogUtils.e("Observable subscribe onNext --> " + 6);
                e.onNext(6);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
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

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        //Observable发送多个onError, 当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.e("Observable subscribe onNext --> " + 1);
                e.onNext(1);
                LogUtils.e("Observable subscribe onNext --> " + 2);
                e.onNext(2);
                e.onError(new Throwable("猫了个咪1"));
                LogUtils.e("Observable subscribe onNext --> " + 3);
                e.onNext(3);
                LogUtils.e("Observable subscribe onNext --> " + 4);
                e.onNext(4);
                e.onError(new Throwable("猫了个咪2"));
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.e("onNext value --> " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError e --> " + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e("onComplete");
            }
        });
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onComplete();
                e.onNext(2);
                e.onError(new Throwable("猫了个咪啊"));
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.e("onNext value --> " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError --> " + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e("onComplete");
            }
        });
    }

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onError(new Throwable("猫了个咪啊"));
                e.onNext(2);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.e("onNext value --> " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError --> " + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e("onComplete");
            }
        });
    }

    /**
     * 介绍了ObservableEmitter, 接下来介绍Disposable, 这个单词的字面意思是一次性用品,用完即可丢弃的.
     * 那么在RxJava中怎么去理解它呢, 对应于上面的水管的例子, 我们可以把它理解成两根管道之间的一个机关,
     * 当调用它的dispose()方法时, 它就会将两根管道切断, 从而导致下游收不到事件.
     * <p>
     * 注意: 调用dispose()并不会导致上游不再继续发送事件, 上游会继续发送剩余的事件.
     * 从运行结果我们看到, 在收到onNext 2这个事件后, 切断了水管, 但是上游仍然发送了3, complete, 4这几个事件,
     * 而且上游并没有因为发送了onComplete而停止. 同时可以看到下游的onSubscribe()方法是最先调用的.
     * <p>
     * Disposable的用处不止这些, 后面讲解到了线程的调度之后, 我们会发现它的重要性. 随着后续深入的讲解,
     * 我们会在更多的地方发现它的身影.
     */

    @OnClick(R.id.btn_test8)
    public void onViewClicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e("emit 1");
                emitter.onNext(1);
                LogUtils.e("emit 2");
                emitter.onNext(2);
                LogUtils.e("emit 3");
                emitter.onNext(3);
                LogUtils.e("emit complete");
                emitter.onComplete();
                LogUtils.e("emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.e("onNext --> " + value);
                if (2 == value) {
                    LogUtils.e("dispose");
                    disposable.dispose();
                    LogUtils.e("disposable.isDisposed() --> " + disposable.isDisposed());
                }
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
     * Disposable的用处不止这些, 后面讲解到了线程的调度之后, 我们会发现它的重要性. 随着后续深入的讲解,
     * 我们会在更多的地方发现它的身影.
     * 另外, subscribe()有多个重载的方法:
     * public final Disposable subscribe() {}
     * public final Disposable subscribe(Consumer<? super T> onNext) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Consumer<? super Disposable> onSubscribe) {}
     * public final void subscribe(Observer<? super T> observer) {}
     * 最后一个带有Observer参数的我们已经使用过了,这里对其他几个方法进行说明.
     * 不带任何参数的subscribe() 表示下游不关心任何事件,你上游尽管发你的数据去吧, 老子可不管你发什么.
     * 带有一个Consumer参数的方法表示下游只关心onNext事件, 其他的事件我假装没看见,
     * 因此我们如果只需要onNext事件可以这么写:
     * 其他几个方法同理, 这里就不一一解释了.
     */
    @OnClick(R.id.btn_test9)
    public void onViewBtnTest9Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e("emit 1");
                emitter.onNext(1);
                LogUtils.e("emit 2");
                emitter.onNext(2);
                LogUtils.e("emit 3");
                emitter.onNext(3);
                LogUtils.e("emit complete");
                emitter.onComplete();
                LogUtils.e("emit 4");
                emitter.onNext(4);
            }
        }).subscribe();
    }

    @OnClick(R.id.btn_test10)
    public void onViewBtnTest10Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e("emit 1");
                emitter.onNext(1);
                LogUtils.e("emit 2");
                emitter.onNext(2);
                LogUtils.e("emit 3");
                emitter.onNext(3);
                LogUtils.e("emit complete");
                emitter.onComplete();
                LogUtils.e("emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.e("accept --> " + integer);
            }
        });
    }

    @OnClick(R.id.btn_test11)
    public void onViewBtnTest11Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e("emit 1");
                emitter.onNext(1);
                LogUtils.e("emit 2");
                emitter.onNext(2);
                LogUtils.e("emit 3");
                emitter.onNext(3);
                LogUtils.e("emit error");
                emitter.onError(new Throwable("猫了个咪啊"));
                LogUtils.e("emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.e("accept onNext --> " + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtils.e("accept onError --> " + throwable.getMessage());
            }
        });
    }

    @OnClick(R.id.btn_test12)
    public void onViewBtnTest12Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e("emit 1");
                emitter.onNext(1);
                LogUtils.e("emit 2");
                emitter.onNext(2);
                LogUtils.e("emit 3");
                emitter.onNext(3);
//                LogUtils.e("emit error");
//                emitter.onError(new Throwable("猫了个咪啊"));
                LogUtils.e("emit 4");
                emitter.onNext(4);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.e("accept onNext --> " + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtils.e("accept onError --> " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.e("Action onComplete");
            }
        });
    }

    @OnClick(R.id.btn_test13)
    public void onViewBtnTest14Clicked() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e("emit 1");
                emitter.onNext(1);
                LogUtils.e("emit 2");
                emitter.onNext(2);
                LogUtils.e("emit 3");
                emitter.onNext(3);
                LogUtils.e("emit error");
                emitter.onError(new Throwable("猫了个咪啊"));
                LogUtils.e("emit 4");
                emitter.onNext(4);
//                emitter.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.e("accept onNext --> " + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtils.e("accept onError --> " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.e("Action onComplete");
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                LogUtils.e("accept onSubscribe disposable --> " + disposable);
            }
        });
    }
}
