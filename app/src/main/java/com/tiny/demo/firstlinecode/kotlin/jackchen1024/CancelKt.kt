package com.tiny.demo.firstlinecode.kotlin.jackchen1024

import kotlinx.coroutines.*

/**
 * @Description: 协程执行的取消
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/27 3:42 PM
 */

fun main(args: Array<String>) {
//    testCancellation()
//    testCooperativeCancellation1()
//    testCooperativeCancellation2()
    testCooperativeCancellation3()
}

fun testCancellation() = runBlocking {
    val job = GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ... CurrentThread: ${Thread.currentThread()}")
            delay(500L)
        }
    }
    delay(1300L)
    println("CurrentThread: ${Thread.currentThread()}")
    println("Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    val b1 = job.cancel()// cancel the job
    println("job cancel: $b1")
    delay(1300L)
    println("Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    var b2 = job.cancel()// cancels the job, job already canceled, return false
    println("job calcel: $b2")

    println("main: Now I can quit.")
}

/**
 * 计算的协程无法取消
 */
fun testCooperativeCancellation1() = runBlocking {
    var job = GlobalScope.launch {
        var nextPrintTime = 0L
        var i = 0
        while (i < 20) { // computation loop
            var currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ... CurrentThread: ${Thread.currentThread()}")
                nextPrintTime = currentTime + 500L
            }
        }
    }

    delay(3000L)
    println("CurrentThread：${Thread.currentThread()}")
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    val b1 = job.cancel()
    println("job cancel1: $b1")
    println("After Cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    delay(5000L)

    val b2 = job.cancel() // cancels the job, job already canceled, returh false
    println("job cancel2: $b2")

    println("main: Now I can quit.")
}

/**
 * 计算代码协程的有效取消
 * 显式检查取消状态isActive
 */
fun testCooperativeCancellation2() = runBlocking {
    var job = GlobalScope.launch {
        var nextPrintTime = 0L
        var i = 0
        while (i < 20) {// computation loop
            if (!isActive) {
                return@launch
            }

            val currentTime = System.currentTimeMillis()
            if ((currentTime >= nextPrintTime)) {
                println("I'm sleeping ${i++} ... CurrentThread: ${Thread.currentThread()}")
                nextPrintTime = currentTime + 500L
            }
        }
    }

    delay(3000L)
    println("CurrentThread: ${Thread.currentThread()}")
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    val b1 = job.cancel()// cancels the job
    println("job cancel1: $b1")
    println("After Cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    delay(5000L)

    val b2 = job.cancel() // cancels the job, job already canceled, return false
    println("job cancel2: $b2")

    println("main: Now I can quit.")
}


fun testCooperativeCancellation3() = runBlocking {
    val job = GlobalScope.launch {
        var nextPrintTime = 0L
        var i = 0
        while (i < 20) {// computation loop

            try {
                yield()
            } catch (e: Exception) {
                println("$i ${e.message}")
            }

            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ... CurrentThread; ${Thread.currentThread()}")
                nextPrintTime = currentTime + 500L
            }
        }
    }

    delay(3000L)
    println("CurrentThread: ${Thread.currentThread()}")
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    val b1 = job.cancel() // cancels the job
    println("job calcel1: $b1")
    println("After cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    delay(3000L)
    val b2 = job.cancel() // cancels the job, hob already canceled, return false
    println("job cancel2: $b2")

    println("main: Now I can quit.")
}