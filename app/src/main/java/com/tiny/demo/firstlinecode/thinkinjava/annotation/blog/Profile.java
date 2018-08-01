package com.tiny.demo.firstlinecode.thinkinjava.annotation.blog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc:    基本资料注解
 * Created by tiny on 2018/1/4.
 * Time: 10:41
 * Version:
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Profile {
    /**
     * ID
     */
    public int id() default -1;

    /**
     * 身高
     */
    public int height() default 0;

    /**
     * 籍贯
     */
    public String nativePlace() default "";
}
