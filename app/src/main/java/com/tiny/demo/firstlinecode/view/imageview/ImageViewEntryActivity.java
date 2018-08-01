package com.tiny.demo.firstlinecode.view.imageview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    ImageView相关的入口
 * Created by tiny on 2018/1/26 下午3:21
 * Version:
 */
public class ImageViewEntryActivity extends AppCompatActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_entry);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_test1, R.id.btn_test2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test1:
                startActivity(new Intent(ImageViewEntryActivity.this,ImageViewRoundActivity.class));
                break;
            case R.id.btn_test2:
                startActivity(new Intent(ImageViewEntryActivity.this,ImageViewCropActivity.class));
                break;
        }
    }
}
