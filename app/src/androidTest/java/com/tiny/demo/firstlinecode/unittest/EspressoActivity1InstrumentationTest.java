package com.tiny.demo.firstlinecode.unittest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

/**
 * Desc:    简介和弹出Toast提示。
 * Created by tiny on 2017/10/11.
 * Version:
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoActivity1InstrumentationTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(EspressoActivity1.class);

    @Test
    public void testAuto1() throws Exception {
        //检查一个文本内容为Hello World!的TextView是否在AutoTestActivity1中显示
        //view必须可见；多个TextView的文本如果都是这个，也会报错；
        onView(allOf(withText("我是蛋蛋的爸爸"), instanceOf(TextView.class)))
                .check(matches(isDisplayed()));

        //界面上有一个文本为 R.string.show_toast 的按钮并点击按钮
        onView(allOf(withText(R.string.show_toast), instanceOf(Button.class)))
                .check(matches(isDisplayed()))
                .perform(click());
        //会弹出一个文本为 R.string.show_toast_en 的Toast.
        Thread.sleep(1000);
        //根据toast弹出的文本找到toast
        onView(withText(R.string.show_toast_en))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}
