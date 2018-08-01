package com.tiny.demo.firstlinecode.kfysts.chapter02.file;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter02.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Desc:    进程间通过文件共享数据
 *
 * @author tiny
 * @date 2018/3/5 上午10:57
 */
public class Chapter02FileSharingActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, Chapter02FileSharingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter02_file_sharing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recoverFromFile();
    }

    /**
     * 从文件中恢复数据
     */
    private void recoverFromFile() {
        User user = null;
        File dir = getCacheDir();
        File cahcedFile = new File(dir, "user");
        if (cahcedFile.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(cahcedFile));
                user = (User) ois.readObject();
                LogUtils.e("recover user:" + user);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
