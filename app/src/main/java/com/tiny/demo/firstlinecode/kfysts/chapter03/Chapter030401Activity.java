package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter03.eventview.EventViewA;
import com.tiny.demo.firstlinecode.kfysts.chapter03.eventview.EventViewB;
import com.tiny.demo.firstlinecode.kfysts.chapter03.eventview.EventViewC;
import com.tiny.demo.firstlinecode.kfysts.chapter03.eventview.EventViewGroupA;
import com.tiny.demo.firstlinecode.kfysts.chapter03.eventview.EventViewGroupB;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: View 事件分发学习
 * <p>
 * Android事件分发机制，大表哥带你慢慢深入
 * https://www.jianshu.com/p/fc0590afb1bf
 * @Author wangjianzhou
 * @Date 2019-07-10 11:42
 * @Version TODO
 */
public class Chapter030401Activity extends AppCompatActivity {
    public static final String TAG = Chapter030401Activity.class.getSimpleName();
    @BindView(R.id.ev_a)
    EventViewA evA;
    @BindView(R.id.ev_a1)
    EventViewA evA1;
    @BindView(R.id.ev_b)
    EventViewB evB;
    @BindView(R.id.ev_c)
    EventViewC evC;
    @BindView(R.id.evg_b)
    EventViewGroupB evgB;
    @BindView(R.id.evg_a)
    EventViewGroupA evgA;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter030401Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter030401);
        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "dispatchTouchEvent event:" + event.getAction());
        boolean result = super.dispatchTouchEvent(event);
        LogUtils.e(TAG, "dispatchTouchEvent result:" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "onTouchEvent event:" + event.getAction());
        boolean result = super.onTouchEvent(event);
        LogUtils.e(TAG, "onTouchEvent result:" + result);
        return result;
    }
}
