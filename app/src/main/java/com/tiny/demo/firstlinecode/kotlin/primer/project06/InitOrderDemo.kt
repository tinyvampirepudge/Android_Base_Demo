package com.tiny.demo.firstlinecode.kotlin.primer.project06

import com.tiny.demo.firstlinecode.kotlin.primer.project01.name

/**
 * @Description:
 *
 * @Author wangjianzhou@qding.me
 * @Version 
 * @Date 2018/8/13 6:02 PM
 */
class InitOrderDemo(name: String) {
    val firstProperties = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperties = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}
