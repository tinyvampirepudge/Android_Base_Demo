package com.tiny.demo.firstlinecode.uicomponents.constraintlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        startActivity(new Intent(this, ConstraintLayout1Activity.class));
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        startActivity(new Intent(this, RelativeActivity.class));
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        startActivity(new Intent(this, ConstraintLayout2Activity.class));
    }

    @OnClick(R.id.btn_test4)
    public void onBtnTest4Clicked() {
        startActivity(new Intent(this, ConstraintLayout3Activity.class));
    }
}
