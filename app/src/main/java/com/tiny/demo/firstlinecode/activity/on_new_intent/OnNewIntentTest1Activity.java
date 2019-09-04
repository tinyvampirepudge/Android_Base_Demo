package com.tiny.demo.firstlinecode.activity.on_new_intent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnNewIntentTest1Activity extends AppCompatActivity {
    @BindView(R.id.btn_test_01)
    Button btnTest01;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, OnNewIntentTest1Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_new_intent_test1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_01)
    public void onViewClicked() {
        OnNewIntentActivity.actionStart(this);
    }
}
