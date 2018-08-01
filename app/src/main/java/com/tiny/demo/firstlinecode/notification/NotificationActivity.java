package com.tiny.demo.firstlinecode.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity {
    @BindView(R.id.btn_send_notification)
    Button btnSendNotification;
    @BindView(R.id.activity_notification)
    RelativeLayout activityNotification;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int setContentLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_send_notification, R.id.activity_notification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_notification:
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                int id = 10086;
                Intent intent = new Intent(mContext, ReceiveNoticeActivity.class);
                intent.putExtra("para", "这个是来自通知的消息！");
                intent.putExtra("ids", id);//用于在接受通知的页面，根据id取消通知。
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
                Notification notification = new NotificationCompat.Builder(mContext)
                        .setContentTitle("tiny的通知")
                        .setContentText("通知的内容")
                        .setSubText("呵呵呵")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
//                        .setAutoCancel(true)//设置自动取消；
//                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/ringtone_001.ogg")))//发送语音
//                        .setVibrate(new long[]{0,1000,1000,1000})//震动的时长
//                        .setLights(Color.GREEN,1000,1000)//设置LED灯的颜色以及模式。
                        .setDefaults(NotificationCompat.DEFAULT_ALL)//设置默认模式，去掉上方三行代码的属性设置
                        //富文本
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("阿萨德花费就爱上对方阿斯顿发哈师大话费卡就是的护肤哈师大几号放假阿诗丹顿花费就爱上对方就爱和史蒂夫"))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                BitmapFactory.decodeResource(getResources(),R.drawable.ic_big_image)))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(id, notification);
                break;
            case R.id.activity_notification:
                break;
        }
    }
}
