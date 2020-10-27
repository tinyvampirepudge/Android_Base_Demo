package com.tiny.demo.firstlinecode.kfysts.chapter02.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ThreadUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Desc:    远程Service的实现
 *
 * @author tiny
 * @date $date$ $time$
 */

public class Book1ManagerService extends Service {
    public static final String TAG = "BMS-Service";

    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);

    /**
     * java.util.concurrent包下的类，不是ArrayList的子类，但是实现了List接口
     * 它支持并发读写。这里直接使用它来进行自动的线程同步。
     * 跟他类似的还有个concurrentHashMap。
     */
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();

    private Binder mBinder = new IBook1Manager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListeners.register(listener);
            //服务端的Binder线程池
            //registerListener: sub Thread,name --> Binder:6824_2
            ThreadUtils.INSTANCE.logCurrThreadName(TAG + " registerListener");
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            //服务端的Binder线程池
            //sub Thread,name --> Binder:6824_3
            mListeners.unregister(listener);
            ThreadUtils.INSTANCE.logCurrThreadName(TAG + " unregisterListener");
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            //校验自定义权限
            int check = checkCallingOrSelfPermission("com.tiny.demo.firstlinecode.permission.ACCESS_BOOK_SERVICE");
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }

            //校验包名
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (!packageName.startsWith("com.tiny.demo.firstlinecode")) {
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.INSTANCE.e(TAG, "远程服务开启");
        ThreadUtils.INSTANCE.logCurrThreadName(TAG + " onCreate");
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));
        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //权限校验
        int check = checkCallingOrSelfPermission("com.tiny.demo.firstlinecode.permission.ACCESS_BOOK_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED) {
            return null;
        }
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed.set(true);
        LogUtils.INSTANCE.e(TAG, "远程服务销毁");
        super.onDestroy();
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        // new 出来的工作线程
        //sub Thread,name --> Thread-3
        ThreadUtils.INSTANCE.logCurrThreadName(TAG + " onNewBookArrived");
        mBookList.add(book);
        int N = mListeners.beginBroadcast();
        LogUtils.INSTANCE.e(TAG, "onNewBookArrived, notify listeners:" + N);
        for (int j = 0; j < N; j++) {
            IOnNewBookArrivedListener listener = mListeners.getBroadcastItem(j);
            if (null != listener) {
                LogUtils.INSTANCE.e(TAG, "onNewBokArrived, notify listener:" + listener);
                listener.onNewBookArrived(book);
            }
        }
        mListeners.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            // do background processing here......
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
