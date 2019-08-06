package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.intentfilter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: IntentFilter匹配规则实战
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-05 21:31
 * @Version
 */
public class ImplicitIntentEntryActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ImplicitIntentEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_intent_test1)
    public void onBtnIntentTest1Clicked() {

    }

    @OnClick(R.id.btn_intent_test2)
    public void onBtnIntentTest2Clicked() {

    }

    @OnClick(R.id.btn_intent_test3)
    public void onBtnIntentTest3Clicked() {
        //Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "猫了个咪");
        sendIntent.setType("text/plain");

        //Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    @OnClick(R.id.btn_intent_test4)
    public void onBtnIntentTest4Clicked() {
        //选择器
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "猫了个咪啊");
        sendIntent.setType("text/plain");
        //Always use string resources for UI text.
        //This says something like "Share this photo with"
        String title = getResources().getString(R.string.chooser_title);
        //Create intent to show the chooser dialog
        Intent chooser = Intent.createChooser(sendIntent, title);

        //Verify the original intent will resolve to at least one activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    @OnClick(R.id.btn_intent_test5)
    public void onBtnIntentTest5Clicked() {

    }

    @OnClick(R.id.btn_intent_test6)
    public void onBtnIntentTest6Clicked() {

    }
}
