package com.tiny.demo.firstlinecode.kotlin.primer.project12

/**
 * @Description: kotlin 空安全原理
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 11:23 AM
 */

fun main(args: Array<String>) {
    var a: String = ""
    println(getValue(a))
}

fun getValue(s: String): String {
    return "1" + s.length
}