package com.tiny.demo.firstlinecode.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tiny.demo.firstlinecode.R;

/**
 * @Description: Handler Sync Barrier
 * android.os.MessageQueue#postSyncBarrier
 * @Author wangjianzhou@qding.me
 * @Date 2019-05-17 08:55
 * @Version
 */
public class HandlerSyncBarrierActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, HandlerSyncBarrierActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_sync_barrier);

        new Thread(() -> {
            Looper.prepare();
            Handler handler = new Handler();
            Looper.loop();// loop方法阻塞了线程。
            Log.e("猫了个咪","after Looper.loop()");// 这行代码永远不会执行。
        }).start();

        Handler mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        System.out.println("接收到同步消息:" + msg.obj);
                        break;
                    case 2:
                        System.out.println("接收到异步消息:" + msg.obj);
                        break;
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            // 发送一个同步消息
            Message syncMsg = Message.obtain();
            syncMsg.what = 1;
            syncMsg.obj = "同步消息" + i;
            mainHandler.sendMessage(syncMsg);

            // 发送一个同步消息
            Message asyncMsg = Message.obtain();
            asyncMsg.what = 2;
            asyncMsg.obj = "异步消息" + i;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                asyncMsg.setAsynchronous(true);
            }
            mainHandler.sendMessage(asyncMsg);
        }
    }
}
