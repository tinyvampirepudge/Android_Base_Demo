package com.tiny.demo.firstlinecode.rxjava2.blog2;

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
 * Desc: RxJava 只看这一篇文章就够了 (上)
 * https://mp.weixin.qq.com/s?__biz=MzIwMTAzMTMxMg==&mid=2649492706&idx=1&sn=d7d213a1db9c8ae3a5b0525d45863518&chksm=8eec871db99b0e0bc4d4d1aa2b7ed5d7c32e5299aee0f0818a798c2deb2996f40f8971c7a6a2&scene=38#wechat_redirect
 *
 * @author tiny
 * @date 2018/6/6 下午2:35
 */
public class Rxjava2Blog2EntryActivity extends AppCompatActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.btn_test3)
    Button btnTest3;
    @BindView(R.id.btn_test4)
    Button btnTest4;
    @BindView(R.id.btn_test5)
    Button btnTest5;
    @BindView(R.id.btn_test6)
    Button btnTest6;
    @BindView(R.id.btn_test7)
    Button btnTest7;
    @BindView(R.id.btn_test8)
    Button btnTest8;
    @BindView(R.id.btn_test9)
    Button btnTest9;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Blog2EntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_blog2_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        Rxjava2Test10Activity.actionStart(this);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        Rxjava2Test11Activity.actionStart(this);
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        Rxjava2Test12Activity.actionStart(this);
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        Rxjava2Test13Activity.actionStart(this);
    }

    @OnClick(R.id.btn_test5)
    public void onBtnTest5Clicked() {
        Rxjava2Test14Activity.actionStart(this);
    }

    @OnClick(R.id.btn_test6)
    public void onBtnTest6Clicked() {
        Rxjava2Test15Activity.actionStart(this);
    }

    @OnClick(R.id.btn_test7)
    public void onBtnTest7Clicked() {
    }

    @OnClick(R.id.btn_test8)
    public void onBtnTest8Clicked() {
    }

    @OnClick(R.id.btn_test9)
    public void onBtnTest9Clicked() {
    }
}
