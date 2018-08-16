package com.tiny.demo.firstlinecode.kotlin.primer.project10

/**
 * @Description: kotlin中的循环
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/16 2:08 PM
 */

fun main(args: Array<String>) {
    // .. 表示闭区间。
    for (i in 1..10) {
        println(i)
    }
    println("------------")

    // until
    for (i in 1 until 10) {
        println(i)
    }
    println("------------")

    // 递减
    for (i in 10 downTo 1) {
        println(i)
    }
    println("------------")

    // step 表示步长
    for (i in 1..10 step 2) {
        println(i)
    }
    println("------------")

    // 高阶函数repeat, 循环执行10次，从0开始
    repeat(10) {
        println(it)
    }
    println("------------")

    // 类似于Java中的forEach循环
    val list: ArrayList<String> = arrayListOf<String>("a", "b", "c", "d")
    for (string: String in list) {
        println(string)
    }
    println("------------")

    // 使用kotlin中的解构
    for ((index, str) in list.withIndex()) {
        println("第${index}个元素是${str}")
    }
}