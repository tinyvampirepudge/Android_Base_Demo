package com.tiny.demo.firstlinecode.test.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 自动调整TextViews
 * https://developer.android.google.cn/guide/topics/ui/look-and-feel/autosizing-textview
 * @Author wangjianzhou@qding.me
 * @Date 2018/11/22 6:03 PM
 * @Version
 */
public class AutoSizeTextViewActivity extends BaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AutoSizeTextViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_auto_size_text_view;
    }

    @Override
    protected void buildContentView() {
//        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv1,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        tv1.setText("猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪");
        tv2.setText("猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪大事发生撒打发斯蒂芬阿斯顿发送到");
        tv3.setText("猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪大事发生撒打发斯蒂芬阿斯顿发送到大事发生撒打发斯蒂芬阿斯顿发送到");
        tv4.setText("猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪猫了个咪大事发生撒打发斯蒂芬阿斯顿发送到大事发生撒打发斯蒂芬阿斯顿发送到大事发生撒打发斯蒂芬阿斯顿发送到");
    }

    @Override
    protected void initViewData() {

    }
}
