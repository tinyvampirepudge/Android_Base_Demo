package com.tiny.demo.firstlinecode.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

/**
 * 水果详情页
 */
public class FruitDetailActivity extends BaseActivity {
    public static final String FRUIT_NAME = "FRUIT_NAME";
    public static final String FRUIT_IMG = "FRUIT_IMG";
    private String name;
    private Integer imgId;

    public static void actionStart(Context context, Bundle bundle) {
        Intent intent = new Intent(context, FruitDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_fruit_detail;
    }

    @Override
    protected void buildContentView() {
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra(FRUIT_NAME);
            imgId = intent.getIntExtra(FRUIT_IMG, 0);
        }
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView img = (ImageView) findViewById(R.id.fruit_image_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView txtContent = (TextView) findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置收缩起来时toolBar的标题和展开时CollapsingToolbarLayout左下角的标题。
        collapsingToolbarLayout.setTitle("童童");
        Glide.with(mContext).load(imgId).into(img);
        String fruitContent = generateFruitContent(name);
        txtContent.setText(fruitContent);
    }

    private String generateFruitContent(String name) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 500; j++) {
            sb.append(name).append(" --> ");
        }
        return sb.toString();
    }

    @Override
    protected void initViewData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
