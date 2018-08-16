package com.tiny.demo.firstlinecode.kotlin.primer.project10

/**
 * @Description: kotlin中自定义操作符
 * 它的功能其实跟我们的map操作符的功能是一致的，它扩展的是一个迭代器。
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/16 5:53 PM
 */

inline fun <T, E> Iterable<T>.convert(action: (T) -> E): Iterable<E> {
    val list: MutableList<E> = mutableListOf()
    for (item in this) list.add(action(item))
    return list
}

fun main(args: Array<String>) {
    myOperator()
}

fun myOperator() {
    val list: List<Int> = listOf(1, 2, 3, 4, 5)
    list.convert {
        it + 1
    }.forEach {
        println(it)
    }
}