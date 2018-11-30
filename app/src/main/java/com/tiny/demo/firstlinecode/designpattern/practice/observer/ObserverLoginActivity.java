package com.tiny.demo.firstlinecode.designpattern.practice.observer;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import butterknife.BindView;

/**
 * @Description: 登录页面，观察者模式实战。
 * @Author wangjianzhou@qding.me
 * @Date 2018/11/30 2:14 PM
 * @Version
 */
public class ObserverLoginActivity extends BaseActivity {
    @BindView(R.id.comp_phone_et)
    EditText compPhoneEt;
    @BindView(R.id.comp_pwd_et)
    EditText compPwdEt;
    @BindView(R.id.login_btn)
    Button finishBtn;

    private InputStatusObservable observablePhoneNum;
    private InputStatusObservable observablePwd;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ObserverLoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_complete_info;
    }

    @Override
    protected void buildContentView() {
        /**
         * 登录按钮是否可点击，依赖于是否输入了手机号码和密码
         */
        InputStatusObserverImpl.OnObservablesReadyListener bindReadyListener = new InputStatusObserverImpl.OnObservablesReadyListener() {
            @Override
            public void ready(boolean isReady) {
                // isReady如果为true，表示所有被观察者的状态均为true。
                finishBtn.setEnabled(isReady);
                finishBtn.setOnClickListener(!isReady ? null : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login();
                    }
                });
            }
        };
        // 定义观察者
        InputStatusObserverImpl observer = new InputStatusObserverImpl(bindReadyListener);
        //定义两个被观察者, 并将它们与观察者绑定。
        observablePhoneNum = new InputStatusObservable(observer);
        observer.add(observablePhoneNum);
        observablePwd = new InputStatusObservable(observer);
        observer.add(observablePwd);

        setEditTextListener();
    }

    /**
     * 给手机号码、密码输入框添加监听器。当输入内容变化时，就更新被观察者的状态。
     */
    private void setEditTextListener() {
        // 给手机输入框添加监听
        compPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                observablePhoneNum.setReady(s.length() > 0);
            }
        });
        // 给密码输入框添加监听
        compPwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                observablePwd.setReady(s.length() > 0);
            }
        });
    }


    private void login() {
        ToastUtils.showSingleToast("登录");
    }

    @Override
    protected void initViewData() {

    }

}
