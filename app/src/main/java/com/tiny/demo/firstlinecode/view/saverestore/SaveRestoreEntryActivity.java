package com.tiny.demo.firstlinecode.view.saverestore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: View#saveHierarchyState View#restoreHierarchyState
 * {@link View#saveHierarchyState(SparseArray)}
 * {@link View#restoreHierarchyState(SparseArray)}
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-04
 * @Version
 */
public class SaveRestoreEntryActivity extends AppCompatActivity {
    public static final String TAG = SaveRestoreEntryActivity.class.getSimpleName();

    @BindView(R.id.tv_save_restore)
    SaveRestoreTextView tvSaveRestore;
    @BindView(R.id.btn_modify_text)
    Button btnModifyText;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, SaveRestoreEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_save_restore_entry);
        ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @OnClick(R.id.btn_modify_text)
    public void onViewClicked() {
        tvSaveRestore.setText("我是修改过后的文字");
    }
}
