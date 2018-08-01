package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 事件冲突
 *
 * @author tiny
 * @date 2018/3/27 下午1:46
 */
public class Chapter0305Activity extends AppCompatActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter0305Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter0305);
        ButterKnife.bind(this);
    }

    private int mLastXIntercept;
    private int mLastYIntercept;

    /**
     * 外部拦截法模板代码
     *
     * @param event
     * @return
     */
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                //父容器是否需要当前点击事件
                boolean condition = true;
                if (condition) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    ViewGroup parent;
    private int mLastX;
    private int mLastY;

    /**
     * 内部拦截法
     *
     * @param event
     * @return
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                parent.requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltaX = x = mLastX;
//                int deltaY = y = mLastY;
//                boolean condition = false;
//                //父容器是否需要此类点击事件
//                if (condition) {
//                    parent.requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//            default:
//                break;
//        }
//        mLastX = x;
//        mLastY = y;
//        return super.dispatchTouchEvent(event);
//    }

    /**
     * 内部拦截法，父容器做的修改
     *
     * @param event
     * @return
     */
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
//            return false;
//        } else {
//            return true;
//        }
//    }
    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        Chapter030501Activity.actionStart(Chapter0305Activity.this);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {

    }
}
