package com.tiny.demo.firstlinecode.view.dispatchevent.summary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * Created by wangjianzhou@qding.me on 2017/6/21.
 */

public class MyTextView extends TextView {
    private static final String TAG = MyTextView.class.getSimpleName();

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.INSTANCE.e(TAG + " dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.INSTANCE.e(TAG + " dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.INSTANCE.e(TAG + " dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.INSTANCE.e(TAG + " onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.INSTANCE.e(TAG + " onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.INSTANCE.e(TAG + " onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
