package com.tiny.demo.firstlinecode.uicomponents.textview;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpannableStringBuilderActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_builder);
        ButterKnife.bind(this);

        String desc = "请每位管家/客户进入 我的 页面，点击 设置服务业主 进行选择";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(desc);
        StyleSpan styleSpanBold = new StyleSpan(Typeface.BOLD);//粗体
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 3, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 10, 14, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 19, 27, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(spannableStringBuilder);
    }
}
