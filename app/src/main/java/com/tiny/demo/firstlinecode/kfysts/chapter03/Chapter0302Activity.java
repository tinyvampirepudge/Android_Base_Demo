package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.kfysts.chapter03.view.AnimationTestView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: View动画
 *
 * @author tiny
 * @date 2018/3/20 下午11:28
 */
public class Chapter0302Activity extends AppCompatActivity {
    @BindView(R.id.btn_test_01)
    Button btnTest01;
    @BindView(R.id.view1)
    TextView view1;
    @BindView(R.id.btn_test_02)
    Button btnTest02;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.btn_test_03)
    Button btnTest03;
    @BindView(R.id.view3)
    TextView view3;
    @BindView(R.id.btn_test_04)
    Button btnTest04;
    @BindView(R.id.view4)
    AnimationTestView view4;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter0302Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter0302);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_01)
    public void onBtnTest01Clicked() {
        Animation animation = AnimationUtils.loadAnimation(Chapter0302Activity.this, R.anim.anim_test1);
        view1.startAnimation(animation);
    }

    @OnClick(R.id.btn_test_02)
    public void onBtnTest02Clicked() {
        ObjectAnimator.ofFloat(view2, "translationX", 0, 100).setDuration(100).start();
    }

    @OnClick(R.id.btn_test_03)
    public void onBtnTest03Clicked() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view3.getLayoutParams();
        params.width += 100;
//        params.leftMargin += 100;

        view3.requestLayout();
        //或者将params重新设置给View
//        view3.setLayoutParams(params);
    }

    @OnClick(R.id.btn_test_04)
    public void onViewClicked() {

    }
}
