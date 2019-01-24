package com.tiny.demo.firstlinecode.test.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import com.example.tiny.tinymodule.ModuleTest;
import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.templates.temp1.view.MvpTestActivity;
import com.tiny.demo.firstlinecode.templates.template5.view.MvpTest5Actiity;
import com.tiny.demo.firstlinecode.test.view.floating.FloatingActivity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.HttpUrl;

public class TestActivity extends BaseActivity {

    @BindView(R.id.btn_templates)
    Button btnTemplates;
    @BindView(R.id.btn_templates5)
    Button btnTemplates5;
    @BindView(R.id.btn_test_image_loader)
    Button btnTestImageLoader;
    @BindView(R.id.btn_test_date_picker_adapter)
    Button btnTestDatePickerAdapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TestActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_test_chLayout)
    Button btnTestChLayout;
    @BindView(R.id.btn_test_listview)
    Button btnTestListview;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void buildContentView() {

        //android studio templates
        findViewById(R.id.btn_templates).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MvpTestActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_templates5).setOnClickListener(v -> {
            startActivity(new Intent(mContext, MvpTest5Actiity.class));
        });

        findViewById(R.id.btn_test_auto_size_text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoSizeTextViewActivity.actionStart(mContext);
            }
        });

        findViewById(R.id.btn_test_validte_ui_on_child_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateUIOnChildThreadActivity.actionStart(mContext);
            }
        });
    }

    @Override
    protected void initViewData() {
        ModuleTest.test();
    }

    @OnClick({R.id.btn_test_chLayout, R.id.btn_test_listview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test_chLayout:
                break;
            case R.id.btn_test_listview:
                ListViewTestActivity.actionStart(mContext);
                break;
        }
    }

    @OnClick(R.id.btn_test_utils)
    public void onViewTestUtilsClicked() {
        UtilsTestActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test_image_loader)
    public void onViewClicked() {
        activitySwitch(ImageLoaderTestActivity.class);
    }

    @OnClick(R.id.btn_test_date_picker_adapter)
    public void onViewDatePickerAdapterClicked() {
        activitySwitch(DatePickerActivity.class);
    }

    @OnClick(R.id.btn_test_click_proxy)
    public void onViewClickProxyClicked() {
        activitySwitch(ClickProxyTestActivity.class);
    }

    @OnClick(R.id.btn_test_full_screen)
    public void onViewFullScreenClicked() {
        activitySwitch(FullscreenActivity.class);
    }

    @OnClick(R.id.btn_test_shadow)
    public void onViewShadowClicked() {
        ShadowTestActivity.actionStart(mContext);
    }

    @OnClick(R.id.btn_test_floating)
    public void onViewFloatingClicked() {
        FloatingActivity.actionStart(mContext);
    }

    @OnClick(R.id.btn_test_toast_on_sub_thread)
    public void onViewToastOnSubThreadClicked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("子线程弹Toast");
                Looper.prepare();
                Toast.makeText(mContext, "子线程弹Toast", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    public enum EnumType {
        type1("type1"), type2("type2"), type3("type3");
        private String type;

        EnumType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    @OnClick(R.id.btn_test_enum_with_switchcase)
    public void onViewEnumWithSwitchCaseClicked() {
        EnumType enumType = EnumType.type1;
        testEnum(enumType);
    }

    private void testEnum(EnumType type) {
        switch (type) {
            case type1:
                Log.e("type1:", type.getType());
                break;
            case type2:
                Log.e("type2:", type.getType());
                break;
            case type3:
                Log.e("type3:", type.getType());
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_test_start_activity_in_sub_thread)
    public void onViewStartSctivityInSubThreadClicked() {
        EnumType enumType = EnumType.type1;
        testEnum(enumType);
    }

    @OnClick(R.id.btn_test_httpUrl)
    public void onViewHttpUrlClicked() {
        URI uri = null;
        try {
            uri = new URI("http://api.quchaogu.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (null != uri) {
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme(uri.getScheme())
                    .host(uri.getHost())
                    .build();
//                if(pcs.get(httpUrl)){
//
//                }
            LogUtils.e(TAG, "httpUrl.host():" + httpUrl.host());
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int width = btnTemplates.getMeasuredWidth();
            int height = btnTemplates.getMeasuredHeight();
        }

        btnTemplates.post(new Runnable() {
            @Override
            public void run() {
                int width = btnTemplates.getMeasuredWidth();
                int height = btnTemplates.getMeasuredHeight();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewTreeObserver observer = btnTemplates.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btnTemplates.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = btnTemplates.getMeasuredWidth();
                int height = btnTemplates.getMeasuredHeight();
            }
        });
    }

    @OnClick(R.id.btn_test_async_2_sync)
    public void onViewAsyhc2SyncClicked() {
        ThreadUtils.logCurrThreadName("111");
        String s = getSyncBusiness("http://www.baidu.com");
        LogUtils.e("异步转同步 FutureTask Callable：" + s);
    }

    public static String getSyncBusiness(final String url) {

        try {
            FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    ThreadUtils.logCurrThreadName("Callable call()");
//                    URL u = new URL(url);
//                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//                    connection.setDoInput(true);
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//                    InputStream in = connection.getInputStream();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf8"));
//                    final StringBuilder sb = new StringBuilder();
//                    String line = null;
//                    while ((line = br.readLine()) != null) {
//                        sb.append(line);
//                    }
//                    return sb.toString();

                    Thread.sleep(3000);
                    return "王蛋蛋的father";
                }
            });

            new Thread(task).start();
            ThreadUtils.logCurrThreadName("task.get()");
            return task.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("网络访问错误");
        }

    }

    @OnClick(R.id.btn_test_sync_2_async)
    public void onViewSyhc2AsyncClicked() {
        ThreadUtils.logCurrThreadName("222");
        String s = getSyncBusiness2("http://www.baidu.com");
        LogUtils.e("异步转同步 Callable：" + s);
    }

    public static String getSyncBusiness2(final String url) {

        try {
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    ThreadUtils.logCurrThreadName("Callable call() 222");
//                    URL u = new URL(url);
//                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//                    connection.setDoInput(true);
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//                    InputStream in = connection.getInputStream();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf8"));
//                    final StringBuilder sb = new StringBuilder();
//                    String line = null;
//                    while ((line = br.readLine()) != null) {
//                        sb.append(line);
//                    }
//                    return sb.toString();

                    Thread.sleep(3000);
                    return "王蛋蛋的father";
                }
            };
            ExecutorService exec = Executors.newFixedThreadPool(1);
            Future<String> task = exec.submit(callable);
            ThreadUtils.logCurrThreadName("task.get() 222");
            return task.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("网络访问错误");
        }
    }

    @OnClick(R.id.btn_test_go_to_settings)
    public void onViewGoToSettingsClicked() {
        switchSettingActivity(mContext);
    }

    /**
     * 跳转应用信息页面，跳转应用设置页面
     * @param context
     */
    public void switchSettingActivity(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
