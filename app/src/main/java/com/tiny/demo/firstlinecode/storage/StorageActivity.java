package com.tiny.demo.firstlinecode.storage;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StorageActivity extends BaseActivity {
    @BindView(R.id.btn_storage_file)
    Button btnStorageFile;
    @BindView(R.id.btn_storage_shared_preferences)
    Button btnStorageSharedPreferences;
    @BindView(R.id.btn_storage_sqlite)
    Button btnStorageSqlite;
    @BindView(R.id.btn_storage_litepal)
    Button btnStorageLitepal;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, StorageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_storage;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_storage_file, R.id.btn_storage_shared_preferences, R.id.btn_storage_sqlite, R.id.btn_storage_litepal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_storage_file:
                FilePersistentActivity.actionStart(mContext);
                break;
            case R.id.btn_storage_shared_preferences:
                SharedPreferencesActivity.actionStart(mContext);
                break;
            case R.id.btn_storage_sqlite:
                startActivity(new Intent(mContext, SQLiteActivity.class));
                break;
            case R.id.btn_storage_litepal:
                startActivity(new Intent(mContext, LitePalActivity.class));
                break;
        }
    }

    @OnClick(R.id.btn_greendao)
    public void onBtnThirdClicked() {
        startActivity(new Intent(this, GreedDaoTestActivity.class));
    }
}
