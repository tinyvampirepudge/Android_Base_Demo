package com.tiny.demo.firstlinecode.staticmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

/**
 * 针对静态的工具类做的demo。
 */
public class StaticManagerActivity extends AppCompatActivity {

    private TextView txt;
    private UrlManager urlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_manager);

        txt = (TextView) findViewById(R.id.txt_login_url);

        findViewById(R.id.btn_online).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlManager.setIsOnline(true);
                txt.setText("online url --> "+UrlManager.login_url);
            }
        });
        findViewById(R.id.btn_offline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlManager.setIsOnline(false);
                txt.setText("offline url --> "+UrlManager.login_url);
            }
        });
        urlManager = new UrlManager();
        findViewById(R.id.btn_online_field).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlManager.setIsOnline(true);
                txt.setText("online url field --> "+ urlManager.feedUrl);
            }
        });
        findViewById(R.id.btn_offline_field).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlManager.setIsOnline(false);
                txt.setText("offline url field --> "+urlManager.feedUrl);
            }
        });

    }
}
