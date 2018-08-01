package com.tiny.demo.firstlinecode.network;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.templates.bean.TestObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonParseActivity extends BaseActivity {
    private static final String url = "http://10.0.2.2/get_json.json";
    @BindView(R.id.btn_json_parse_json_object)
    Button btnJsonParseJsonObject;
    @BindView(R.id.btn_json_parse_gson)
    Button btnJsonParseGson;
    @BindView(R.id.btn_json_parse_json_array)
    Button btnJsonParseJsonArray;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, JsonParseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_json_parse;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    private void doSomeThing() {
        String jsonString = "[{\"handlerName\":\"showExtPlotSelection\",\"data\":{\"type\":1,\"data\":[{\"type\":\"VOL_MA\",\"text\":\"成交量\",\"act\":1,\"chart_type\":0,\"name\":\"\",\"tab_type\":0,\"unit\":\"\"},{\"type\":\"MACD\",\"text\":\"MACD\",\"act\":0,\"chart_type\":0,\"name\":\"\",\"tab_type\":0,\"unit\":\"\"},{\"type\":\"KDJ\",\"text\":\"KDJ\",\"act\":0,\"chart_type\":0,\"name\":\"\",\"tab_type\":0,\"unit\":\"\"},{\"type\":\"MAIN_FUND\",\"text\":\"主力资金\",\"act\":0,\"chart_type\":0,\"name\":\"\",\"tab_type\":0,\"unit\":\"\"}]}}]";
        //gson解析
        List<TestObject> citys = new Gson().fromJson(jsonString, new TypeToken<ArrayList<TestObject>>() {
        }.getType());
        LogUtils.e("citys.get(0).getData().getData().get(0).getType() --> " + citys.get(0).getData().getData().get(0).getType());

        //JSONArray解析
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String handlerName = jsonObject1.getString("handlerName");
                LogUtils.d("jsonObject1 handlerName --> " + handlerName);

                JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                String type = jsonObject2.getString("type");
                LogUtils.d("jsonObject2 type --> " + type);
                JSONArray jsonArray3 = jsonObject2.getJSONArray("data");
                for (int j = 0; j < jsonArray3.length(); j++) {
                    JSONObject jsonObject4 = jsonArray3.getJSONObject(j);
                    int act = jsonObject4.getInt("act");
                    LogUtils.d("jsonObject4 act --> " + act);
                    String unit = jsonObject4.getString("unit");
                    LogUtils.d("jsonObject4 unit --> " + unit);
                    int tabType = jsonObject4.getInt("tab_type");
                    LogUtils.d("jsonObject4 tab_type --> " + tabType);
                    String text = jsonObject4.getString("text");
                    LogUtils.d("jsonObject4 text --> " + text);
                    String type4 = jsonObject4.getString("type");
                    LogUtils.d("jsonObject4 type --> " + type4);
                    int chart_type = jsonObject4.getInt("chart_type");
                    LogUtils.d("jsonObject4 chart_type --> " + chart_type);
                    String name = jsonObject4.getString("name");
                    LogUtils.d("jsonObject4 name --> " + name);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_json_parse_json_object, R.id.btn_json_parse_gson})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_json_parse_json_object:
                sendRequestParseJsonObject();
                break;
            case R.id.btn_json_parse_gson:
                sendRequestParseGson();
                break;
        }
    }

    private void sendRequestParseJsonObject() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJsonWithJsonObject(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJsonWithJsonObject(String responseData) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                LogUtils.d("JsonObject id --> " + id);
                LogUtils.d("JsonObject name --> " + name);
                LogUtils.d("JsonObject version --> " + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestParseGson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        parseJsonWithGon(responseData);
                    }
                });
            }
        }).start();
    }

    private void parseJsonWithGon(String responseData) {
        Gson gson = new Gson();
        List<AppBean> list = gson.fromJson(responseData, new TypeToken<List<AppBean>>() {
        }.getType());
        LogUtils.e("gson list --> " + list.toString());
    }

    @OnClick(R.id.btn_json_parse_json_array)
    public void onViewClicked() {
        doSomeThing();
    }
}
