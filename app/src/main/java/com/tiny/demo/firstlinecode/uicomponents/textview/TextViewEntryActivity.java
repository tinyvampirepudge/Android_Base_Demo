package com.tiny.demo.firstlinecode.uicomponents.textview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextViewEntryActivity extends AppCompatActivity {
    @BindView(R.id.btn_SpannableStringBuilder)
    Button btnSpannableStringBuilder;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, TextViewEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_SpannableStringBuilder)
    public void onViewClicked() {
        SpannableStringBuilderActivity.actionStart(this);
    }
}
