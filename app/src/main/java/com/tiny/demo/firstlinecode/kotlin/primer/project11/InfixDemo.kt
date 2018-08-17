package com.tiny.demo.firstlinecode.kotlin.primer.project11

/**
 * @Description: 自定义中缀表达式
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/17 2:04 PM
 */
sealed class CompareResult {
    object LESS : CompareResult() {
        override fun toString(): String {
            return "小于"
        }
    }

    object MORE : CompareResult() {
        override fun toString(): String {
            return "大于"
        }
    }

    object EQUAL : CompareResult() {
        override fun toString(): String {
            return "等于"
        }
    }
}

infix fun Int.vs(num: Int): CompareResult =
        if (this - num < 0) {
            CompareResult.LESS
        } else if (this - num > 0) {
            CompareResult.MORE
        } else {
            CompareResult.EQUAL
        }

fun main(args: Array<String>) {
    println(5 vs 4)
    println(5 vs 5)
    println(5 vs 6)

    println(10.vs(12))
}