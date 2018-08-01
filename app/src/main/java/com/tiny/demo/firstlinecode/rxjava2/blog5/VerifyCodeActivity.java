package com.tiny.demo.firstlinecode.rxjava2.blog5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

/**
 * Desc:    获取验证码倒计时
 *
 * @author tiny
 * @date 2018/6/18 下午5:43
 */
public class VerifyCodeActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, VerifyCodeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
    }
}
