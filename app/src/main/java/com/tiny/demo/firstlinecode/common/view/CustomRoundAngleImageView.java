package com.tiny.demo.firstlinecode.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Desc:    可以自己设置角度的ImageView
 * 参考：https://www.jianshu.com/p/626dbd93207d
 * Created by tiny on 2018/1/24.
 * Time: 20:10
 * Version:
 * <com.xxx.xxx.common.view.CustomRoundAngleImageView
        android:id="@+id/iv_avatar8"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_marginTop="50dp"
        android:scaleType="centerCrop"
        roundiv:left_bottom_radius="10dp"
        roundiv:radius="10dp"
        roundiv:left_top_radius="10dp"
        roundiv:right_bottom_radius="10dp"
        roundiv:right_top_radius="10dp"
        tools:src="@mipmap/ic_launcher"/>
 * 所有角度默认值均为0，即直角。
 * 左上、右上、右下、左下四个角的度数的值分别对应的是
 * 左上：left_top_radius
 * 右上：right_top_radius
 * 右下：right_bottom_radius
 * 左下：left_bottom_radius
 * 如果四个角度的度数一致，推荐使用radius，在不使用上述四个参数的时候，它的值会作为默认值。
 */

public class CustomRoundAngleImageView extends AppCompatImageView {
    float width, height;
    private int defaultRadius = 0;
    private int radius;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;

    public CustomRoundAngleImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        // 读取配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Custom_Round_Image_View);
        radius = array.getDimensionPixelOffset(R.styleable.Custom_Round_Image_View_radius, defaultRadius);
        leftTopRadius = array.getDimensionPixelOffset(R.styleable.Custom_Round_Image_View_left_top_radius, defaultRadius);
        rightTopRadius = array.getDimensionPixelOffset(R.styleable.Custom_Round_Image_View_right_top_radius, defaultRadius);
        rightBottomRadius = array.getDimensionPixelOffset(R.styleable.Custom_Round_Image_View_right_bottom_radius, defaultRadius);
        leftBottomRadius = array.getDimensionPixelOffset(R.styleable.Custom_Round_Image_View_left_bottom_radius, defaultRadius);

        LogUtils.e("radius --> " + radius);

        //如果四个角的值没有设置，那么就使用通用的radius的值。
        if (defaultRadius == leftTopRadius) {
            leftTopRadius = radius;
        }
        if (defaultRadius == rightTopRadius) {
            rightTopRadius = radius;
        }
        if (defaultRadius == rightBottomRadius) {
            rightBottomRadius = radius;
        }
        if (defaultRadius == leftBottomRadius) {
            leftBottomRadius = radius;
        }
        array.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //这里做下判断，只有图片的宽高大于设置的圆角距离的时候才进行裁剪
        int maxLeft = Math.max(leftTopRadius, leftBottomRadius);
        int maxRight = Math.max(rightTopRadius, rightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(leftTopRadius, rightTopRadius);
        int maxBottom = Math.max(leftBottomRadius, rightBottomRadius);
        int minHeight = maxTop + maxBottom;
        if (width >= minWidth && height > minHeight) {
            Path path = new Path();
            //四个圆角
//            path.moveTo(12, 0);
//            path.lineTo(width - 12, 0);
//            path.quadTo(width, 0, width, 12);
//            path.lineTo(width, height - 12);
//            path.quadTo(width, height, width - 12, height);
//            path.lineTo(12, height);
//            path.quadTo(0, height, 0, height - 12);
//            path.lineTo(0, 12);
//            path.quadTo(0, 0, 12, 0);

            //四个直角
//            path.moveTo(0, 0);
//            path.lineTo(width, 0);
//            path.lineTo(width, height);
//            path.lineTo(0, height);
//            path.lineTo(0, 0);

            //四个角：右上，右下，左下，左上
            path.moveTo(leftTopRadius, 0);
            path.lineTo(width - rightTopRadius, 0);
            path.quadTo(width, 0, width, rightTopRadius);

            path.lineTo(width, height - rightBottomRadius);
            path.quadTo(width, height, width - rightBottomRadius, height);

            path.lineTo(leftBottomRadius, height);
            path.quadTo(0, height, 0, height - leftBottomRadius);

            path.lineTo(0, leftTopRadius);
            path.quadTo(0, 0, leftTopRadius, 0);

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

}
