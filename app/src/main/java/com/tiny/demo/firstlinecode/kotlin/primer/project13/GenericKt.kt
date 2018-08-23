package com.tiny.demo.firstlinecode.kotlin.primer.project13


/**
 * @Description: kotlin的泛型
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 2:32 PM
 */

// T 需要同时实现Callback和Runnable接口
class Test<T> where T : Callback, T : Runnable {
    fun add(t: T) {
        t.run()
        t.callback()
    }
}

interface Callback {
    fun callback()
}

class A : Callback, Runnable {
    override fun callback() {
        println("A callback")
    }

    override fun run() {
        println("A run")
    }
}

fun main(args: Array<String>) {
    val testA = Test<A>()
    testA.add(A())

    val testC = Test<C>()
    testC.add(C())
}

open class B : Callback {
    override fun callback() {
        println("B callback")
    }
}

class C : B(), Runnable {
    override fun run() {
        println("C run")
    }

}