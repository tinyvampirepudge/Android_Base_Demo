package com.tiny.demo.firstlinecode.kotlin.primer.project02

import kotlin.reflect.KClass

/**
 * desc kotlin中调用Java中的class
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/9 10:05 AM
 */

fun main(args: Array<String>) {
    testJavaClass(JavaMain::class.java)
    testKotlinClass(KotlinMain::class)
    println(JavaMain.`in`)
}

fun testJavaClass(clazz: Class<JavaMain>) {
    println(clazz.simpleName)
}

fun testKotlinClass(clazz: KClass<KotlinMain>) {
    println(clazz.simpleName)
}