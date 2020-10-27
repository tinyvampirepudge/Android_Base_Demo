package com.tiny.demo.firstlinecode.rxjava2.blog1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ThreadUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Rxjava2Test6Activity extends AppCompatActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_test6);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onViewBtn1Clicked() {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emit) throws Exception {
                LogUtils.INSTANCE.e("emit 1");
                emit.onNext(1);
                LogUtils.INSTANCE.e("emit 2");
                emit.onNext(2);
                LogUtils.INSTANCE.e("emit 3");
                emit.onNext(3);
                LogUtils.INSTANCE.e("complete");
                emit.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downStream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.INSTANCE.e("onSubscribe");
                //缺少这行代码会在onError中抛出异常。
                /*io.reactivex.exceptions.MissingBackpressureException:
                create: could not emit value due to lack of requests*/
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.INSTANCE.e("onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.INSTANCE.e("onError: " + t.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e("onComplete");
            }
        };

        upstream.subscribe(downStream);
    }

    /**
     * 异步模式下，不调用s.request(Long.MAX_VALUE)方法的结果
     * 这次上游正确的发送了所有的事件, 但是下游一个事件也没有收到
     */
    @OnClick(R.id.btn_test2)
    public void onViewBtn2Clicked() {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emit) throws Exception {
                ThreadUtils.INSTANCE.logCurrThreadName("upstream");
                LogUtils.INSTANCE.e("emit 1");
                emit.onNext(1);
                LogUtils.INSTANCE.e("emit 2");
                emit.onNext(2);
                LogUtils.INSTANCE.e("emit 3");
                emit.onNext(3);
                LogUtils.INSTANCE.e("complete");
                emit.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downStream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.INSTANCE.e("onSubscribe");
                ThreadUtils.INSTANCE.logCurrThreadName("downStream");
//                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer s) {
                LogUtils.INSTANCE.e("onNext: " + s);
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.INSTANCE.e("onError: " + t.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.INSTANCE.e("onComplete");
            }
        };

        upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(downStream);
    }

    /**
     * 这是因为Flowable在设计的时候采用了一种新的思路也就是响应式拉取的方式来更好的解决上下游流速不均衡的问题,
     * 与我们之前所讲的控制数量和控制速度不太一样, 这种方式用通俗易懂的话来说就好比是叶问打鬼子, 我们把上游看成小日本,
     * 把下游当作叶问, 当调用Subscription.request(1)时, 叶问就说我要打一个! 然后小日本就拿出一个鬼子给叶问, 让他打,
     * 等叶问打死这个鬼子之后, 再次调用request(10), 叶问就又说我要打十个! 然后小日本又派出十个鬼子给叶问,
     * 然后就在边上看热闹, 看叶问能不能打死十个鬼子, 等叶问打死十个鬼子后再继续要鬼子接着打...

     所以我们把request当做是一种能力, 当成下游处理事件的能力, 下游能处理几个就告诉上游我要几个, 这样只要上游根据下游的
     处理能力来决定发送多少事件, 就不会造成一窝蜂的发出一堆事件来, 从而导致OOM. 这也就完美的解决之前我们所学到的两种方式
     的缺陷, 过滤事件会导致事件丢失, 减速又可能导致性能损失. 而这种方式既解决了事件丢失的问题, 又解决了速度的问题, 完美 !

     但是太完美的东西也就意味着陷阱也会很多, 你可能只是被它的外表所迷惑, 失去了理智, 如果你滥用或者不遵守规则,
     一样会吃到苦头.

     比如这里需要注意的是, 只有当上游正确的实现了如何根据下游的处理能力来发送事件的时候, 才能达到这种效果,
     如果上游根本不管下游的处理能力, 一股脑的瞎他妈发事件, 仍然会产生上下游流速不均衡的问题, 这就好比小日本管他叶问要打几个,
     老子直接拿出1万个鬼子, 这尼玛有种打死给我看看? 那么如何正确的去实现上游呢, 这里先卖个关子, 之后我们再来讲解.

     */

    /**
     * 学习了request, 我们就可以解释上面的两段代码了.
     * <p>
     * 首先第一个同步的代码, 为什么上游发送第一个事件后下游就抛出了MissingBackpressureException异常,
     * 这是因为下游没有调用request, 上游就认为下游没有处理事件的能力, 而这又是一个同步的订阅, 既然下游处理不了,
     * 那上游不可能一直等待吧, 如果是这样, 万一这两根水管工作在主线程里, 界面不就卡死了吗, 因此只能抛个异常来提醒我们.
     * 那如何解决这种情况呢, 很简单啦, 下游直接调用request(Long.MAX_VALUE)就行了, 或者根据上游发送事件的数量来
     * request就行了, 比如这里request(3)就可以了.
     * <p>
     * 然后我们再来看看第二段代码, 为什么上下游没有工作在同一个线程时, 上游却正确的发送了所有的事件呢?
     * 这是因为在Flowable里默认有一个大小为128的水缸, 当上下游工作在不同的线程中时, 上游就会先把事件发送到这个水缸中,
     * 因此, 下游虽然没有调用request, 但是上游在水缸中保存着这些事件, 只有当下游调用request时, 才从水缸里取出事件发给下游.
     */

    @OnClick(R.id.btn_test3)
    public void onViewBtn3Clicked() {
        //初始化，将消息放进水缸
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emit) throws Exception {
                ThreadUtils.INSTANCE.logCurrThreadName("upstream");
                LogUtils.INSTANCE.e("emit 1");
                emit.onNext(1);
                LogUtils.INSTANCE.e("emit 2");
                emit.onNext(2);
                LogUtils.INSTANCE.e("emit 3");
                emit.onNext(3);
                LogUtils.INSTANCE.e("complete");
                emit.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        ThreadUtils.INSTANCE.logCurrThreadName("downstream");
                        LogUtils.INSTANCE.e("onSubscribe");
                        subscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.INSTANCE.e("onError： " + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e("onComplete");
                    }
                });
    }

    @OnClick(R.id.btn_test4)
    public void onViewBtn4Clicked() {
        //request数据
        if (subscription != null) {
            subscription.request(1);
        }
    }

    /**
     * 水缸的大小是128，验证下
     */
    @OnClick(R.id.btn_test5)
    public void onViewBtn5Clicked() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int j = 0; j < 129; j++) {
                    LogUtils.INSTANCE.e("emit " + j);
                    e.onNext(j);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.INSTANCE.e("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.INSTANCE.e("onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.INSTANCE.e("onError:" + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.INSTANCE.e("onComplete");
                    }
                });
    }
}
