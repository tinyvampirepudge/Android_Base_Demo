package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;
/**
 * @Description: 设置了action、默认的category、data(自定义URI)
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 14:17
 * @Version TODO
 */
public class ImplicitIntentTestDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_test_d);
    }
}
