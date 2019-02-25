package com.tiny.demo.firstlinecode.test.view.loopviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * Created by tiny on 2018/1/23.
 * Time: 14:38
 * Version:
 */
public class LoopViewPager extends ViewPager {
    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        autoPollTask = new AutoPollTask(this);
    }

    private static final long TIME_AUTO_POLL = 3000L;
    private static AutoPollTask autoPollTask;
    private static boolean running; //标示是否正在自动轮询
    private static boolean canRun;//标示是否可以自动轮询,可在不需要的是否置false

    static class AutoPollTask implements Runnable {
        private final WeakReference<ViewPager> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(ViewPager reference) {
            this.mReference = new WeakReference<>(reference);
        }

        @Override
        public void run() {
            ViewPager viewPager = mReference.get();
            if (viewPager != null && running && canRun) {
                int currPos = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currPos + 1, true);
                viewPager.postDelayed(autoPollTask, TIME_AUTO_POLL);
            }
        }
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running) {
            stop();
        }
        canRun = true;
        running = true;
        postDelayed(autoPollTask, TIME_AUTO_POLL);
    }

    public void stop() {
        running = false;
        removeCallbacks(autoPollTask);
    }

    /**
     * onTouchEvent中的事件被viewPager处理过了，有些拦截不到
     * 所以在这里拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //只要接收到事件，就停止滚动。
        if (running) {
            stop();
        }
        switch (ev.getAction()) {
            //当手指抬起或者划出页面时
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_OUTSIDE:
                if (canRun) {
                    start();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
