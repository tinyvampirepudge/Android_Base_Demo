package com.tiny.demo.firstlinecode.storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SharedPreferencesActivity extends BaseActivity {
    @BindView(R.id.btn_sp_context)
    Button btnSpContext;
    @BindView(R.id.btn_sp_activity)
    Button btnSpActivity;
    @BindView(R.id.btn_sp_pm)
    Button btnSpPm;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SharedPreferencesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_shared_preferences;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_sp_context, R.id.btn_sp_activity, R.id.btn_sp_pm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sp_context:
                saveSpContext();
                break;
            case R.id.btn_sp_activity:
                saveSpActivity();
                break;
            case R.id.btn_sp_pm:
                saveSpPm();
                break;
        }
    }

    private void saveSpContext() {
        //sp名按照提供的文件名存储
        SharedPreferences sp = mContext.getSharedPreferences("context_sp", Context.MODE_PRIVATE);
        sp.edit()
                .putString("string", "string")
                .putInt("int", 10)
                .apply();

        //最终的实现在ContextWrapper类中。
        /**
         *   @Override
         *   public SharedPreferences getSharedPreferences(String name, int mode) {
         *      return mBase.getSharedPreferences(name, mode);
         *  }
         */
    }

    private void saveSpActivity() {
        //sp名称为全路径中去除前面的包名。
        // Activity里面的getLocalClassName()方法返回。
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        sp.edit()
                .putString("string", "string")
                .putLong("long", 100l)
                .apply();
        LogUtils.e("getLocalClassName() --> " + getLocalClassName());
        //storage.SharedPreferencesActivity
    }

    private void saveSpPm() {
        //sp名称按照context.getPackageName() + "_preferences"规则存储。
        //可以通过getDefaultSharedPreferencesName方法获取对应的sp名称。
        LogUtils.e("getDefaultSharedPreferencesName(context) --> "
                + getPackageName() + "_preferences");
        //com.tiny.demo.firstlinecode_preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        sp.edit()
                .putString("string", "string")
                .putBoolean("boolean", true)
                .apply();
    }
}
