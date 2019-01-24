package com.tiny.demo.firstlinecode.test.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 子线程更新UI
 * https://blog.csdn.net/xyh269/article/details/52728861
 * @Author wangjianzhou@qding.me
 * @Date 2019/1/24 4:36 PM
 * @Version
 */
public class ValidateUIOnChildThreadActivity extends AppCompatActivity {

    private TextView textView;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ValidateUIOnChildThreadActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_ui_on_child_thread);

        textView = findViewById(R.id.tv);

        // 这儿不会报错
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textView.setText("onCreate() 子线程中更新UI：" + Thread.currentThread().toString());
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
