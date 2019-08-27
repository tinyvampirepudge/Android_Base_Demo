package com.tiny.demo.firstlinecode.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 通知栏消息，适配8.0
 * https://juejin.im/post/5b3c376df265da0f6436a1fc
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-27 16:54
 * @Version TODO
 */
public class NotificationActivity extends BaseActivity {
    @BindView(R.id.btn_send_notification)
    Button btnSendNotification;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "upgrade";
            String channelName = "升级";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
            channelId = "compose";
            channelName = "私信";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_send_notification, R.id.btn_to_app_notification_management})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_notification:
                sendUpgradeMsg();
                sendComposeMsg();
                break;
            case R.id.btn_clean_all_notification:
                cleanAllNotification();
                break;
            case R.id.btn_to_app_notification_management:
                toAppNotificationManagement();
                break;
        }
    }

    /**
     * 清除所有通知
     */
    private void cleanAllNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

    /**
     * 跳转应用的通知管理页面
     */
    private void toAppNotificationManagement() {
        // https://github.com/googlesamples/android-NotificationChannels/#readme
        // https://www.jianshu.com/p/1e27efb1dcac
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("NOTIFICATION_CHANNEL_ID", "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
            Intent intent111 = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent111.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
            intent111.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            startActivity(intent111);
        }
    }

    //创建通知渠道
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    public void sendUpgradeMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int id = 10086;
        Intent intent = new Intent(mContext, ReceiveNoticeActivity.class);
        intent.putExtra("para", "这个是来自通知的消息！");
        intent.putExtra("ids", id);//用于在接受通知的页面，根据id取消通知。
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(mContext, "upgrade")
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
                        BitmapFactory.decodeResource(getResources(), R.drawable.ic_big_image)))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        manager.notify(id, notification);
    }

    public void sendComposeMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int id = 10087;
        Intent intent = new Intent(mContext, ReceiveNoticeActivity.class);
        intent.putExtra("para", "这个是来自通知的消息！");
        intent.putExtra("ids", id);//用于在接受通知的页面，根据id取消通知。
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, "compose")
                .setContentTitle("私信")
                .setContentText("有人私信向你提出问题")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();
        manager.notify(id, notification);
    }
}
