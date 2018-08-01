package com.tiny.demo.firstlinecode.stetho;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.stetho.httphelper.HomepagePresenter;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * 测试stetho的网络抓包。
 */
public class StethoActivity extends BaseActivity implements StethoContract.IView {

    private Map<String, String> params;
    private HomepagePresenter mPresenter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, StethoActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_stetho_send)
    Button btnSend;
    @BindView(R.id.txt_stetho_content)
    TextView txtContent;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_stetho;
    }

    @Override
    protected void buildContentView() {
        //mvp
        params = new HashMap<>();
        mPresenter = new HomepagePresenter(this);
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_stetho_send)
    public void onClick() {
        mPresenter.getDataFromNet(params);
    }

    @Override
    public void sendResultToView(ResponseBody responseBody, Map<String, String> params) {
        //获取到responseBody,自己进行解析。
        try {
            Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
            String responseString = responseBody.string();
            txtContent.setText(responseString);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("json解析错误。");
        }
    }

    @Override
    public void requestFailure(Map<String, String> params) {
        Toast.makeText(mContext, "homepage 数据请求失败。", Toast.LENGTH_SHORT).show();
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
}
