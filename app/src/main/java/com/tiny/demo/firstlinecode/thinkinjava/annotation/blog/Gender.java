package com.tiny.demo.firstlinecode.thinkinjava.annotation.blog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc:    性别注解
 * https://mp.weixin.qq.com/s/NwlIy_knmx4Es7kFnk0gRQ
 * Created by tiny on 2018/1/4.
 * Time: 10:30
 * Version:
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Gender {
    public enum GenderType {
        Male("男"), Female("女"), Other("中性");

        private String genderStr;

        GenderType(String genderStr) {
            this.genderStr = genderStr;
        }

        @Override
        public String toString() {
            return genderStr;
        }
    }

    GenderType gender() default GenderType.Male;
}
