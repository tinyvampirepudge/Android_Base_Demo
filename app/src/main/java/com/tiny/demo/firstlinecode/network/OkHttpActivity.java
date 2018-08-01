package com.tiny.demo.firstlinecode.network;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends BaseActivity {

    @BindView(R.id.btn_okhttp_get)
    Button btnOkhttpGet;
    @BindView(R.id.btn_okhttp_post)
    Button btnOkhttpPost;
    @BindView(R.id.txt_okhttp_response)
    TextView txtOkhttpResponse;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OkHttpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_ok_http;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_okhttp_get, R.id.btn_okhttp_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_okhttp_get:
                sendGetRequest();
                break;
            case R.id.btn_okhttp_post:
                sendPostRequest();
                break;
        }
    }

    private void sendGetRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://api.quchaogu.com/lhbapp/index/index")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        showGetResponse(responseData);
                    }
                });
            }
        }).start();
    }

    private void showGetResponse(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtOkhttpResponse.setText("");
                txtOkhttpResponse.append("get response\n");
                txtOkhttpResponse.append(responseData);
            }
        });
    }

    private void showPostResponse(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtOkhttpResponse.setText("");
                txtOkhttpResponse.append("post response\n");
                txtOkhttpResponse.append(responseData);
            }
        });
    }

    private void sendPostRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.quchaogu.com/lhbapp/index/index")
                .addHeader("username", "admin")
                .addHeader("password", "123456")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                showPostResponse(responseData);
            }
        });

    }
}
