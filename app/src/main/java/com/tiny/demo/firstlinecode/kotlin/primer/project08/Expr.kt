package com.tiny.demo.firstlinecode.kotlin.primer.project08

/**
 * @Description: 密闭类
 * https://www.kotlincn.net/docs/reference/sealed-classes.html
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 10:47 AM
 */

sealed class Expr

data class Const(val number: Double) : Expr()

data class Sum(val e1: Expr, val e2: Expr) : Expr()

object NotANumber : Expr()

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}

fun main(args: Array<String>) {
    println(eval(Const(220.0))) // 220.0
    println(eval(Sum(Const(230.0), Const(240.0))))  // 470.0
    println(eval(NotANumber))   // NaN
}