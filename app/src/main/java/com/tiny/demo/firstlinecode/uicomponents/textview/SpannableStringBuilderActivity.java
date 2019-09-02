package com.tiny.demo.firstlinecode.uicomponents.textview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: SpannableStringBuilder
 * @Author wangjianzhou@qding.me
 * @Date 2019-09-02 13:44
 * @Version
 */
public class SpannableStringBuilderActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, SpannableStringBuilderActivity.class);
        context.startActivity(starter);
    }

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

        // SpannableStringBuilder + ImageSpan 给文本添加图片
        String desc3 = "天行健，君子以自强不息；地势坤，君子以厚德载物。";
        SpannableStringBuilder ssb3 = new SpannableStringBuilder(desc3);
        //图片
        ImageSpan imageSpan3 = new ImageSpan(this, R.drawable.icon_cat_48);
        //将最后字符用图片替换
        ssb3.setSpan(imageSpan3, desc3.length() - 2, desc3.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tv3.setText(ssb3);

        // SpannableStringBuilder + ImageSpan 给文本添加图片
        String desc4 = "天行健，君子以自强不息；地势坤，君子以厚德载";
        SpannableStringBuilder ssb4 = new SpannableStringBuilder(desc4);
        //图片
        ImageSpan imageSpan4 = new ImageSpan(this, R.drawable.icon_cat_48);
        //将最后字符用图片替换
        ssb4.setSpan(imageSpan4, desc4.length() - 2, desc4.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tv4.setText(ssb4);

        addTagToTextView(tv5, "天行健，君子以自强不息。", "王蛋蛋的爸比");

        addTagToTextView(tv6, "天行健，君子以自强不息；地势坤，君子以厚德载物。", "王蛋蛋的爸比");
    }

    private void addTagToTextView(TextView target, String title, String tag) {
        if (TextUtils.isEmpty(title)) {
            title = "";
        }

        String content = title + tag;


        /**
         * 创建TextView对象，设置drawable背景，设置字体样式，设置间距，设置文本等
         * 这里我们为了给TextView设置margin，给其添加了一个父容器LinearLayout。不过他俩都只是new出来的，不会添加进任何布局
         */
        LinearLayout layout = new LinearLayout(this);
        TextView textView = new TextView(this);
        textView.setText(tag);
        textView.setBackground(getResources().getDrawable(R.drawable.room_member_role_bg));
        textView.setTextSize(12);
        textView.setTextColor(Color.parseColor("#FDA413"));
        textView.setIncludeFontPadding(false);
        textView.setPadding(ScreenUtils.dip2px(this, 6), 0,
                ScreenUtils.dip2px(this, 6), 0);
        textView.setHeight(ScreenUtils.dip2px(this, 17));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置左间距
        layoutParams.leftMargin = ScreenUtils.dip2px(this, 6);
        // 设置下间距，简单解决ImageSpan和文本竖直方向对齐的问题
        layoutParams.bottomMargin = ScreenUtils.dip2px(this, 3);
        layout.addView(textView, layoutParams);

        /**
         * 第二步，测量，绘制layout，生成对应的bitmap对象
         */
        layout.setDrawingCacheEnabled(true);
        layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        // 给上方设置的margin留出空间
        layout.layout(0, 0, textView.getMeasuredWidth() + ScreenUtils.dip2px(this, (6 + 3)), textView.getMeasuredHeight());
        // 获取bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache());
        //千万别忘最后一步
        layout.destroyDrawingCache();

        /**
         * 第三步，通过bitmap生成我们需要的ImageSpan对象
         */
        ImageSpan imageSpan = new ImageSpan(this, bitmap);


        /**
         * 第四步将ImageSpan对象设置到SpannableStringBuilder的对应位置
         */
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        //将尾部tag字符用ImageSpan替换
        ssb.setSpan(imageSpan, title.length(), content.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        target.setText(ssb);
    }
}
