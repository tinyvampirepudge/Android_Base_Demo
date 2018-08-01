package com.tiny.demo.firstlinecode.kfysts.chapter02.binderpool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc: BinderPool线程池
 *
 * @author tiny
 * @date 2018/3/18 下午8:48
 */
public class BinderPoolActivity extends AppCompatActivity {
    public static final String TAG = "BinderPoolActivity";
    private BinderPool binderPool;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, BinderPoolActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        new Thread(() -> doWork()).start();
    }

    private void doWork() {
        binderPool = BinderPool.getInstance(BinderPoolActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CODE);
        ISecurityCenter mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
        LogUtils.e(TAG, "visit ISecurityCenter");
        String msg = "hello,world-安卓";
        LogUtils.e(TAG, "content:" + msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            LogUtils.e(TAG, "encrypt:" + password);
            LogUtils.e(TAG, "decrypt:" + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        LogUtils.e(TAG, "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        ICompute mCompute = ComputeImpl.asInterface(computeBinder);
        try {
            LogUtils.e(TAG, "3 + 5 = " + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        binderPool.disconnectBinderPoolService();
        super.onDestroy();
    }
}
