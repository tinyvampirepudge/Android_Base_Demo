package com.tiny.demo.firstlinecode.activity.activity_stack_manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 87959 on 2017/3/7.
 * activity的任务栈
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
