package com.tiny.demo.firstlinecode.view.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    图片裁剪
 * Created by tiny on 2018/1/26 下午3:45
 * Version:
 */
public class ImageViewCropActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_crop);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv)
    public void onViewClicked() {
    }
}
