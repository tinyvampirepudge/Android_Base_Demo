package com.tiny.demo.firstlinecode.kotlin.primer.project10

/**
 * @Description: 集合操作符
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/16 2:34 PM
 */

fun main(args: Array<String>) {
    val list = arrayListOf<Char>('a', 'b', 'c', 'd')
    val a = list.map { it - 'a' }// Char --> Int
            .filter { it > 0 } // 这里是Int类型
            .find { it > 1 }// 这里是Int类型，find只会返回符合条件的第一个值
    println(a)
}