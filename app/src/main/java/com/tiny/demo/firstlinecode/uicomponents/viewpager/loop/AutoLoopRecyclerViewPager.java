package com.tiny.demo.firstlinecode.uicomponents.viewpager.loop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Desc:    在现有循环控件的基础上，添加自动滚动的功能。
 * Created by tiny on 2018/1/20.
 * Time: 18:20
 * Version:
 */

public class AutoLoopRecyclerViewPager extends LoopRecyclerViewPager {
    public AutoLoopRecyclerViewPager(Context context) {
        super(context);
        init();
    }

    public AutoLoopRecyclerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoLoopRecyclerViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        autoPollTask = new AutoPollTask(this);
    }

    private static final long TIME_AUTO_POLL = 2000;
    AutoPollTask autoPollTask;
    private boolean running; //标示是否正在自动轮询
    private boolean canRun;//标示是否可以自动轮询,可在不需要的是否置false

    static class AutoPollTask implements Runnable {
        private final WeakReference<AutoLoopRecyclerViewPager> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(AutoLoopRecyclerViewPager reference) {
            this.mReference = new WeakReference<>(reference);
        }

        @Override
        public void run() {
            AutoLoopRecyclerViewPager recyclerView = mReference.get();
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
//                recyclerView.scrollBy(2, 2);
                int currPos = recyclerView.getActualCurrentPosition();
                LogUtils.e("currPos --> " + currPos);
                recyclerView.smoothScrollToPosition(currPos + 1);
                recyclerView.postDelayed(recyclerView.autoPollTask, recyclerView.TIME_AUTO_POLL);
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
