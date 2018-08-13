package com.tiny.demo.firstlinecode.javareference.reflect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.javareference.reflect.type.GenericArrayTypeTest;
import com.tiny.demo.firstlinecode.javareference.reflect.type.ParameterizedTypeTest;
import com.tiny.demo.firstlinecode.javareference.reflect.type.TypeVariableTest;
import com.tiny.demo.firstlinecode.javareference.reflect.type.WildcardTypeTest;

import java.lang.reflect.ParameterizedType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: Java反射相关知识
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/8/13 5:22 PM
 */
public class JavaReflectActivity extends AppCompatActivity {

    public static void actionStart(Context context, Bundle extra) {
        Intent starter = new Intent(context, JavaReflectActivity.class);
        starter.putExtra("", extra);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_reflect);
    }

}
