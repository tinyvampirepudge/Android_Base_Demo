package com.tiny.demo.firstlinecode.kotlin.official.function.lambda

/**
 * @Description: lambda表达式语法
 * https://www.kotlincn.net/docs/reference/lambdas.html#lambda-%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%AF%AD%E6%B3%95
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/20 12:10 PM
 */

fun main(args: Array<String>) {
    // 1、Lambda的完整语法格式：

    //Lambda表达式的完整语法形式如下：
    val sum = { x: Int, y: Int -> x + y }

    println(sum) // (kotlin.Int, kotlin.Int) -> kotlin.Int

    /**
     * Lambda 表达式总是括在花括号中， 完整语法形式的参数声明放在花括号内，并有可选的类型标注，
     * 函数体跟在一个 -> 符号之后。
     * 如果推断出的该 lambda 的返回类型不是 Unit，
     * 那么该 lambda 主体中的最后一个（或可能是单个）表达式会视为返回值。
     */

    // 如果我们把所有可选标注都留下，看起来如下:
    val sum1: (Int, Int) -> Int = { x, y -> x + y }
    println(sum1)   //(kotlin.Int, kotlin.Int) -> kotlin.Int

    // 2、将 lambda 表达式传给最后一个参数

    /**
     * 在 Kotlin 中有一个约定：如果函数的最后一个参数接受函数，那么作为相应参数传入的 lambda 表达式可以放在圆括号之外：
     */
    val items = arrayOf(1, 2, 3, 4)
    val product = items.fold(1) { acc, e ->
        acc * e
    }
    println(items)      // [Ljava.lang.Integer;@2357d90a
    println(product)    // 24

    // 如果该 lambda 表达式是调用时唯一的参数，那么圆括号可以完全省略：
    run {
        println("mmp") // mmp
    }

    // 3、单个参数的隐式名称：it
    /**
     * 一个 lambda 表达式只有一个参数是很常见的。
     * 如果编译器自己可以识别出签名，也可以不用声明唯一的参数并忽略 ->。 该参数会隐式声明为 it：
     */
    val ints = arrayOf(-2, -1, 0, 1, 2, 3, 4)
    ints.filter { it > 0 } // 这个字面值是“(it: Int) -> Boolean”类型的
            .forEach { println(it) } // 1 2 3 4

    // 4、从 Lambda 表达式中返回一个值
    /**
     * 我们可以使用限定的返回语法从 lambda 显式返回一个值。 否则，将隐式返回最后一个表达式的值。
     * 因此，以下两个片段是等价的：
     */
    val filterResult1 = ints.filter {
        val shouldFilter = it > 0
        shouldFilter
    }
    println(filterResult1)  // [1, 2, 3, 4]

    val filterResult2 = ints.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter
    }
    println(filterResult2)  // [1, 2, 3, 4]

    // 这一约定连同在圆括号外传递 lambda 表达式一起支持 LINQ-风格 的代码：
    val strings = arrayOf("aaaaa", "bbbbbb", "ccc", "dd", "e")
    strings.filter { it.length >= 3 }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }    // AAAAA BBBBBB CCC

    // 下划线用于未使用的变量
    /**
     * 如果 lambda 表达式的参数未使用，那么可以用下划线取代其名称：
     */
    val map = mapOf("aaa" to "bbb", "ccc" to "ddd")
    map.forEach { _, value -> println("$value") }

    // 在 lambda 表达式中解构是作为解构声明的一部分描述的。

}