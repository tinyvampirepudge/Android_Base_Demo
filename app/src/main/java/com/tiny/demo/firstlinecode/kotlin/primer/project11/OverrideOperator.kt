package com.tiny.demo.firstlinecode.kotlin.primer.project11

/**
 * @Description: 运算符重载
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/17 1:41 PM
 */

fun main(args: Array<String>) {

    // .. 重载的是rangeTo运算符，api说明如下
    /**
     *  Creates a range from this value to the specified [other] value.
     *  public operator fun rangeTo(other: Int): IntRange
     */
    for (i in 1..100 step 20) {
        println("$i" + "")
    }

    // .. 等价于.rangeTo
    for (i in 1.rangeTo(100) step 20) {
        println("$i")
    }

    // step 是一个中缀表达式
    for (i in 1..100 step 20) {
        println("$i")
    }
}