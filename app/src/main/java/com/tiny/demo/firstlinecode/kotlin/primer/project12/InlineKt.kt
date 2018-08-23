package com.tiny.demo.firstlinecode.kotlin.primer.project12

import com.tiny.demo.firstlinecode.kotlin.primer.project12.JavaMain.test2

/**
 * @Description: 内联函数的特殊情况
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 1:36 PM
 */

val runnable = Runnable { println("runnable") }

fun main(args: Array<String>) {
    test1 {
        println("hello1")
        return@test1
        println("hello2")
    }
    println("hello3")


    val test3: () -> Unit = fun() {
        println("test3")
        return
        println("test31")
    }
    test3()

    println("hello4")

    test2({ println("hello5") }, runnable::run)
}

inline fun test1(crossinline l: () -> Unit) {
    l.invoke()
    return
}

inline fun test2(l1: () -> Unit, noinline l2: () -> Unit): () -> Unit {
    l1.invoke()
    l2.invoke()
    println("test2")
    return l2
}