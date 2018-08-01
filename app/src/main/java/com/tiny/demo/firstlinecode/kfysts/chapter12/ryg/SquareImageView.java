package com.tiny.demo.firstlinecode.kfysts.chapter12.ryg;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Desc:    打造一个正方形的ImageView。
 *
 * @author tiny
 * @date 2018/5/27 上午1:19
 */

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
