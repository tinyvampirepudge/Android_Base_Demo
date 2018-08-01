package com.tiny.demo.firstlinecode.designpattern.chapter13;

import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    客户不需要知道具体的构建过程
 * Created by tiny on 2017/10/23.
 * Version:
 */

public class BuilderActivity extends BaseActivity {
    @BindView(R.id.btn_builder_demo)
    Button btnBuilderDemo;
    @BindView(R.id.btn_builder_test)
    Button btnBuilderTest;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_builder;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_builder_demo)
    public void onBtnBuilderDemoClicked() {
        Director director = new Director();
        Builder b1 = new ConcreteBuilder1();
        Builder b2 = new ConcreteBuilder2();

        //指挥者用ConcreteBuilder1的方法来构建产品
        director.constructor(b1);
        Product p1 = b1.getResult();
        p1.show();

        //指挥者用ConcreteBuilder2的方法来构建产品
        director.constructor(b2);
        Product p2 = b2.getResult();
        p2.show();
    }

    @OnClick(R.id.btn_builder_test)
    public void onBtnBuilderTestClicked() {
    }
}
