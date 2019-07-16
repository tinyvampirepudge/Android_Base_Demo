package com.tiny.demo.firstlinecode.kotlin.jackchen1024

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Description: runBlocking函数: 使用纯的Kotlin的协程代码来实现上面的 阻塞+非阻塞 的例子（不用Thread）。
 * https://www.jianshu.com/p/993902c98688?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/27 11:57 AM
 */

/**
 * 该runBlocking函数不是用来当作普通协程函数使用的，
 * 它的设计主要是用来桥接普通阻塞代码和挂起风格的（suspending style）的非阻塞代码的,
 * 例如用在 main 函数中，或者用于测试用例代码中。
 */

/**
 * Kotlin中提供了runBlocking函数来实现类似主协程的功能：
 */
fun testRunBlocking() = runBlocking {
    //主协程
    println("111" + Thread.currentThread().name)

    GlobalScope.launch {
        // 在common thread pool中创建协程
        println("222" + Thread.currentThread().name)
        delay(3000L)
        println("333" + "hello")
    }

    println("444 world")

    delay(5000L)

    println("555" + Thread.currentThread().name)
}

/**
 * 等待一个任务执行完毕：Job.join()
 */
fun testJoinCoroutine() = runBlocking<Unit> {
    // Start a coroutine
    var job1 = GlobalScope.launch {
        println("job1 Thread: ${Thread.currentThread()}")
        println("job1 Start")
        delay(3000L)
        println("job1 End")
    }

    var job2 = GlobalScope.launch {
        println("job2 Thread: ${Thread.currentThread()}")
        println("job2 Start")
        delay(5000L)
        println("job2 End")
    }

    println("Main Thread: ${Thread.currentThread()}")

    println("000")
    println("job1 is active: ${job1.isActive}  ${job1.isCompleted}")
    println("job2 is active: ${job2.isActive}  ${job2.isCompleted}")
    println("111")

    job1.join()
    println("222")
    println("job1 is active: ${job1.isActive}  ${job1.isCompleted}")
    println("job2 is active: ${job2.isActive}  ${job2.isCompleted}")
    println("333")

    job2.join()
    println("444")
    println("job1 is active: ${job1.isActive}  ${job1.isCompleted}")
    println("job2 is active: ${job2.isActive}  ${job2.isCompleted}")
    println("555")
}

fun main(args: Array<String>) {
//    testRunBlocking()
    testJoinCoroutine()
}


