package com.tiny.demo.firstlinecode.kotlin.primer.project11

import java.io.File

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/17 3:20 PM
 */

fun main(args: Array<String>) {
    val a: A = A("app/src/main/java/com/tiny/demo/firstlinecode/kotlin/primer/project11/TypeAliasDemo.kt")
    println(a.readText())
    println("--------------")
    val b: A = File("app/src/main/java/com/tiny/demo/firstlinecode/kotlin/primer/project11/MainKt.kt")
    println(b.readText())
}