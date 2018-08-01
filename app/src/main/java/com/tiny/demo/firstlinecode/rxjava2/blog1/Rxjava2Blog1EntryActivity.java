package com.tiny.demo.firstlinecode.rxjava2.blog1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test1Activity;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test2Activity;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test3Activity;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test4Activity;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test5Activity;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test6Activity;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test7Activity;
import com.tiny.demo.firstlinecode.rxjava2.blog1.activity.Rxjava2Test8Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:  rxjava2练习
 * https://www.jianshu.com/p/464fa025229e
 *
 * @author tiny
 * @date 2018/6/6 上午11:39
 */
public class Rxjava2Blog1EntryActivity extends AppCompatActivity {
    @BindView(R.id.btn_first)
    Button btnFirst;
    @BindView(R.id.btn_second)
    Button btnSecond;
    @BindView(R.id.btn_fourth)
    Button btnFourth;
    @BindView(R.id.btn_fifth)
    Button btnFifth;
    @BindView(R.id.btn_sixth)
    Button btnSixth;
    @BindView(R.id.btn_seventh)
    Button btnSeventh;
    @BindView(R.id.btn_eighth)
    Button btnEighth;
    @BindView(R.id.btn_ninth)
    Button btnNinth;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Blog1EntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_blog1_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_first)
    public void onBtnFirstClicked() {
        startActivity(new Intent(this, Rxjava2Test1Activity.class));
    }

    @OnClick(R.id.btn_second)
    public void onBtnSecondClicked() {
        startActivity(new Intent(this, Rxjava2Test2Activity.class));
    }

    @OnClick(R.id.btn_fourth)
    public void onBtnFourthClicked() {
        startActivity(new Intent(this, Rxjava2Test3Activity.class));
    }

    @OnClick(R.id.btn_fifth)
    public void onBtnFifthClicked() {
        startActivity(new Intent(this, Rxjava2Test4Activity.class));
    }

    @OnClick(R.id.btn_sixth)
    public void onBtnSixthClicked() {
        startActivity(new Intent(this, Rxjava2Test5Activity.class));
    }

    @OnClick(R.id.btn_seventh)
    public void onBtnSenventhClicked() {
        startActivity(new Intent(this, Rxjava2Test6Activity.class));
    }

    @OnClick(R.id.btn_eighth)
    public void onBtnEighthClicked() {
        startActivity(new Intent(this, Rxjava2Test7Activity.class));
    }

    @OnClick(R.id.btn_ninth)
    public void onBtnNinthClicked() {
        startActivity(new Intent(this, Rxjava2Test8Activity.class));
    }
}
