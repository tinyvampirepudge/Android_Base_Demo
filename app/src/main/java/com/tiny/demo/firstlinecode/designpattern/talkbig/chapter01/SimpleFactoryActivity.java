package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter01;

import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    简单工厂模式
 * Created by tiny on 2017/10/24.
 * Version:
 */

public class SimpleFactoryActivity extends BaseActivity {
    @BindView(R.id.btn_operate_add)
    Button btnOperateAdd;
    @BindView(R.id.btn_operate_sub)
    Button btnOperateSub;
    @BindView(R.id.btn_operate_mul)
    Button btnOperateMul;
    @BindView(R.id.btn_operate_div)
    Button btnOperateDiv;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_simple_factory;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_operate_add)
    public void onBtnOperateAddClicked() {
        Operation operation = SimpleOperationFactory.createOperate("+");
        operation.setNumberA(100);
        operation.setNumberB(100);
        LogUtils.e(operation.getNumberA() + " + " + operation.getNumberB() + " = " + operation.getResult());
    }

    @OnClick(R.id.btn_operate_sub)
    public void onBtnOperateSubClicked() {
        Operation operation = SimpleOperationFactory.createOperate("-");
        operation.setNumberA(100);
        operation.setNumberB(1000);
        LogUtils.e(operation.getNumberA() + " + " + operation.getNumberB() + " = " + operation.getResult());
    }

    @OnClick(R.id.btn_operate_mul)
    public void onBtnOperateMulClicked() {
        Operation operation = SimpleOperationFactory.createOperate("*");
        operation.setNumberA(100);
        operation.setNumberB(100);
        LogUtils.e(operation.getNumberA() + " + " + operation.getNumberB() + " = " + operation.getResult());
    }

    @OnClick(R.id.btn_operate_div)
    public void onBtnOperateDivClicked() {
        Operation operation = SimpleOperationFactory.createOperate("/");
        operation.setNumberA(10);
        operation.setNumberB(100);
        LogUtils.e(operation.getNumberA() + " + " + operation.getNumberB() + " = " + operation.getResult());
    }
}
