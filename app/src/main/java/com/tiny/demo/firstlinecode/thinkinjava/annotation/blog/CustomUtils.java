package com.tiny.demo.firstlinecode.thinkinjava.annotation.blog;

import com.tinytongtong.tinyutils.LogUtils;

import java.lang.reflect.Field;

/**
 * Desc:    为前面定义好的Name，Profile，Gender的自定义注解写一个简单的处理器
 * Created by tiny on 2018/1/4.
 * Time: 11:50
 * Version:
 */

public class CustomUtils {
    public static final String tag = "Custom Annotation";

    public static void getInfo(Class<?> clazz) {
        String name = "";
        String gender = "";
        String profile = "";
        Field fields[] = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Name.class)) {
                Name arg0 = field.getAnnotation(Name.class);
                name = name + arg0.value();
                LogUtils.INSTANCE.e(tag, "name = " + name);
            }
            if (field.isAnnotationPresent(Gender.class)) {
                Gender arg0 = field.getAnnotation(Gender.class);
                gender = gender + arg0.gender().toString();
                LogUtils.INSTANCE.e(tag, "gender = " + gender);
            }
            if (field.isAnnotationPresent(Profile.class)) {
                Profile arg0 = field.getAnnotation(Profile.class);
                profile = "[id=" + arg0.id() + ",height="
                        + arg0.height() + ",nativePlace="
                        + arg0.nativePlace() + "]";
                LogUtils.INSTANCE.e(tag, "profile = " + profile);
            }
        }
    }
}
