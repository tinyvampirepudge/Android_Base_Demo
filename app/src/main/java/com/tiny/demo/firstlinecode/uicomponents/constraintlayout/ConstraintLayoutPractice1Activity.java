package com.tiny.demo.firstlinecode.uicomponents.constraintlayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;

/**
 * @Description: ConstraintLayout实现跟随布局
 * @Author wangjianzhou
 * @Date 2019-11-20 11:20
 * @Version
 */
public class ConstraintLayoutPractice1Activity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ConstraintLayoutPractice1Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_practice1);
    }
}
