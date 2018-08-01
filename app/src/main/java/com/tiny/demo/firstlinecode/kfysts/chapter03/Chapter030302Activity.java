package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    通过演示策略实现弹性滑动
 *
 * @author tiny
 * @date 2018/3/25 下午3:28
 */
public class Chapter030302Activity extends AppCompatActivity {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter030302Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter030302);
        ButterKnife.bind(this);
    }

    public static final int MESSAGE_SCROLL_TO = 1;
    public static final int FRAME_COUNT = 30;
    public static final int DELAYED_TIME = 33;
    public static final int SCROLL_VALUE = 100;//滑动距离

    private int mCount = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if (mCount < FRAME_COUNT) {
                        float fraction = mCount * 1.0f / FRAME_COUNT;
                        int scrollX = (int) (fraction * SCROLL_VALUE);
                        tv.scrollTo(scrollX, 0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mHandler.sendEmptyMessage(MESSAGE_SCROLL_TO);
    }
}
