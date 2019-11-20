package com.tiny.demo.firstlinecode.test.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: TextView字体加粗效果实战
 *
 * @Author wangjianzhou
 * @Date 2019-11-20 11:16
 * @Version
 */
public class TextViewBoldTestActivity extends AppCompatActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, TextViewBoldTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_bold_test);
        ButterKnife.bind(this);
        tv3.getPaint().setFakeBoldText(true);
    }
}
