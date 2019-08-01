package com.tiny.demo.firstlinecode.kfysts.chapter02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.Book1ManagerActivity;
import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.BookManagerService;
import com.tiny.demo.firstlinecode.kfysts.chapter02.binderpool.BinderPoolActivity;
import com.tiny.demo.firstlinecode.kfysts.chapter02.contentprovider.ContentProviderTestActivity;
import com.tiny.demo.firstlinecode.kfysts.chapter02.file.Chapter02FileSharingActivity;
import com.tiny.demo.firstlinecode.kfysts.chapter02.messenger.MessengerActivity;
import com.tiny.demo.firstlinecode.kfysts.chapter02.socket.SocketIPCActivity;
import com.tinytongtong.tinyutils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    IPC机制
 * Created by tiny on 2018/2/25 下午9:53
 * Version:
 */
public class AndroidKfystsChapter02Activity extends AppCompatActivity {
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AndroidKfystsChapter02Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter02);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        MultipleProcessActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        //client调用参见aidl-test项目，server端参见本项目
        /**
         * service
         * {@link BookManagerService}
         */
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        //通过Intent在进程间传递数据，这里不再赘述
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        //通过文件在进程间共享数据
        //当前Activity的onResume()方法中序列化一个User对象到sd卡上的一个文件里，然后在下一个Activity的OnResume()中去反序列化。
        Chapter02FileSharingActivity.actionStart(AndroidKfystsChapter02Activity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        persistToFile();
    }

    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(1, "Hello world!", false);
                File dir = getCacheDir();
                File cachedFile = new File(dir, "user");
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(new FileOutputStream(cachedFile));
                    oos.writeObject(user);
                    LogUtils.e("persist user:" + user);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * Messenger
     */
    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        MessengerActivity.actionStart(AndroidKfystsChapter02Activity.this);
    }

    /**
     * AIDL
     */
    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        Book1ManagerActivity.actionStart(AndroidKfystsChapter02Activity.this);
    }

    /**
     * ContentProvider
     */
    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
        ContentProviderTestActivity.actionStart(AndroidKfystsChapter02Activity.this);
    }

    /**
     * Socket
     */
    @OnClick(R.id.btn_test8)
    public void onBtnTest8Clicked() {
        SocketIPCActivity.actionStart(AndroidKfystsChapter02Activity.this);
    }

    /**
     * Binder Pool
     */
    @OnClick(R.id.btn_test9)
    public void onBtnTest9Clicked() {
        BinderPoolActivity.actionStart(AndroidKfystsChapter02Activity.this);
    }
}
