package com.tiny.demo.firstlinecode.storage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FilePersistentActivity extends BaseActivity {

    private EditText editText;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FilePersistentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_file_persistent;
    }

    @Override
    protected void buildContentView() {
        editText = (EditText) findViewById(R.id.edittext);
        String input = load();
        if (!TextUtils.isEmpty(input)){
            editText.setText(input);
            editText.setSelection(input.length());
            Toast.makeText(mContext,"Restoring succeed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initViewData() {

    }

    private void save(String inputText) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = openFileOutput("data_file_persistent", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String str = editText.getText().toString();
        save(str);
    }

    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            in = openFileInput("data_file_persistent");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (reader !=null){
                    reader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
