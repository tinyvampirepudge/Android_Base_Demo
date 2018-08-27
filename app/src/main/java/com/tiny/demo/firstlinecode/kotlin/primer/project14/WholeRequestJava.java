package com.tiny.demo.firstlinecode.kotlin.primer.project14;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;
import java.security.PublicKey;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description: Java代码发起一个简单的异步请求
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/24 11:42 AM
 */
public class WholeRequestJava {
    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private final Request mRequest = new Request.Builder().url("https://www.baidu.com").get().build();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public void display(final TextView textView) {
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(string);
                    }
                });
            }
        });
    }
}
