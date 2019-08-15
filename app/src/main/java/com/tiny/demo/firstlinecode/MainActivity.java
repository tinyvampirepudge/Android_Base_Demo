package com.tiny.demo.firstlinecode;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.google.gson.Gson;
import com.tiny.demo.firstlinecode.activity.ActivityReferedActivity;
import com.tiny.demo.firstlinecode.activity.activity_stack_manager.ActivityCollector;
import com.tiny.demo.firstlinecode.adaptive.AdaptiveActivity;
import com.tiny.demo.firstlinecode.audio.AudioActivity;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.bitmap.BitmapActivity;
import com.tiny.demo.firstlinecode.broadcastreceiver.BroadcastReceiverActivity;
import com.tiny.demo.firstlinecode.brvah.BaseRecyclerViewAdapterHelperActivity;
import com.tiny.demo.firstlinecode.camera.CameraAlbumActivity;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.MapUtils;
import com.tiny.demo.firstlinecode.coolweather.CoolWeatherActivity;
import com.tiny.demo.firstlinecode.designpattern.DesignPatternActivity;
import com.tiny.demo.firstlinecode.drawable.DrawableEntryActivity;
import com.tiny.demo.firstlinecode.filter.FilterTestActivity;
import com.tiny.demo.firstlinecode.fragment.FragmentActivity;
import com.tiny.demo.firstlinecode.handler.HandlerEntryActivity;
import com.tiny.demo.firstlinecode.javareference.JavaReferenceEntryActivity;
import com.tiny.demo.firstlinecode.kfysts.AndroidKfystsActivity;
import com.tiny.demo.firstlinecode.kotlin.KotlinEntryActivity;
import com.tiny.demo.firstlinecode.lambda.LambdaActivity;
import com.tiny.demo.firstlinecode.launcherbadge.LaunchBadgeActivity;
import com.tiny.demo.firstlinecode.leakcanary.LeakCanaryActivity;
import com.tiny.demo.firstlinecode.materialdesign.MaterialDesignActivity;
import com.tiny.demo.firstlinecode.network.HttpUrlConnectionActivity;
import com.tiny.demo.firstlinecode.network.JsonParseActivity;
import com.tiny.demo.firstlinecode.network.OkHttpActivity;
import com.tiny.demo.firstlinecode.network.XmlParseActivity;
import com.tiny.demo.firstlinecode.notification.NotificationActivity;
import com.tiny.demo.firstlinecode.oom.OOMActivity;
import com.tiny.demo.firstlinecode.parser.ActivityParser;
import com.tiny.demo.firstlinecode.permission.PermissionActivity;
import com.tiny.demo.firstlinecode.provider.ContentProviderActivity;
import com.tiny.demo.firstlinecode.proxy.ProxyActivity;
import com.tiny.demo.firstlinecode.refresh.view.SmartRefreshHomeActivity;
import com.tiny.demo.firstlinecode.rxjava2.Rxjava2EntryActivity;
import com.tiny.demo.firstlinecode.screenadaptive.ScreenAdaptiveEntryActivity;
import com.tiny.demo.firstlinecode.service.ServiceEntryActivity;
import com.tiny.demo.firstlinecode.smarttoast.SmartToastActivity;
import com.tiny.demo.firstlinecode.sourcecode.AndroidSourceCodeDesignPatternActivity;
import com.tiny.demo.firstlinecode.staticmanager.StaticManagerActivity;
import com.tiny.demo.firstlinecode.stetho.StethoActivity;
import com.tiny.demo.firstlinecode.storage.StorageActivity;
import com.tiny.demo.firstlinecode.svg.SvgToTextViewActivity;
import com.tiny.demo.firstlinecode.test.view.TestActivity;
import com.tiny.demo.firstlinecode.thinkinjava.ThingInJavaActivity;
import com.tiny.demo.firstlinecode.ui.view.CommonUIComponentsActivity;
import com.tiny.demo.firstlinecode.uicomponents.UIComponentsActivity;
import com.tiny.demo.firstlinecode.unittest.AndroidTestActivity;
import com.tiny.demo.firstlinecode.video.VideoActivity;
import com.tiny.demo.firstlinecode.view.ViewActivity;
import com.tiny.demo.firstlinecode.view.imageview.ImageViewEntryActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //是否需要退出App
    private static Boolean isExit = false;

    private void testMethod() {
        mapToJson();
        getCurrentMillionSeconds();
    }

    private void getCurrentMillionSeconds() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2019-12-12");
            LogUtils.e("date.getTime()  --> " + date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void mapToJson() {
        //Map转换为json串
        Map<String, String> params = new HashMap<>();
        params.put("123", "321");
        params.put("234", "432");
        params.put("345", "543");
        params.put("456", "654");
        params.put("567", "765");
//        params.clear();
        Gson gson = new Gson();
        String s = gson.toJson(params);
        Log.e("json str --> ", s);

        Map<String, String> params1 = new HashMap<>();
        params.put("123", "321");

        Map<String, String> params2 = new HashMap<>();
        params.put("123", "321");
        params.put("233", "432");
        params.put("234", "432");

        LogUtils.e("map1.size() --> " + params1.size());
        LogUtils.e("map2.size() --> " + params2.size());

        LogUtils.e("equal --> " + MapUtils.compareMapsEqual(params1, params2));
        LogUtils.e("equal --> " + params1.equals(params2));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void buildContentView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        testMethod();

        //cool weather
        findViewById(R.id.btn_cool_weather).setOnClickListener(
                v -> CoolWeatherActivity.actionStart(mContext));

        //静态管理类的bug
        findViewById(R.id.btn_static_manager).setOnClickListener(v -> {
            startActivity(new Intent(mContext, StaticManagerActivity.class));
            //43896696730
            //43896696730
        });

        //intent
        //显示intent
        findViewById(R.id.btn_activity).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityReferedActivity.class)));

        //控件
        findViewById(R.id.btn_common_view).setOnClickListener(v -> CommonUIComponentsActivity.actionStart(MainActivity.this));

        //UI组件
        findViewById(R.id.btn_ui_components).setOnClickListener(v -> activitySwitch(UIComponentsActivity.class));

        //布局适配
        findViewById(R.id.btn_adaptive).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AdaptiveActivity.class)));

        //fragment
        findViewById(R.id.btn_fragment).setOnClickListener(v -> startActivity(new Intent(mContext, FragmentActivity.class)));

        //Broadcast Receiver
        findViewById(R.id.btn_broadcast_receiver).setOnClickListener(v -> startActivity(new Intent(mContext, BroadcastReceiverActivity.class)));

        //handler
        findViewById(R.id.btn_handler).setOnClickListener(v -> startActivity(new Intent(mContext, HandlerEntryActivity.class)));

        //storage
        findViewById(R.id.btn_storage).setOnClickListener(v -> StorageActivity.actionStart(mContext));

        //stetho
        findViewById(R.id.btn_stetho).setOnClickListener(v -> {
            activitySwitch(StethoActivity.class);
        });

        //content provider
        findViewById(R.id.btn_content_provider).setOnClickListener(v -> ContentProviderActivity.actionStart(mContext));

        //permission
        findViewById(R.id.btn_permission).setOnClickListener(v -> PermissionActivity.actionStart(mContext));

        //notification
        findViewById(R.id.btn_notification).setOnClickListener(v -> NotificationActivity.actionStart(mContext));

        //调用相机和相册
        findViewById(R.id.btn_camera).setOnClickListener(v -> CameraAlbumActivity.actionStart(mContext));

        //播放音频
        findViewById(R.id.btn_audio).setOnClickListener(v -> AudioActivity.actionStart(mContext));

        //播放视频
        findViewById(R.id.btn_video).setOnClickListener(v -> VideoActivity.actionStart(mContext));

        //HttpUrlConnection
        findViewById(R.id.btn_http_url_conn).setOnClickListener(v -> HttpUrlConnectionActivity.actionStart(mContext));

        //ok http
        findViewById(R.id.btn_ok_http).setOnClickListener(v -> OkHttpActivity.actionStart(mContext));

        //xml parse
        findViewById(R.id.btn_xml_parse).setOnClickListener(v -> XmlParseActivity.actionStart(mContext));

        //json parse
        findViewById(R.id.btn_json_parse).setOnClickListener(v -> JsonParseActivity.actionStart(mContext));

        //service
        findViewById(R.id.btn_service).setOnClickListener(v -> ServiceEntryActivity.actionStart(mContext));

        //material design
        findViewById(R.id.btn_material_design).setOnClickListener((view) -> {
            MaterialDesignActivity.actionStart(mContext);
        });

        //svg设置给textview
        findViewById(R.id.btn_svg_to_textview).setOnClickListener((view) -> {
            SvgToTextViewActivity.actionStart(mContext);
        });

        //Android解析相关
        findViewById(R.id.btn_parser).setOnClickListener(v -> ActivityParser.actionStart(mContext));

        //Android oom 相关
        findViewById(R.id.btn_oom).setOnClickListener(v -> OOMActivity.actionStart(mContext));

        //view
        findViewById(R.id.btn_view).setOnClickListener(v -> startActivity(new Intent(mContext, ViewActivity.class)));

        findViewById(R.id.btn_android_source_code_design_pattern).setOnClickListener(v -> startActivity(new Intent(mContext, AndroidSourceCodeDesignPatternActivity.class)));

        //Android测试相关
        findViewById(R.id.btn_android_test).setOnClickListener(v -> startActivity(new Intent(mContext, AndroidTestActivity.class)));

        //Android测试相关
        findViewById(R.id.btn_android_test).setOnClickListener(v -> startActivity(new Intent(mContext, AndroidTestActivity.class)));

        //设计模式
        findViewById(R.id.btn_design_pattern).setOnClickListener(v -> startActivity(new Intent(mContext, DesignPatternActivity.class)));

        //java相关
        findViewById(R.id.btn_java_reference).setOnClickListener(v -> activitySwitch(JavaReferenceEntryActivity.class));

        //内存泄漏测试
        findViewById(R.id.btn_leak_canary_test).setOnClickListener(v -> activitySwitch(LeakCanaryActivity.class));

        //BaseRecyclerViewAdapterHelper测试
        findViewById(R.id.btn_brvah).setOnClickListener(v -> activitySwitch(BaseRecyclerViewAdapterHelperActivity.class));

        //SmartRefreshLayout测试
        findViewById(R.id.btn_smart_refresh).setOnClickListener(v -> activitySwitch(SmartRefreshHomeActivity.class));

        //SmartToast
        findViewById(R.id.btn_smart_toast).setOnClickListener(v -> activitySwitch(SmartToastActivity.class));

        //Think In Java
        findViewById(R.id.btn_think_in_java).setOnClickListener(v -> activitySwitch(ThingInJavaActivity.class));

        //ImageView圆角
        findViewById(R.id.btn_image_view_round_corner).setOnClickListener(v -> activitySwitch(ImageViewEntryActivity.class));

        //Android开发艺术探索
        findViewById(R.id.btn_android_kfysts).setOnClickListener(v -> AndroidKfystsActivity.actionStart(mContext));

        //rxjava2
        findViewById(R.id.btn_rxjava2).setOnClickListener(v -> Rxjava2EntryActivity.actionStart(mContext));

        //Bitmap相关
        findViewById(R.id.btn_bitmap).setOnClickListener(v -> BitmapActivity.actionStart(mContext));

        //lambda相关
        findViewById(R.id.btn_lambda).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LambdaActivity.class)));

        //proxy
        findViewById(R.id.btn_proxy).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProxyActivity.class)));

        //kotlin
        findViewById(R.id.btn_kotlin).setOnClickListener(v -> KotlinEntryActivity.actionStart(mContext));

        // 桌面角标适配
        findViewById(R.id.btn_launch_badge).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LaunchBadgeActivity.class)));

        //Android中Filter的使用
        findViewById(R.id.btn_filter).setOnClickListener(v -> FilterTestActivity.actionStart(mContext));

        //Android中的屏幕适配
        findViewById(R.id.btn_screen_adaptive).setOnClickListener(v -> ScreenAdaptiveEntryActivity.actionStart(mContext));

        //Drawable加载规则测试
        findViewById(R.id.btn_drawable_test).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DrawableEntryActivity.class)));

        //test的数据都放在这里面
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            SmartToast.show("进入test页面");
            TestActivity.actionStart(mContext);
        });

        //就是想提交下代码
    }

    @Override
    protected void initViewData() {
        LogUtils.buddha();
    }

    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, R.string.string_exit_app, Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {


            ActivityCollector.finishAll();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
