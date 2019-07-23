package com.tiny.demo.firstlinecode.view.dispatchevent.scrollview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: HorizontalScrollview 关于事件分发的一些问题。
 * @Author wangjianzhou
 * @Date 2019-07-22 21:58
 * @Version TODO
 */
public class HorizontalScrollviewDispatchTouchEventActivity extends AppCompatActivity {
    public static final String TAG = HorizontalScrollviewDispatchTouchEventActivity.class.getSimpleName().substring(0, 23);

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.sv2)
    ScrollView sv2;
    @BindView(R.id.tv21)
    TextView tv21;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.hsv3)
    HorizontalScrollView hsv3;
    @BindView(R.id.tv31)
    TextView tv31;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.rl11)
    RelativeLayout rl11;
    @BindView(R.id.hsv11)
    HorizontalScrollView hsv11;
    @BindView(R.id.tv120)
    TextView tv120;
    @BindView(R.id.hsv12)
    HorizontalScrollView hsv12;
    @BindView(R.id.tv121)
    TextView tv121;
    @BindView(R.id.rl12)
    RelativeLayout rl12;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, HorizontalScrollviewDispatchTouchEventActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrollview_dispatch_touch_event);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv1)
    public void onTv1Clicked() {
        LogUtils.e(TAG, "tv1 clicked");
    }

    @OnClick(R.id.rl1)
    public void onRl1Clicked() {
        LogUtils.e(TAG, "rl1 clicked");
    }

    @OnClick(R.id.tv2)
    public void onTv2Clicked() {
        LogUtils.e(TAG, "tv2 clicked");
    }

    @OnClick(R.id.ll2)
    public void onLl2Clicked() {
        LogUtils.e(TAG, "ll2 clicked");
    }

    @OnClick(R.id.sv2)
    public void onSv2Clicked() {
        LogUtils.e(TAG, "sv2 clicked");
    }

    @OnClick(R.id.tv21)
    public void onTv21Clicked() {
        LogUtils.e(TAG, "tv21 clicked");
    }

    @OnClick(R.id.rl2)
    public void onRl2Clicked() {
        LogUtils.e(TAG, "rl2 clicked");
    }

    @OnClick(R.id.tv3)
    public void onTv3Clicked() {
        LogUtils.e(TAG, "tv3 clicked");
    }

    @OnClick(R.id.ll3)
    public void onLl3Clicked() {
        LogUtils.e(TAG, "ll3 clicked");
    }

    @OnClick(R.id.hsv3)
    public void onHsv3Clicked() {
        LogUtils.e(TAG, "hsv3 clicked");
    }

    @OnClick(R.id.tv31)
    public void onTv31Clicked() {
        LogUtils.e(TAG, "tv31 clicked");
    }

    @OnClick(R.id.rl3)
    public void onRl3Clicked() {
        LogUtils.e(TAG, "rl3 clicked");
    }

    @OnClick(R.id.tv11)
    public void onTv11Clicked() {
        LogUtils.e(TAG, "tv11 clicked");
    }

    @OnClick(R.id.rl11)
    public void onRl11Clicked() {
        LogUtils.e(TAG, "rl11 clicked");
    }

    @OnClick(R.id.hsv11)
    public void onHsv11Clicked() {
        LogUtils.e(TAG, "hsv11 clicked");
    }

    @OnClick(R.id.tv120)
    public void onTv120Clicked() {
        LogUtils.e(TAG, "tv120 clicked");
    }

    @OnClick(R.id.hsv12)
    public void onHsv12Clicked() {
        LogUtils.e(TAG, "hsv12 clicked");
    }

    @OnClick(R.id.tv121)
    public void onTv121Clicked() {
        LogUtils.e(TAG, "tv121 clicked");
    }

    @OnClick(R.id.rl12)
    public void onRl12Clicked() {
        LogUtils.e(TAG, "rl12 clicked");
    }
}
