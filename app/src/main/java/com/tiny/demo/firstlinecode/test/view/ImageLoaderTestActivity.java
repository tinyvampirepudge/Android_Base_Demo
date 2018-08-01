package com.tiny.demo.firstlinecode.test.view;

import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    测试图片加载库 universal-image-loader 和 glide
 * Created by tiny on 2017/10/17.
 * Version:
 */

public class ImageLoaderTestActivity extends BaseActivity {
    @BindView(R.id.btn_uil)
    Button btnUil;
    @BindView(R.id.img_uil)
    ImageView imgUil;
    @BindView(R.id.btn_glide)
    Button btnGlide;
    @BindView(R.id.img_glide)
    ImageView imgGlide;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_image_loader_test;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_uil)
    public void onBtnUilClicked() {
    }

    @OnClick(R.id.btn_glide)
    public void onBtnGlideClicked() {
        String testUrl = "http://sta.quchaogu.com/upload/sfzzp/a/8/59a52ac54c6b9.thumb.jpg";
        Glide.with(mContext).load(testUrl).into(imgGlide);
    }
}
