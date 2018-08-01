package com.tiny.demo.firstlinecode.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 开机启动的广播的接收器
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    public BootCompletedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Toast.makeText(context,"Boot complete",Toast.LENGTH_SHORT).show();
        //需要注意的是，不要在onReceive()方法中添加过多的逻辑或者进行任何耗时操作，
        //因为在广播接收器中是不允许开启线程的，当onReceive()法国法运行了较长时间而没有结束时，
        //程序就会报错。因此，广播接收器更多的是扮演一种打开程序其他组件的角色。
        //比如创建一条状态栏通知，或者启动一个服务等。
    }
}
