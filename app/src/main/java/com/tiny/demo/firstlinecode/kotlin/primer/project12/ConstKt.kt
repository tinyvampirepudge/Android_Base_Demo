package com.tiny.demo.firstlinecode.kotlin.primer.project12

/**
 * @Description: const
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 11:05 AM
 */

// top-level
const val a = 0

fun main(args: Array<String>) {
    val a = A.m
}

object A {
    const val m = 0
}

class B {
    companion object {
        const val n = 0
    }
}
