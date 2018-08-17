package com.tiny.demo.firstlinecode.kotlin.primer.project11

/**
 * @Description: kotlin中比较对象
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/17 3:04 PM
 */

fun main(args: Array<String>) {

    // 使用Java的String类
    val string = "string";
    val newString = StringBuilder("string").toString()
    val newString2 = String("string".toByteArray())

    println(string == newString)    // equals   true
    println(string === newString)   // ==       false

    println(string == newString2)   // equals   true
    println(string === newString2)  // ==       false
}