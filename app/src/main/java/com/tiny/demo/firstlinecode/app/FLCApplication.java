package com.tiny.demo.firstlinecode.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Process;
import android.support.v7.app.AppCompatDelegate;

import com.coder.zzq.smartshow.core.SmartShow;
import com.facebook.stetho.Stetho;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.brvah.util.Utils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ProcessUtil;
import com.tiny.demo.firstlinecode.refresh.util.DynamicTimeFormat;
import com.tiny.demo.firstlinecode.stetho.httphelper.OkHttpClientUtils;
import com.tiny.demo.firstlinecode.storage.greendao.GreenDaoHelper;

import org.litepal.LitePal;

/**
 * Created by 87959 on 2017/3/7.
 */

public class FLCApplication extends Application {
    private static Context instance;
    public static Typeface iconfont;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("MyApplication onCreate");

        // litepal
        LitePal.initialize(this);

        //打印当前process的名称
        String processName = ProcessUtil.getProcessNameByCtx(getApplicationContext(), Process.myPid());
        LogUtils.e("processName --> " + processName);

        instance = this;

        setupLeakCanary();

        Utils.init(this);

        //stetho
        Stetho.initializeWithDefaults(this);

        LogUtils.openLog(true);
        OkHttpClientUtils.setShowLog(true);

        //初始化SmartToast
        initSmartToast();

        GreenDaoHelper.setupDataBase(this);

        InitializeService.startActionInitApp(this);
    }

    private void initSmartToast() {
        SmartShow.init(this);

        //使用默认布局的普通Toast
//        SmartToast.plainToast(this);

        //返回PlainToastSetting对象，对布局进行风格设置
//        SmartToast.plainToast(this)
//           /*
//            设置背景颜色，有可选方法，直接以颜色值为参数。Toast的默认背景是一个圆角图片，当你设置了
//            背景颜色时，原有背景失效。SmartToast内部用GradientDrawable实现背景，可以保证大小与你
//            手机系统的Toast一致，但是不同品牌手机的Toast的圆角半径不尽相同，将统一使用2.5dp
//           */
//                .backgroundColorRes(R.color.colorPrimary)
//                //设置文本颜色，有可选方法，直接以颜色值为参数
//                .textColorRes(R.color.colorAccent)
//                //设置文本字体大小，单位为sp
//                .textSizeSp(18)
//                //设置是否加粗文本
//                .textBold(true)
//                //如果以上还不够，可调用此方法
//                .processPlainView(new ProcessViewCallback() {
//                    //outParent为显示文本的TextView的父布局，msgView为显示文本的TextView @Override
//                    public void processPlainView(LinearLayout outParent, TextView msgView) {
//                        //处理代码
//                        // TODO: 2017/12/14
//                        msgView.append(" --> 猫了个咪啊");
//                    }
//                });

        //设置自定义布局
        /**
         * 设置自定义布局，有重载方法，直接以View为参数。在自定义布局中，一定要设置显示
         文本提示的TextView的Id为android:id="@id/custom_toast_msg"。如果不调用下面
         的方法，那么上面的调用与SmartToast.plainToast(this)等效
         */
//        SmartToast.customToast(this)
//                //填充布局
//                .view(R.layout.layout_toast_custom)
//                //对自定义布局进行代码处理
//                .processCustomView(new ProcessViewCallback() {
//                    @Override
//                    public void processCustomView(View view) {
//                        super.processCustomView(view);
//                    }
//                });
    }

    public static Context instance() {
        return instance;
    }

    public static Typeface getIconfont(Context context) {
        if (iconfont != null) {
            return iconfont;
        } else {
            iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        }
        return iconfont;
    }

    protected RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
}
