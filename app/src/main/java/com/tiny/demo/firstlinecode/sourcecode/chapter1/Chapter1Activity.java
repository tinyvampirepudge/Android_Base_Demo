package com.tiny.demo.firstlinecode.sourcecode.chapter1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.ImageLoaderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Android源码设计模式解析与实战
 * 第一章，走向灵活软件之路
 */
public class Chapter1Activity extends BaseActivity {
    @BindView(R.id.btn_image_loader1)
    Button btnImageLoader1;
    @BindView(R.id.btn_image_loader2)
    Button btnImageLoader2;
    @BindView(R.id.btn_image_loader3)
    Button btnImageLoader3;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_chapter1;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_image_loader1)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString(ImageLoaderActivity.INTENT_TYPE, ImageLoaderActivity.TYPE1);
        Intent intent = new Intent(mContext, ImageLoaderActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.btn_image_loader2)
    public void onBtnImageLoader2Clicked() {
        Bundle bundle = new Bundle();
        bundle.putString(ImageLoaderActivity.INTENT_TYPE, ImageLoaderActivity.TYPE2);
        Intent intent = new Intent(mContext, ImageLoaderActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.btn_image_loader3)
    public void onBtnImageLoader3Clicked() {
        Bundle bundle = new Bundle();
        bundle.putString(ImageLoaderActivity.INTENT_TYPE, ImageLoaderActivity.TYPE3);
        Intent intent = new Intent(mContext, ImageLoaderActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
