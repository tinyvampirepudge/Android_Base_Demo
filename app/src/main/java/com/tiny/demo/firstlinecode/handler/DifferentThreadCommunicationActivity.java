package com.tiny.demo.firstlinecode.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    子线程间相互发送消息
 * Created by tiny on 2017/10/27.
 * Version:
 */

public class DifferentThreadCommunicationActivity extends BaseActivity {
    @BindView(R.id.btn_handler_send_msg)
    Button btnHandlerSendMsg;
    @BindView(R.id.btn_handler_receive_msg)
    Button btnHandlerReceiveMsg;
    private Handler handler1;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_handler_thread;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_handler_send_msg)
    public void onBtnHandlerSendMsgClicked() {
        new Thread2().start();
    }

    @OnClick(R.id.btn_handler_receive_msg)
    public void onBtnHandlerReceiveMsgClicked() {
        new Thread1().start();
    }

    public class Thread1 extends Thread {

        public Handler getHandler1() {//注意，在run执行之前，返回的是null;
            return handler1;
        }

        @Override
        public void run() {
            Looper.prepare();
            handler1 = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    //这里处理消息；
                    LogUtils.INSTANCE.e("收到消息了，当前线程是：" + Thread.currentThread().getName() + ",msg.object --> " + msg.obj.toString());
                }
            };
            Looper.loop();
        }
    }

    public class Thread2 extends Thread {
        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = System.currentTimeMillis() + "";
                handler1.sendMessage(msg);
                LogUtils.INSTANCE.e("发送消息了，当前线程是：" + Thread.currentThread().getName() + ",msg.object --> " + msg.obj.toString());
                SystemClock.sleep(1000);
            }
        }
    }
}
