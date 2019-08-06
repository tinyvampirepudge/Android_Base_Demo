package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;

/**
 * @Description: 设置了action、自定义的category和默认的category
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 13:35
 * @Version
 */
public class ImplicitIntentTestBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_test_b);
    }
}
