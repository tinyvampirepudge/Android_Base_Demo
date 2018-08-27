package com.tiny.demo.firstlinecode.kotlin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.kotlin.primer.project06.KotlinConstructorActivity;
import com.tiny.demo.firstlinecode.kotlin.primer.project14.CoroutinesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: kotlin入口
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/8/8 9:42 PM
 */
public class KotlinEntryActivity extends AppCompatActivity {
    @BindView(R.id.btn_test_01)
    Button btnTest01;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, KotlinEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_01)
    public void onViewClicked() {
        startActivity(new Intent(this, KotlinConstructorActivity.class));
    }


    @OnClick(R.id.btn_test_02)
    public void onViewCoroutinesClicked() {
        startActivity(new Intent(this, CoroutinesActivity.class));
    }
}
