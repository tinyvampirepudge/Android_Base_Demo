package com.tiny.demo.firstlinecode.kotlin.jackchen1024

import kotlinx.coroutines.experimental.*

/**
 * @Description: 协程上下文与调度器
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/28 9:45 AM
 */
fun main(args: Array<String>) {
    testDispatchersAndThreads()
}

fun testDispatchersAndThreads() = runBlocking {
    val jobs = ArrayList<Job>()
    jobs += launch(Unconfined) {
        //未做限制 —— 将会在 main thread 中执行
        println("Unconfined: I'm working in thread ${Thread.currentThread()}")
    }

    jobs += launch {
        println("Default: I'm working in thread ${Thread.currentThread()}")
    }

    jobs += launch(CommonPool) {
        // 调度指派给ForkJoinPool.commonPool
        println("CommonPool: I'm working in thread ${Thread.currentThread()}")
    }

    jobs += launch(newSingleThreadContext("MyOwnThread")){
        // 将会在这个协程自己的新线程中执行
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread()}")
    }
}