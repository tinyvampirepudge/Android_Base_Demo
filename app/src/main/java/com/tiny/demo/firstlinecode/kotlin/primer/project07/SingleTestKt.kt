package com.tiny.demo.firstlinecode.kotlin.primer.project07

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 4:26 PM
 */

fun main(args: Array<String>) {
    println("Java下的单例类")
    SingleJava.getInstance();
    SingleJava.getInstance();
    SingleJava.getInstance();

    println("kotlin下的单例类")
    SingleKt.get();
    SingleKt.get();
    SingleKt.get();
}