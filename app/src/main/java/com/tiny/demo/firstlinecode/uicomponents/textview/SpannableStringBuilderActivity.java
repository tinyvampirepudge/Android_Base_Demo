package com.tiny.demo.firstlinecode.uicomponents.textview;

import android.content.Context;
import android.content.Intent;
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

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, SpannableStringBuilderActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_builder);
        ButterKnife.bind(this);

        String desc = "天行健，君子以自强不息；地势坤，君子以厚德载物。";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(desc);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 4, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 7, 9, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 16, 18, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 19, 23, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(spannableStringBuilder);
    }
}
