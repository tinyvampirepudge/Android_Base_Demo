package com.tiny.demo.firstlinecode.kotlin.primer.project02;

import com.tiny.demo.firstlinecode.kotlin.primer.project02.object.Test;

/**
 * desc Java代码调用kotlin方法
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/9 9:26 AM
 */
public class Main {
    public static void main(String[] args) {
        UtilsDemo.echo("hello");

        //调用匿名内部类
        Test.INSTANCE.sayMessage("hello kotlin by java");
    }
}
