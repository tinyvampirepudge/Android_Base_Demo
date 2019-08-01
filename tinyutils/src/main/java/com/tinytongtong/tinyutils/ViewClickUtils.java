package com.tinytongtong.tinyutils;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

/**
 * @Description: 给View的事件传递设置代理，放大View点击区域。
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:40
 * @Version
 */

public class ViewClickUtils {

    public static void setTouchDelegate(final Context mContext, final View view) {
        setTouchDelegate(mContext, view, 30, 30, 30, 30);
    }

    /**
     * 放大view的点击区域，受限于父view的范围。
     *
     * @param mContext
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setTouchDelegate(final Context mContext, final View view,
                                        final float left, final float top, final float right, final float bottom) {
        if (mContext == null || view == null) {
            return;
        }
        final View parent = (View) view.getParent();
        if (parent != null) {
            parent.post(new Runnable() {
                @Override
                public void run() {
                    // Post in the parent's message queue to make sure the parent
                    // lays out its children before we call getHitRect()
                    final Rect r = new Rect();
                    view.getHitRect(r);
                    r.left -= ScreenUtils.dip2px(mContext, left);
                    r.top -= ScreenUtils.dip2px(mContext, top);
                    r.right += ScreenUtils.dip2px(mContext, right);
                    r.bottom += ScreenUtils.dip2px(mContext, bottom);
                    parent.setTouchDelegate(new TouchDelegate(r, view) {
                        @Override
                        public boolean onTouchEvent(MotionEvent event) {
                            return super.onTouchEvent(event);//正常情况下返回这个值即可。
                        }
                    });
                }
            });
        }
    }
}
