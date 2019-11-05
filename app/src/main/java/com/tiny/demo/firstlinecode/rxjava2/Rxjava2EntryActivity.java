package com.tiny.demo.firstlinecode.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.rxjava2.blog1.Rxjava2Blog1EntryActivity;
import com.tiny.demo.firstlinecode.rxjava2.blog2.Rxjava2Blog2EntryActivity;
import com.tiny.demo.firstlinecode.rxjava2.blog3.Rxjava2Blog3EntryActivity;
import com.tiny.demo.firstlinecode.rxjava2.blog4.Rxjava2Blog4EntryActivity;
import com.tiny.demo.firstlinecode.rxjava2.blog5.Rxjava2Blog5EntryActivity;
import com.tiny.demo.firstlinecode.rxjava2.practice.RxEasyHttpCacheFirstRemoteStrategyTestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    rxjava2练习。
 * 来自：https://github.com/rengwuxian/RxJavaSamples
 *
 * @author tiny
 * @date 2018/6/6 上午11:35
 */
public class Rxjava2EntryActivity extends AppCompatActivity {
    @BindView(R.id.btn_first)
    Button btnFirst;
    @BindView(R.id.btn_second)
    Button btnSecond;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2EntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_first)
    public void onBtnFirstClicked() {
        Rxjava2Blog1EntryActivity.actionStart(this);
    }

    @OnClick(R.id.btn_second)
    public void onBtnSecondClicked() {
        Rxjava2Blog2EntryActivity.actionStart(this);
    }

    @OnClick(R.id.btn_third)
    public void onBtnThirdClicked() {
        Rxjava2Blog3EntryActivity.actionStart(this);
    }

    @OnClick(R.id.btn_fourth)
    public void onBtnFourthClicked() {
        Rxjava2Blog4EntryActivity.actionStart(this);
    }

    @OnClick(R.id.btn_fifth)
    public void onBtnFifthClicked() {
        Rxjava2Blog5EntryActivity.actionStart(this);
    }

    @OnClick(R.id.btn_sixth)
    public void onBtnSixthClicked() {
        RxEasyHttpCacheFirstRemoteStrategyTestActivity.actionStart(this);
    }
}
