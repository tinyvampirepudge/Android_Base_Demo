package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:   通过动画实现弹性滑动
 *
 * @author tiny
 * @date 2018/3/25 下午3:26
 */
public class Chapter030301Activity extends AppCompatActivity {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter030301Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter030301);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
//        ObjectAnimator.ofFloat(tv, "translationX", 0, 100).setDuration(100).start();
        int startX = 0;
        int deltaX = 100;
        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int destX = (int) (startX + deltaX * fraction);
                LogUtils.e("fraction --> " + fraction);
                LogUtils.e("destX --> " + destX);
                LogUtils.e("tv.getScrollX() --> " + tv.getScrollX());
                tv.scrollTo(destX, 0);
            }
        });
        animator.start();
    }
}
