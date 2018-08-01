package com.tiny.demo.firstlinecode.kfysts.chapter02.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    复用之前的Book相关的aidl文件
 *
 * @author tiny
 * @date 2018/3/8 上午12:47
 */
public class Book1ManagerActivity extends AppCompatActivity {
    public static final String TAG = "BMS-Activity";
    @BindView(R.id.btn_bind_on_thread1)
    Button btnBindOnThread1;
    @BindView(R.id.btn_bind_on_thread2)
    Button btnBindOnThread2;

    public static final int MESSAGE_MSG_BOOK_ARRIVED = 1;
    @BindView(R.id.btn_unbind_on_thread1)
    Button btnUnbindOnThread1;
    @BindView(R.id.btn_unbind_on_thread2)
    Button btnUnbindOnThread2;

    private IBook1Manager mRemoteBook1Manager1;
    private IBook1Manager mRemoteBook1Manager2;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Book1ManagerActivity.class);
        context.startActivity(starter);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_MSG_BOOK_ARRIVED:
                    LogUtils.e(TAG, "receive new book:" + msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    private ServiceConnection conn1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e(TAG, "onServiceConnected");
            ThreadUtils.logCurrThreadName(TAG + " conn1 onServiceConnected");
            IBook1Manager bookManager = IBook1Manager.Stub.asInterface(service);
            try {
                mRemoteBook1Manager1 = bookManager;
                List<Book> list = bookManager.getBookList();
                LogUtils.e(TAG, "query book list, list type:" + list.getClass().getCanonicalName());
                LogUtils.e(TAG, "query book list:" + list.toString());

                Book newBook = new Book(3, "Android进阶");
                bookManager.addBook(newBook);
                LogUtils.e(TAG, "newBook:" + newBook);
                List<Book> newList = bookManager.getBookList();
                LogUtils.e(TAG, "query new book list:" + newList.toString());
                bookManager.registerListener(iOnNewBookArrivedListener1);
                LogUtils.e(TAG, "register iOnNewBookArrivedListener1 --> " + iOnNewBookArrivedListener1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e(TAG, "onServiceDisconnected");
            ThreadUtils.logCurrThreadName(TAG + " conn1 onServiceDisconnected");
            mRemoteBook1Manager1 = null;
        }
    };

    private ServiceConnection conn2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e(TAG, "onServiceConnected");
            //主线程
            ThreadUtils.logCurrThreadName(TAG + " conn2 onServiceDisconnected");
            IBook1Manager bookManager = IBook1Manager.Stub.asInterface(service);
            try {
                mRemoteBook1Manager2 = bookManager;
                List<Book> list = bookManager.getBookList();
                LogUtils.e(TAG, "query book list, list type:" + list.getClass().getCanonicalName());
                LogUtils.e(TAG, "query book list:" + list.toString());

                Book newBook = new Book(3, "IOS进阶");
                bookManager.addBook(newBook);
                LogUtils.e(TAG, "newBook:" + newBook);
                List<Book> newList = bookManager.getBookList();
                LogUtils.e(TAG, "query new book list:" + newList.toString());
                bookManager.registerListener(iOnNewBookArrivedListener2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e(TAG, "onServiceDisconnected");
            //主线程
            ThreadUtils.logCurrThreadName(TAG + " conn2 onServiceDisconnected");
            mRemoteBook1Manager2 = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1_manager);
        ButterKnife.bind(this);

    }

    private IOnNewBookArrivedListener iOnNewBookArrivedListener1 = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_MSG_BOOK_ARRIVED, newBook).sendToTarget();
            //客户端的Binder线程池
            //sub Thread,name --> Binder:6670_3
            ThreadUtils.logCurrThreadName(TAG + " iOnNewBookArrivedListener1");
        }
    };
    private IOnNewBookArrivedListener iOnNewBookArrivedListener2 = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_MSG_BOOK_ARRIVED, newBook).sendToTarget();
            //客户端的Binder线程池
            //sub Thread,name --> Binder:6670_3
            ThreadUtils.logCurrThreadName(TAG + " iOnNewBookArrivedListener2");
        }
    };

    @Override
    protected void onDestroy() {
        unbindService1();
        unbindService2();
        super.onDestroy();
    }

    private void bindService1() {
        Intent intent = new Intent();
        intent.setAction("com.tiny.demo.firstlinecode.AIDL.Book1ManagerService");
        intent.setPackage("com.tiny.demo.firstlinecode");
        boolean bindResult = bindService(intent, conn1, Context.BIND_AUTO_CREATE);
        LogUtils.e(TAG, "bindResult --> " + bindResult);
    }

    private void unbindService1() {
        if (mRemoteBook1Manager1 != null) {
            if (mRemoteBook1Manager1.asBinder().isBinderAlive()) {
                try {
                    LogUtils.e(TAG, "unregister listener:" + iOnNewBookArrivedListener1);
                    mRemoteBook1Manager1.unregisterListener(iOnNewBookArrivedListener1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            unbindService(conn1);
        }
    }

    private void bindService2() {
        Intent intent = new Intent();
        intent.setAction("com.tiny.demo.firstlinecode.AIDL.Book1ManagerService");
        intent.setPackage("com.tiny.demo.firstlinecode");
        boolean bindResult = bindService(intent, conn2, Context.BIND_AUTO_CREATE);
        LogUtils.e(TAG, "bindResult --> " + bindResult);
    }

    private void unbindService2() {
        if (mRemoteBook1Manager2 != null) {
            if (mRemoteBook1Manager2.asBinder().isBinderAlive()) {
                try {
                    LogUtils.e(TAG, "unregister listener:" + iOnNewBookArrivedListener2);
                    mRemoteBook1Manager2.unregisterListener(iOnNewBookArrivedListener2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            unbindService(conn2);
        }
    }

    @OnClick({R.id.btn_bind_on_thread1, R.id.btn_unbind_on_thread1, R.id.btn_bind_on_thread2, R.id.btn_unbind_on_thread2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bind_on_thread1:
                bindService1();
                break;
            case R.id.btn_unbind_on_thread1:
                unbindService1();
                break;
            case R.id.btn_bind_on_thread2:
                bindService2();
                break;
            case R.id.btn_unbind_on_thread2:
                unbindService2();
                break;
        }
    }
}
