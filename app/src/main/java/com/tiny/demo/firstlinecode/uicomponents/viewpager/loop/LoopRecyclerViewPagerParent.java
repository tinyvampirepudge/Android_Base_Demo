package com.tiny.demo.firstlinecode.uicomponents.viewpager.loop;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * Created by tiny on 2018/1/20.
 * Time: 19:54
 * Version:
 */

public class LoopRecyclerViewPagerParent extends RelativeLayout {
    private static final long TIME_AUTO_POLL = 2000;
    static AutoPollTask autoPollTask;
    private static boolean running; //标示是否正在自动轮询
    private static boolean canRun;//标示是否可以自动轮询,可在不需要的是否置false

    public LoopRecyclerViewPagerParent(Context context) {
        super(context);
    }

    public LoopRecyclerViewPagerParent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoopRecyclerViewPagerParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoopRecyclerViewPagerParent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void set(LoopRecyclerViewPager reference) {
        autoPollTask = new AutoPollTask(reference);
    }

    static class AutoPollTask implements Runnable {
        private final WeakReference<LoopRecyclerViewPager> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(LoopRecyclerViewPager reference) {
            this.mReference = new WeakReference<>(reference);
        }

        @Override
        public void run() {
            LoopRecyclerViewPager recyclerView = mReference.get();
            if (recyclerView != null && running && canRun) {
//                recyclerView.scrollBy(2, 2);
                int currPos = recyclerView.getActualCurrentPosition();
                LogUtils.e("currPos --> " + currPos);
                recyclerView.smoothScrollToPosition(currPos + 1);
                recyclerView.postDelayed(autoPollTask, TIME_AUTO_POLL);
            }
        }
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running)
            stop();
        canRun = true;
        running = true;
        postDelayed(autoPollTask, TIME_AUTO_POLL);
    }

    public void stop() {
        running = false;
        removeCallbacks(autoPollTask);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        getParent().requestDisallowInterceptTouchEvent(false);
        LogUtils.e("onTouchEvent");
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("onTouchEvent ACTION_DOWN");
                if (running)
                    stop();
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("onTouchEvent ACTION_UP");
            case MotionEvent.ACTION_CANCEL:
                LogUtils.e("onTouchEvent ACTION_CANCEL");
            case MotionEvent.ACTION_OUTSIDE:
                LogUtils.e("onTouchEvent ACTION_OUTSIDE");
                if (canRun)
                    start();
                break;
        }
        return super.onTouchEvent(e);
    }
}
