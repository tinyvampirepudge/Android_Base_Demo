package com.tiny.demo.firstlinecode.unittest;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;

/**
 * Desc: Android UI Test
 * Created by tiny on 2017/10/9.
 * Version:
 */

public class UITestActivity extends BaseActivity {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_ui_test;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    public void sayHello(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        textView.setText("Hello, " + editText.getText().toString() + "!");
    }
}
