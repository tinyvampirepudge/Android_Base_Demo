package com.tiny.demo.firstlinecode.kotlin.primer.project01


/**
 * desc
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/8 10:27 PM
 */

var age = 18

var name = "Dan Dan"
var name2: String? = null

fun main(args: Array<String>) {
//    name = name2!!
    printLen("王蛋蛋的的father")
}

fun printLen(str: String): String {
    println(str)
    return str
}