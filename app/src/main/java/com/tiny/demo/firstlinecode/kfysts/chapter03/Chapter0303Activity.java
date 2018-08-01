package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 弹性滑动
 *
 * @author tiny
 * @date 2018/3/25 下午1:49
 */
public class Chapter0303Activity extends AppCompatActivity {
    @BindView(R.id.btn_test_01)
    Button btnTest01;
    @BindView(R.id.btn_test_02)
    Button btnTest02;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter0303Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter0303);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_01)
    public void onView01Clicked() {
        Chapter030301Activity.actionStart(Chapter0303Activity.this);
    }

    @OnClick(R.id.btn_test_02)
    public void onView02Clicked() {
        Chapter030302Activity.actionStart(Chapter0303Activity.this);
    }
}
