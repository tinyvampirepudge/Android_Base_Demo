package com.tiny.demo.firstlinecode.common.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ScreenUtils;


/**
 * Created by wenli on 15/8/5.
 */
public class RedPotTextView extends TextView {
    private boolean bRedPotVisible = false;
    private int xToCenter = 0;
    private int yToCenter = 0;

    public RedPotTextView(Context context) {
        super(context);
    }

    public RedPotTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RedPotTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public RedPotTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setRedPotVisible(boolean b) {
        bRedPotVisible = b;
    }

    public boolean isRedPotVisible() {
        return bRedPotVisible;
    }

    public void setRedPotPos(int dpXToCenter, int dpYToCenter) {
        xToCenter = ScreenUtils.dip2px(getContext(), dpXToCenter);
        yToCenter = ScreenUtils.dip2px(getContext(), dpYToCenter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bRedPotVisible) {
            int width = getWidth();
            int height = getHeight();
            int paddingRight = getPaddingRight();
            Paint paint = new Paint();
            paint.setColor(getContext().getResources().getColor(R.color.point_pink));
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
//            canvas.drawCircle(width / 2 + ScreenUtils.dip2px(getRefContext(),20), 10 , ScreenUtils.dip2px(getRefContext(),3), paint);
            canvas.drawCircle(width / 2 + xToCenter, height / 2 - yToCenter, ScreenUtils.dip2px(getContext(), 3), paint);
        }
    }

}
