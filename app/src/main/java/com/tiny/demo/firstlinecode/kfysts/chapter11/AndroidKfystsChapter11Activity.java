package com.tiny.demo.firstlinecode.kfysts.chapter11;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ThreadUtils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 第十一章 Android的线程和线程池
 *
 * @author tiny
 * @date 2018/4/21 下午11:12
 */
public class AndroidKfystsChapter11Activity extends AppCompatActivity {
    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    private DownloadFileTask downloadFileTask;
    private Handler handlerSub;
    private ExecutorService fixedThreadPool;
    private ExecutorService cachedThreadPool;
    private ScheduledExecutorService scheduledThreadPool;
    private ExecutorService singleThreadExecutor;

    private ExecutorService fixedThreadPool1;
    private ExecutorService cachedThreadPool1;
    private ScheduledExecutorService scheduledThreadPool1;
    private ExecutorService singleThreadExecutor1;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AndroidKfystsChapter11Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter11);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        downloadFileTask = new DownloadFileTask();
        try {
            String s = "http://www.baidu.com";
            downloadFileTask.execute(new URL(s), new URL(s), new URL(s), new URL(s), new URL(s), new URL(s));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        if (downloadFileTask != null && !downloadFileTask.isCancelled()) {
            downloadFileTask.cancel(true);
        }
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                new MyAsyncTask("子线程初始化的AsyncTask").execute("Sub Thread");
            }
        }.start();

    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        new MyAsyncTask("MyAsyncTask#1").execute("first");
        new MyAsyncTask("MyAsyncTask#2").execute("second");
        new MyAsyncTask("MyAsyncTask#3").execute("third");
        new MyAsyncTask("MyAsyncTask#4").execute("fourth");
        new MyAsyncTask("MyAsyncTask#5").execute("fifth");
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {
        public final String TAG = MyAsyncTask.class.getSimpleName();
        private String nName = "MyAsyncTask";

        public MyAsyncTask(String nName) {
            super();
            this.nName = nName;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return nName;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LogUtils.INSTANCE.e(TAG, result + " execute finish at " + sdf.format(new Date()));
            btnTest2.setText(result);
        }
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {

        HandlerThread handlerThreadMain = new HandlerThread("main");
        handlerThreadMain.start();
        Handler handlerMain = new Handler(handlerThreadMain.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ThreadUtils.INSTANCE.logCurrThreadName();
            }
        };
        handlerMain.sendEmptyMessage(1);
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        new Thread("sub") {
            @Override
            public void run() {
                super.run();
                HandlerThread handlerThreadSub = new HandlerThread("sub");
                handlerThreadSub.start();
                handlerSub = new Handler(handlerThreadSub.getLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        ThreadUtils.INSTANCE.logCurrThreadName();
                    }
                };
            }
        }.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handlerSub.sendEmptyMessageDelayed(1, 1000);
            }
        }, 1000);
    }

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        Intent service = new Intent(this, LocalIntentService.class);
        service.putExtra("task_action", "com.tinytongtong.task1");
        startService(service);
        service.putExtra("task_action", "com.tinytongtong.task2");
        startService(service);
        service.putExtra("task_action", "com.tinytongtong.task3");
        startService(service);
        service.putExtra("task_action", "com.tinytongtong.task4");
        startService(service);
        service.putExtra("task_action", "com.tinytongtong.task5");
        startService(service);
    }

    Runnable command = new Runnable() {
        @Override
        public void run() {
            LogUtils.INSTANCE.e("command run() start");
            ThreadUtils.INSTANCE.logCurrThreadName();
            SystemClock.sleep(2000);
            LogUtils.INSTANCE.e("command run() end");
        }
    };

    @OnClick(R.id.btn_test8)
    public void onBtnTest8Clicked() {
        fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(command);

        cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(command);

        scheduledThreadPool = Executors.newScheduledThreadPool(4);
        //2000ms后执行command
        scheduledThreadPool.schedule(command, 2000, TimeUnit.MILLISECONDS);
        //延迟10ms后，每隔1000ms执行一次command
        scheduledThreadPool.scheduleAtFixedRate(command, 10, 1000, TimeUnit.MILLISECONDS);

        singleThreadExecutor = Executors.newSingleThreadExecutor();
        scheduledThreadPool.execute(command);
    }

    @OnClick(R.id.btn_test9)
    public void onBtnTest9Clicked() {
        scheduledThreadPool1 = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-scheduled-pool-%d").daemon(true).build());
        //2000ms后执行command
        scheduledThreadPool1.schedule(command, 2000, TimeUnit.MILLISECONDS);
        //延迟10ms后，每隔1000ms执行一次command
        scheduledThreadPool1.scheduleAtFixedRate(command, 10, 1000, TimeUnit.MILLISECONDS);
    }

    public static class LocalIntentService extends IntentService {
        public static final String TAG = LocalIntentService.class.getSimpleName();

        public LocalIntentService() {
            super(TAG);
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            String action = intent.getStringExtra("task_action");
            LogUtils.INSTANCE.e(TAG, "receive task :" + action);
            SystemClock.sleep(3000);
            LogUtils.INSTANCE.e(TAG, "finish task :" + action);
            if ("com.tinytongtong.task1".equals(action)) {
                LogUtils.INSTANCE.e(TAG, "handle task: " + action);
            }
        }

        @Override
        public void onDestroy() {
            LogUtils.INSTANCE.e(TAG, "service destroyed!");
            super.onDestroy();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fixedThreadPool != null && !fixedThreadPool.isShutdown()) {
            fixedThreadPool.shutdown();
        }
        if (cachedThreadPool != null && !cachedThreadPool.isShutdown()) {
            cachedThreadPool.shutdown();
        }
        if (scheduledThreadPool != null && !scheduledThreadPool.isShutdown()) {
            scheduledThreadPool.shutdown();
        }
        if (singleThreadExecutor != null && !singleThreadExecutor.isShutdown()) {
            singleThreadExecutor.shutdown();
        }

        if (fixedThreadPool1 != null && !fixedThreadPool1.isShutdown()) {
            fixedThreadPool1.shutdown();
        }
        if (cachedThreadPool1 != null && !cachedThreadPool1.isShutdown()) {
            cachedThreadPool1.shutdown();
        }
        if (scheduledThreadPool1 != null && !scheduledThreadPool1.isShutdown()) {
            scheduledThreadPool1.shutdown();
        }
        if (singleThreadExecutor1 != null && !singleThreadExecutor1.isShutdown()) {
            singleThreadExecutor1.shutdown();
        }
    }
}
