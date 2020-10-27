package com.tiny.demo.firstlinecode.kfysts.chapter02.messenger;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    Messenger进行进程间通信
 *
 * @author tiny
 * @date 2018/3/7 下午1:35
 */
public class MessengerActivity extends AppCompatActivity {
    public static final String TAG = MessengerActivity.class.getSimpleName();
    @BindView(R.id.bind)
    Button btnBind;
    @BindView(R.id.unbind)
    Button btnUnbind;
    @BindView(R.id.callback)
    TextView tvCallback;

    private static int count = 1;

    /**
     * Flag indicating whether we have called bind on the service.
     */
    boolean mIsBound;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, MessengerActivity.class);
        context.startActivity(starter);
    }

    private Messenger mGetReplyMessenger = new Messenger(new IncomingHandler());

    private static class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVER:
                    LogUtils.INSTANCE.e(TAG, "Receive msg from Service:" + msg.getData().get("reply"));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    private Messenger mService;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.INSTANCE.e(TAG, "onServiceConnected");
            mService = new Messenger(service);
            tvCallback.setText("Attached.");

            // We want to monitor the service for as long as we are
            // connected to it.
            try {
                Message msg = Message.obtain(null, MessengerService.MSG_REGISTER_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                mService.send(msg);

                // Give it some value as an example.
                msg = Message.obtain(null, MessengerService.MSG_FROM_CLIENT);
                Bundle data = new Bundle();
                data.putString("msg", "Hello, this is client");
                msg.setData(data);
                //注意下面这句
                msg.replyTo = mGetReplyMessenger;
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
                // In this case the service has crashed before we could even
                // do anything with it; we can count on soon being
                // disconnected (and then reconnected if it can be restarted)
                // so there is no need to do anything here.
            }

            // As part of the sample, tell the user what happened.
            ToastUtils.showSingleToast(getString(R.string.remote_service_connected));

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.INSTANCE.e(TAG, "onServiceDisconnected");
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
            tvCallback.setText("Disconnected.");

            // As part of the sample, tell the user what happened.
            ToastUtils.showSingleToast(getString(R.string.remote_service_disconnected));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);

        tvCallback.setText("Not attached.");
    }

    private void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because there is no reason to be able to let other
        // applications replace our component.

        //bind service
        Intent intent = new Intent();
        intent.setAction("com.tiny.demo.firstlinecode.IPC_BY_MESSENGER");
        //this is important
        //这里只需设置应用包名，而不是MessengerService.class的全路径
        intent.setPackage("com.tiny.demo.firstlinecode");

        boolean bindResult = bindService(intent, conn, Service.BIND_AUTO_CREATE);
        LogUtils.INSTANCE.e(TAG, "bindResult --> " + bindResult);
        mIsBound = true;
        tvCallback.setText("Binding.");
    }


    private void sendMessageToServer() {
        if (mIsBound) {
            // If we have received the service, and hence registered with
            // it, then now is the time to unregister.
            if (mService != null) {
                try {
                    Message msg = Message.obtain(null, MessengerService.MSG_FROM_CLIENT);
                    Bundle data = new Bundle();
                    data.putString("msg", "Hello Server, 这是我" + count + "次发送消息给你了，还他妈不回我");
                    data.putInt("count", count);
                    count++;
                    msg.setData(data);
                    msg.replyTo = mGetReplyMessenger;
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    // There is nothing special we need to do if the service
                    // has crashed.
                }
            }
        }
    }

    private void doUnbindService() {
        if (mIsBound) {
            // If we have received the service, and hence registered with
            // it, then now is the time to unregister.
            if (mService != null) {
                try {
                    Message msg = Message.obtain(null, MessengerService.MSG_UNREGISTER_CLIENT);
                    msg.replyTo = mGetReplyMessenger;
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    // There is nothing special we need to do if the service
                    // has crashed.
                }
            }
            // Detach our existing connection.
            unbindService(conn);
            mIsBound = false;
            tvCallback.setText("Unbinding.");
        }
    }

    @OnClick({R.id.bind, R.id.unbind, R.id.send_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bind:
                doBindService();
                break;
            case R.id.unbind:
                doUnbindService();
                break;
            case R.id.send_msg:
                sendMessageToServer();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        doUnbindService();
        super.onDestroy();
    }
}
