package com.tiny.demo.firstlinecode.handler;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

/**
 * Desc:    Handler发送消息的几种方式
 * Created by tiny on 2017/10/26.
 * Version:
 */
public class HandlerActivity extends BaseActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return true;
        }
    };

    Handler handler1 = new Handler(callback);
    private Button button;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_handler;
    }

    @Override
    protected void buildContentView() {
        button = (Button) findViewById(R.id.btn_handler);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1000;
                msg.arg1 = 10;
                handler.sendMessage(msg);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1001;
                msg.arg1 = 11;
                handler1.sendMessage(msg);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                button.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                button.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
            }
        }).start();
    }

    @Override
    protected void initViewData() {

    }
}
