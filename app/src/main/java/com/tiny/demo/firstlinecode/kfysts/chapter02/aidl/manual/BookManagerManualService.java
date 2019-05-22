package com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.manual;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:    手写的AIDL相关的服务
 *
 * @author tiny
 * @date 2018/3/5 上午1:16
 */
public class BookManagerManualService extends Service {
    public static final String TAG = "BookManagerManualService";
    private ArrayList<Book> mBookList = new ArrayList<>();
    private Binder mBinder = new IBookManagerManual.BookManagerManualImpl() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }
    };

    public BookManagerManualService() {
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
