package com.tiny.demo.firstlinecode.kotlin.official.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Description: 协程官网教程——
 * https://www.kotlincn.net/docs/tutorials/coroutines-basic-jvm.html
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/27 10:15 AM
 */

fun main(args: Array<String>) {
    runBlocking {
        delay(1000L)
    }
}

/**
 * This starts a new coroutine. By default, coroutines are run on a shared pool of threads.
 * Threads still exist in a program based on coroutines, but one thread can run many coroutines,
 * so there's no need for too many threads.
 * launch方法启动了一个新的协程。默认情况下，协程运行在一个共享线程池中。
 * 线程依赖于协程，但一个线程可以运行很多协程，所以这里不需要太多的线程。
 */
fun launchTest() {
    println("Start")

    //Start a coroutine
    GlobalScope.launch {
        println("launchTest:" + Thread.currentThread().name)
        delay(1000L)
        println("hello")
    }
    println("main:" + Thread.currentThread().name)
    Thread.sleep(2000L)
    println("Stop")
}
