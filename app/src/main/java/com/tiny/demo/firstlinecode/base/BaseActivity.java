package com.tiny.demo.firstlinecode.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tiny.demo.firstlinecode.activity.activity_stack_manager.ActivityCollector;
import com.tiny.demo.firstlinecode.broadcastreceiver.LoginActivity;
import com.tiny.demo.firstlinecode.common.utils.AppUtils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 87959 on 2017/3/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;
    private OfflineReceiver offlineReceiver;
    protected final String TAG = this.getClass().getSimpleName();
    private Disposable disposable;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        View contentView = getLayoutInflater().inflate(setContentLayout(), null);
        setContentView(contentView);
        ButterKnife.bind(this);
        mContext = this;
        LogUtils.e("class name --> " + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        buildContentView();
        contentView.postDelayed(() -> initViewData(), 300);

        /**
         * 每隔一段时间就会发送一个事件，这个事件是从0开始，不断增1的数字。
         */
        Observable.interval(4, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        LogUtils.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Boolean result = AppUtils.isAppInBackgroundInternal(mContext,"com.tiny.demo.firstlinecode");
                        LogUtils.e(TAG,"onNext 是否运行在后台："+result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "onComplete");
                    }
                });
    }

    protected abstract int setContentLayout();

    protected abstract void buildContentView();

    protected abstract void initViewData();

    public void setTheme() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null){
            disposable.dispose();
        }
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.tiny.firstlinecode.FORCE_OFFLINE");
        offlineReceiver = new OfflineReceiver();
        registerReceiver(offlineReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (offlineReceiver != null) {
            unregisterReceiver(offlineReceiver);
            offlineReceiver = null;
        }
    }

    class OfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", (dialog, which) -> {
                ActivityCollector.finishAll();
                Intent intent1 = new Intent(mContext, LoginActivity.class);
                startActivity(intent1);
            });
            builder.show();
        }
    }


    // 页面切换方法，不带参数
    public void activitySwitch(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }

    // 页面切换方法，带参数
    public void activitySwitchWithBundle(Class<?> cls, Bundle args) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtras(args);
        startActivity(intent);
    }
}
