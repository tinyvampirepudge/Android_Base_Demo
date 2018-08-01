package com.tiny.demo.firstlinecode.view.dispatchevent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ScreenUtils;

public class ViewDispatchTouchEventActivity extends BaseActivity {

    private MyButton myButton1;
    private MyTextView myTextView1;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ViewDispatchTouchEventActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_view_dispatch_touch_event;
    }

    @Override
    protected void buildContentView() {
        myButton1 = (MyButton) findViewById(R.id.my_btn_1);
        //给button设置delegate
        final View parent = (View) myButton1.getParent();
        if (parent != null) {
            parent.post(() -> {
                // Post in the parent's message queue to make sure the parent
                // lays out its children before we call getHitRect()
                final Rect r = new Rect();
                myButton1.getHitRect(r);
                r.left -= ScreenUtils.dip2px(mContext, 200);
                r.top -= ScreenUtils.dip2px(mContext, 200);
                r.right -= ScreenUtils.dip2px(mContext, 100);
                r.bottom -= ScreenUtils.dip2px(mContext, 100);
                parent.setTouchDelegate(new TouchDelegate(r, myButton1) {
                    @Override
                    public boolean onTouchEvent(MotionEvent event) {
                        LogUtils.e("MyButton setTouchDelegate onTouchEvent");
                        return super.onTouchEvent(event);//正常情况下返回这个值即可。
//                        return true;
                    }
                });
            });
        }
        myButton1.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            String tag = "MyButton";
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    LogUtils.e(tag + " onTouch ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    LogUtils.e(tag + " onTouch ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    LogUtils.e(tag + " onTouch ACTION_UP");
                    break;
                default:
                    break;
            }
            return false;
        });
        myButton1.setOnClickListener(v -> LogUtils.e("MyButton onClick"));
        myButton1.setOnLongClickListener(v -> {
            LogUtils.e("MyButton onLongClick");
            return false;
        });

        myTextView1 = (MyTextView) findViewById(R.id.my_txt_1);
        myTextView1.setOnTouchListener((v, event) -> {
                    int action = event.getAction();
                    String tag = "MyTextView";
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            LogUtils.e(tag + " onTouch ACTION_DOWN");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            LogUtils.e(tag + " onTouch ACTION_MOVE");
                            break;
                        case MotionEvent.ACTION_UP:
                            LogUtils.e(tag + " onTouch ACTION_UP");
                            break;
                        default:
                            break;
                    }
                    return false;

                }
        );
    }

    @Override
    protected void initViewData() {

    }
}
