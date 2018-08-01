package com.tiny.demo.firstlinecode.unittest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tiny.demo.firstlinecode.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Desc: Intent测试，
 * 需要注意的是Intent测试时的包名和className。
 * Created by tiny on 2017/10/12.
 * Version:
 */

@RunWith(AndroidJUnit4.class)
public class EspressoActivity3InstrumentationTest {
    private String TEST_PAGE_NAME = "com.tiny.demo.firstlinecode";

    @Rule
    public IntentsTestRule<EspressoActivity3> mIntentsTestRule
            = new IntentsTestRule<>(EspressoActivity3.class);

    @Test
    public void testStartActivity() {
        //点击不获取结果按钮
        onView(withId(R.id.not_for_result_button)).perform(click());
        //测试是否对应的Intent被发送
        intended(allOf(
                hasComponent(hasShortClassName(".unittest.OtherActivity")),
                toPackage(TEST_PAGE_NAME)
        ));
    }

    @Test
    public void testStartActivityForResult() {
        //构造结果Intent
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", "OK");
        Instrumentation.ActivityResult activityResult
                = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent);

        //如果有相应的Intent发送，并返回虚构的结果
        intending(allOf(
                hasComponent(hasShortClassName(".unittest.OtherActivity")),
                toPackage(TEST_PAGE_NAME)
        )).respondWith(activityResult);

        //点击获取结果按钮
        onView(withId(R.id.for_result_button)).perform(click());

        //查看是否显示结果
        onView(withId(R.id.result_text_view))
                .check(matches(withText("OK")));
    }
}
