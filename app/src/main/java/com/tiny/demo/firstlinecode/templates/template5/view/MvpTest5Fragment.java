package com.tiny.demo.firstlinecode.templates.template5.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.templates.template5.contract.MvpContract;
import com.tiny.demo.firstlinecode.templates.template5.presenter.MvpPresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by tiny on 2017/7/17.
 */

public class MvpTest5Fragment extends Fragment implements MvpContract.IView {
    MvpContract.IPresenter mPresenter;
    private Bundle mArgs;//上一个页面传递过来的bundle参数
    private Map<String, String> params;

    public static MvpTest5Fragment newInstance(Bundle bundle) {
        MvpTest5Fragment f = new MvpTest5Fragment();
        if (bundle != null) {
            f.setArguments(bundle);
        }
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mvp_test5, null);
        mArgs = getArguments();
        initView(root);
        initData();
        requestSeverData();
        return root;
    }

    private void initView(View root) {
        TextView txt = (TextView) root.findViewById(R.id.txt_mvp_test);
        if (mArgs != null) {
            txt.setText(mArgs.getString("txt"));
        } else {
            txt.setText(MvpTest5Fragment.class.getSimpleName());
        }
    }

    private void initData() {
        mPresenter = new MvpPresenter(this);
        params = new HashMap<>();
    }

    private void requestSeverData() {
        mPresenter.getDataFromNet(params);
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void sendResultToView(ResponseBody responseBody, Map<String, String> params) {

    }
}
