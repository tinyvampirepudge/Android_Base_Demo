package com.tiny.demo.firstlinecode.kotlin.primer.project08

/**
 * @Description: kotlin的动态代理
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 5:49 PM
 */

interface Animal {
    fun bark()
}

class Dog : Animal {
    override fun bark() {
        println("wang wang")
    }
}

class Zoo(animal: Animal) : Animal by animal {
    override fun bark() {
        println("Zoo")
    }

}

fun main(args: Array<String>) {
    Zoo(Dog()).bark()
}