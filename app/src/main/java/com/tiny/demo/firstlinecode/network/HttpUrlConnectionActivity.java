package com.tiny.demo.firstlinecode.network;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

public class HttpUrlConnectionActivity extends BaseActivity {

    @BindView(R.id.btn_request_get)
    Button btnRequest;
    @BindView(R.id.response_text)
    TextView responseText;
    @BindView(R.id.btn_request_post)
    Button btnRequestPost;
    @BindView(R.id.activity_http_url_connection)
    LinearLayout activityHttpUrlConnection;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HttpUrlConnectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_http_url_connection;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    //get request
    private void sendGetRequestWithHttpUrlConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://api.quchaogu.com/lhbapp/index/index");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showGetResponse(response.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showGetResponse(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                responseText.setText("");
                responseText.append("get response\n");
                responseText.append(s);
            }
        });
    }

    @OnClick({R.id.btn_request_get, R.id.btn_request_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request_get:
                responseText.setText("");
                sendGetRequestWithHttpUrlConnection();
                break;
            case R.id.btn_request_post:
                responseText.setText("");
                sendPostRequestWithHttpUrlConnection();
                break;
        }
    }

    //post request
    private void sendPostRequestWithHttpUrlConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://api.quchaogu.com/lhbapp/index/index");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("username=admin&password=123456");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showPostResponse(response.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showPostResponse(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                responseText.setText("");
                responseText.append("post response\n");
                responseText.append(s);
            }
        });
    }
}
