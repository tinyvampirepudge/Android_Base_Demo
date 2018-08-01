package com.tiny.demo.firstlinecode.unittest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tiny.demo.firstlinecode.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Desc: ViewAction的使用
 * Created by tiny on 2017/10/11.
 * Version:
 */

@RunWith(AndroidJUnit4.class)
public class EspressoActivity2InstrumentationTest {
    @Rule
    public ActivityTestRule<EspressoActivity2> mActivityTestRule = new ActivityTestRule<EspressoActivity2>(EspressoActivity2.class);

    @Test
    public void textEspresso2Activity() {
        Context context = InstrumentationRegistry.getTargetContext();

        //从SharedPreferences取出值
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String key = context.getResources().getString(R.string.edit_text_content_sp_key);
        String editTextContent = sp.getString(key, "");

        //内容应该为空
        assertEquals(editTextContent, "");

        //看到一个输入内容的EditText，提示信息为请输入要写入的内容。
        //然后在EditText写入内容
        onView(allOf(withId(R.id.content_edit_text), withHint(R.string.content_edit_text_hint)))
                .check(matches(isDisplayed()))
                .perform(replaceText("测试内容"));

        //看到写入按钮并点击
        onView(allOf(withId(R.id.write_to_button), withText(R.string.write_to)))
                .check(matches(isDisplayed()))
                .perform(click());

        //检查SharedPreferences内容是否写入
        editTextContent = sp.getString("edit_text_content", "");
        assertEquals(editTextContent, "测试内容");

        //清空SharedPreferences
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, "");
        editor.apply();
    }
}
