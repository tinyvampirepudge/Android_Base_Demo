package com.tiny.demo.firstlinecode.kotlin.primer.project06

import com.tiny.demo.firstlinecode.kotlin.primer.project01.name

/**
 * desc 主构造函数，init代码块和属性初始化代码的执行顺序。
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 3:24 PM
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