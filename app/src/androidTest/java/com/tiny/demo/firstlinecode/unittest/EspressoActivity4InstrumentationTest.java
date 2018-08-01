package com.tiny.demo.firstlinecode.unittest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

/**
 * Desc:
 * Created by tiny on 2017/10/12.
 * Version:
 */

@RunWith(AndroidJUnit4.class)
public class EspressoActivity4InstrumentationTest {
    @Rule
    public ActivityTestRule<EspressoActivity4> activityTestRule
            = new ActivityTestRule<>(EspressoActivity4.class);

    @Test
    public void testListView() throws Exception {
        //随机选择点击的位置
        Random random = new Random();
        int pos = random.nextInt(10);
        String text = "item" + pos;
        //查看该Item的文本，点击对应的Item
        onData(instanceOf(String.class))
                .atPosition(pos)
                .check(matches(withText(text)))
                .perform(click());

        //弹出Toast
        Thread.sleep(1000);
        onView(withText(text))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}