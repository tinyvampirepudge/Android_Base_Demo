package com.tiny.demo.firstlinecode.kfysts.chapter02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 多进程相关的入口
 *
 * @author tiny
 * @date 2018/3/2 上午11:35
 */
public class MultipleProcessActivity extends AppCompatActivity {
    public static int sUserId = 1;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MultipleProcessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_process);
        ButterKnife.bind(this);
        sUserId++;
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        Chapter02FirstActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        Chapter02SecondActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        Chapter02ThirdActivity.actionStart(this);
    }

//    public static int counter = 3;
//    private IBookManager bookManager;
//    private ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            Log.e("BookManagerService", "test onServiceConnected");
//            // 从远程service中获得AIDL实例化对象
//            bookManager = IBookManager.Stub.asInterface(service);
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            Log.e("BookManagerService", "test onServiceDisconnected");
//            bookManager = null;
//        }
//    };

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
//        Intent intent = new Intent();
//        intent.setAction("com.tiny.demo.firstlinecode.IBookManager");
//        //this is important
//        //这里只需设置应用包名，而不是IBookManager.class的全路径
//        intent.setPackage("com.tiny.demo.firstlinecode");
////        intent = new Intent(this, BookManagerService.class);
//        boolean bindResult = bindService(intent, conn, Service.BIND_AUTO_CREATE);
//        Log.e("BookManagerService", "test bindResult --> " + bindResult);
//        invokeRemoteService();
    }

//    private void invokeRemoteService() {
//        if (bookManager != null) {
//            try {
//                //添加书籍
//                bookManager.addBook(new Book(counter, "book" + counter++));
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                List<Book> bookList = bookManager.getBookList();
//                for (Book book : bookList) {
//                    Log.e("BookManagerService", book.toString());
//                }
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
//        unbindService(conn);
        super.onDestroy();
    }
}
