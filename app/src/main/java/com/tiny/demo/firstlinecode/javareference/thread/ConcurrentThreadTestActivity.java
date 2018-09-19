package com.tiny.demo.firstlinecode.javareference.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.tiny.tinymodule.util.LogUtils;
import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: Java并发编程：Callable、Future和FutureTask
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/18 5:49 PM
 * @Version TODO
 */
public class ConcurrentThreadTestActivity extends AppCompatActivity {
    public static final String TAG = ConcurrentThreadTestActivity.class.getSimpleName();


    @BindView(R.id.btn_java_callable_future)
    Button btnJavaCallableFuture;
    @BindView(R.id.btn_java_callable_futureTask)
    Button btnJavaCallableFutureTask;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ConcurrentThreadTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrent_thread_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_java_callable_future)
    public void onBtnJavaCallableFutureClicked() {
        ThreadUtils.logCurrThreadName(TAG + "  main start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();

        ThreadUtils.logCurrThreadName(TAG + "  main 111");

        try {
            LogUtils.e(TAG, "task执行结果:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            ThreadUtils.logCurrThreadName(TAG + "  main 222:" + i);
        }

        ThreadUtils.logCurrThreadName(TAG + "  main 主线程任务执行完毕");
    }

    class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            LogUtils.e(TAG, "在子线程进行计算");
            ThreadUtils.logCurrThreadName(TAG + " Task start");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100);
                sum += i;
                ThreadUtils.logCurrThreadName(TAG + " Task sum:" + sum);
            }
            ThreadUtils.logCurrThreadName(TAG + " Task end");
            return sum;
        }
    }

    @OnClick(R.id.btn_java_callable_futureTask)
    public void onBtnJavaCallableFutureTaskClicked() {

    }
}
