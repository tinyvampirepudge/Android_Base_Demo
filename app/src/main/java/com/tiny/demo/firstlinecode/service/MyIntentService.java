package com.tiny.demo.firstlinecode.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.tiny.demo.firstlinecode.service.action.FOO";
    private static final String ACTION_BAZ = "com.tiny.demo.firstlinecode.service.action.BAZ";

    // Rename parameters
    private static final String EXTRA_PARAM1 = "com.tiny.demo.firstlinecode.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.tiny.demo.firstlinecode.service.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // Handle action Foo
//        throw new UnsupportedOperationException("Not yet implemented");
        LogUtils.INSTANCE.e("MyIntentService handleActionFoo");
        LogUtils.INSTANCE.e("Current Thread id is --> " + Thread.currentThread().getId());
        LogUtils.INSTANCE.e("Current Thread name is --> " + Thread.currentThread().getName());
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // Handle action Baz
//        throw new UnsupportedOperationException("Not yet implemented");
        LogUtils.INSTANCE.e("MyIntentService handleActionBaz");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.INSTANCE.e("MyIntentService onDestroy");
    }
}
