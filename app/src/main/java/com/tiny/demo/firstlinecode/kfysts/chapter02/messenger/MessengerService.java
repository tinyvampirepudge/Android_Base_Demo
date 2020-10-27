package com.tiny.demo.firstlinecode.kfysts.chapter02.messenger;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.test.view.TestActivity;

import java.util.ArrayList;

/**
 * Desc:    Messenger进程间通信
 *
 * @author tiny
 * @date $date$ $time$
 */

public class MessengerService extends Service {
    public static final String TAG = MessengerService.class.getSimpleName();

    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_UNREGISTER_CLIENT = 2;

    public static final int MSG_FROM_CLIENT = 0x100;
    public static final int MSG_FROM_SERVER = 0x101;

    /**
     * For showing and hiding our notification.
     */
    NotificationManager mNM;
    /**
     * Keeps track of all current registered clients.
     */
    ArrayList<Messenger> mClients = new ArrayList<Messenger>();
    /**
     * Holds last value set by a client.
     */
    int mValue = 0;

    /**
     * Handler of incoming messages from clients.
     */
    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;
                case MessengerService.MSG_FROM_CLIENT:
                    LogUtils.INSTANCE.e(TAG, "receive msg from Client:" + msg.getData().get("msg"));
                    for (int j = mClients.size() - 1; j >= 0; j--) {
                        try {
                            Messenger client = mClients.get(j);
                            Message replyMessage = Message.obtain(null, MessengerService.MSG_FROM_SERVER);
                            Bundle bundle = new Bundle();
                            int c = msg.getData().getInt("count");
                            LogUtils.INSTANCE.e(TAG, "");
                            String replyStr = "嗯，你的消息我已经收到，稍后会回复你。";
                            if (c > 0) {
                                replyStr += "\n这是你的第" + c + "条消息，慢点发，我忙不过来的.";
                            }
                            bundle.putString("reply", replyStr);
                            replyMessage.setData(bundle);
                            client.send(replyMessage);
                        } catch (RemoteException e) {
                            // The client is dead.  Remove it from the list;
                            // we are going through the list from back to front
                            // so this is safe to do inside the loop.
                            e.printStackTrace();
                            mClients.remove(j);
                        }
                    }

                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.INSTANCE.e(TAG, "onCreate");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Display a notification about us starting.
        showNotification();
    }

    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.remote_service_started);

        // The PendingIntent to launch our activity if the user selects this notification
        Intent intent = new Intent(this, TestActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        String channelId = getString(R.string.channel_id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel

            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel(channelId, name, importance);
            mChannel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    NOTIFICATION_SERVICE);
            mNM.createNotificationChannel(mChannel);
        }

        // Set the info for the views that show in the notification panel.
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.add_icon) // the status icon
                .setTicker(text) // the status text
                .setWhen(System.currentTimeMillis()) // the time stamp
                .setContentTitle("Messenger IPC") // the label of the entry
                .setContentText(text) // the contents of the entry
                .setContentIntent(contentIntent) // the intent to send then the entry is clicked
                .setChannelId(channelId)
                .build();

        // send the notification.
        // We use a string id because it is a unique number.  We use it later to cancel.
        mNM.notify(R.string.remote_service_started, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.INSTANCE.e(TAG, "onBind");
        return mMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
        LogUtils.INSTANCE.e(TAG, "onDestroy");
        // Cancel the persistent notification.
        mNM.cancel(R.string.remote_service_started);
        ToastUtils.showSingleToast(getString(R.string.remote_service_stopped));
        super.onDestroy();
    }
}
