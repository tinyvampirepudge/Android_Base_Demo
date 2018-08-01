package com.tiny.demo.firstlinecode.view.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.view.CustomRoundAngleImageView;
import com.tiny.demo.firstlinecode.common.view.RoundCornerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:    ImageView直角圆角
 * Created by tiny on 2018/1/26 下午3:21
 * Version:
 */
public class ImageViewRoundActivity extends AppCompatActivity {

    @BindView(R.id.iv_avatar1)
    RoundCornerImageView ivAvatar1;
    @BindView(R.id.iv_avatar2)
    CustomRoundAngleImageView ivAvatar2;
    @BindView(R.id.iv_avatar3)
    ImageView ivAvatar3;
    @BindView(R.id.iv_avatar4)
    ImageView ivAvatar4;
    @BindView(R.id.iv_avatar5)
    ImageView ivAvatar5;
    @BindView(R.id.iv_avatar6)
    CustomRoundAngleImageView ivAvatar6;
    @BindView(R.id.iv_avatar7)
    CustomRoundAngleImageView ivAvatar7;
    @BindView(R.id.iv_avatar8)
    ImageView ivAvatar8;
    @BindView(R.id.iv_avatar9)
    ImageView ivAvatar9;
    @BindView(R.id.iv_avatar10)
    ImageView ivAvatar10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_test);
        ButterKnife.bind(this);
        /**
         * 使用Glide加载圆角图片时，添加的配置应该是Glide全局的。会影响别的地方。
         这里我将RoundCorners的值设为1，近似等于0.
         这里建议Glide加载圆角图片时，使用封装过的ImageView，而不是使用RequestOptions。
         */

        String avatarUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516644385815&di=c0552674db9f07a5f889d7c0980e33db&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170529%2F83d3ce719e9d4c0a8f1cd033ecac3692_th.jpg";
        Glide.with(this).load(avatarUrl).into(ivAvatar1);
        Glide.with(this).load(avatarUrl).into(ivAvatar2);
        Glide.with(this).load(avatarUrl).into(ivAvatar3);
        Glide.with(this)
//                .setDefaultRequestOptions(new RequestOptions().optionalTransform(new RoundedCorners(ScreenUtils.dip2px(this, 20))))
                .load(avatarUrl).into(ivAvatar4);
        Glide.with(this).load(avatarUrl).into(ivAvatar5);
        Glide.with(this).load(avatarUrl).into(ivAvatar6);
        Glide.with(this).load(avatarUrl).into(ivAvatar7);
        Glide.with(this).load(avatarUrl).into(ivAvatar8);
        Glide.with(this).load(avatarUrl).into(ivAvatar9);
        Glide.with(this).load(avatarUrl).into(ivAvatar10);
    }
}
