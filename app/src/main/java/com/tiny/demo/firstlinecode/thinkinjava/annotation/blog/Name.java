package com.tiny.demo.firstlinecode.thinkinjava.annotation.blog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc:    姓名注解
 * https://mp.weixin.qq.com/s/NwlIy_knmx4Es7kFnk0gRQ
 * Created by tiny on 2018/1/4.
 * Time: 10:24
 * Version:
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Name {
    String value() default "";
}
