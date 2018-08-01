package com.tiny.demo.firstlinecode.thinkinjava.annotation;

import android.os.Bundle;
import android.widget.Button;

import com.squareup.otto.Bus;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.thinkinjava.annotation.blog.CustomUtils;
import com.tiny.demo.firstlinecode.thinkinjava.annotation.blog.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnnotationActivity extends BaseActivity {

    @BindView(R.id.btn_anno_blog)
    Button btnAnnoBlog;
    @BindView(R.id.btn_anno_otto)
    Button btnAnnoBook1;
    private Bus bus;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_annotation;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bus = new Bus();
        bus.register(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @OnClick(R.id.btn_anno_blog)
    public void onBtnAnnoBlogClicked() {
        CustomUtils.getInfo(Person.class);
    }

    @OnClick(R.id.btn_anno_otto)
    public void onBtnAnnoBook1Clicked() {

    }
}
