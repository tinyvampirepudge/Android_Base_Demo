package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.implicit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;
/**
 * @Description: 隐式Intent启动当前应用的Activity
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-05 22:14
 * @Version
 */
public class ImplicitIntentTestAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_test_a);
    }
}
