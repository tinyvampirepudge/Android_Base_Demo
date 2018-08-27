package com.tiny.demo.firstlinecode.kotlin.jackchen1024

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * @Description: 协程 vs 守护线程
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/27 3:07 PM
 */

fun main(args: Array<String>) {
//    testDeamon1()
    testDeamon2()
}

// Thread的守护线程
fun testDeamon1() {
    var t = Thread {
        repeat(100) { i ->
            println("I'm sleeping $i ...")
            Thread.sleep(500L)
        }
    }
    t.isDaemon = true// 必须在start之前调用，不然会报错 Exception in thread "main" java.lang.IllegalThreadStateException
    t.start()
    Thread.sleep(2000L)
}

// 协程的行为和守护进程很像
fun testDeamon2() = runBlocking {
    launch(CommonPool) {
        repeat(100) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(2000L)
}