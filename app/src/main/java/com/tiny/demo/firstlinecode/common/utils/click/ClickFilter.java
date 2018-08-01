package com.tiny.demo.firstlinecode.common.utils.click;

import android.view.View;

import java.lang.reflect.Field;

/**
 * Desc:    http://www.jianshu.com/p/d98e22c127ed
 * <p>
 * Created by tiny on 2017/12/22.
 * Version:
 */

public class ClickFilter {
    /*
    private StateButton mStateButton;//自定义控件

    private void initView() {
        ClickFilter.setFilter(mStateButton);
    }
    这种动态替换的方式同样适合普通场景,在设置点击事件后,
    都可以通过设置该过滤器来处理重复点击(包括butterknife等注解绑定的点击事件)

    */
    public static void setFilter(View view) {
        try {
            Field field = View.class.getDeclaredField("mListenerInfo");
            field.setAccessible(true);
            Class listInfoType = field.getType();
            Object listinfo = field.get(view);
            Field onclickField = listInfoType.getField("mOnClickListener");
            View.OnClickListener origin = (View.OnClickListener) onclickField.get(listinfo);
            onclickField.set(listinfo, new ClickProxy(origin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
