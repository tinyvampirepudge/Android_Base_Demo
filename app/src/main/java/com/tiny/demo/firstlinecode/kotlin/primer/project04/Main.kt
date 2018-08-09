package com.tiny.demo.firstlinecode.kotlin.primer.project04

/**
 * desc
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/9 11:10 PM
 */

fun main(args: Array<String>) {
    print1("hello world")
    print1()
    print2("王蛋蛋")
    function()
}

fun print1(name: String = "dandan"): String? {
    println("$name")
    return name
}

fun print2(name: String) = println("$name")

fun function() {
    val string = "hello world"

    fun say(count: Int = 10) {
        println(string)
        if (count > 0) {
            say(count - 1)
        }
    }
    say()
}