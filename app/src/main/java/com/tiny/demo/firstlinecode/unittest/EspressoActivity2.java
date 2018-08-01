package com.tiny.demo.firstlinecode.unittest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:
 * Created by tiny on 2017/10/11.
 * Version:
 */

public class EspressoActivity2 extends BaseActivity {
    @BindView(R.id.content_edit_text)
    EditText contentEditText;
    @BindView(R.id.write_to_button)
    Button writeToButton;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_espresso2;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.write_to_button)
    public void onViewClicked() {
        String content = contentEditText.getText().toString();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sp.edit();
        String key = getResources().getString(R.string.edit_text_content_sp_key);
        editor.putString(key, content);
        editor.apply();
    }
}
