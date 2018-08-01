package com.tiny.demo.firstlinecode.kfysts.chapter02.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:    aidl
 * https://www.jianshu.com/p/2cf325d79352
 * @author tiny
 * @date 2018/3/2 下午5:35
 */
public class BookManagerService extends Service {
    public static final String TAG = "BookManagerService";
    private ArrayList<Book> mBookList = new ArrayList<>();
    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }
    };

    public BookManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e(TAG, "远程服务启动");
        mBookList.add(new Book(1, "book1"));
        mBookList.add(new Book(2, "book2"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        LogUtils.e(TAG, "unbindService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "远程服务销毁");
    }

}
