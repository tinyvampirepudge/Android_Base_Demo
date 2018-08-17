package com.tiny.demo.firstlinecode.kotlin.primer.project10

/**
 * @Description: 作用域函数
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/17 10:01 AM
 */
data class ScopeUser(var name: String)

fun main(args: Array<String>) {
    var user = ScopeUser("WangDanDan")

    // let与run都会返回闭包的执行结果，区别在于let有闭包参数，而run没有闭包参数
    println("---------let and run start---------")
    val letResult = user.let {
        println("let::${it.javaClass}")
        it.javaClass.simpleName
    }
    println(letResult)
    val runResult = user.run {
        println("run::${this.javaClass}")
        javaClass.simpleName
    }
    println(runResult)
    println("---------let and run end---------")

    // also与apply都不返回闭包的执行结果，区别在于also有闭包参数，而apply没有闭包参数
    // 返回值是调用者本身，所以可以支持链式调用
    println("---------also and apply start---------")
    val alsoResult = user.also {
        println("also::${it.javaClass}")
        it.javaClass
    }
    println(alsoResult)
    val applyResult = user.apply {
        println("apply::${this.javaClass}")
        this.javaClass
    }
    println(applyResult)

    // 链式调用
    user.also {
        it.name = "DanDan"
    }.apply {
        println(this)
    }.name = "hello"
    println(user)
    println("---------also and apply end---------")

    println("---------takeIf and takeUnless start---------")
    // takeIf的闭包返回一个判断结果，为false时，takeIf函数会返回空
    // takeUnless 与 takeIf 刚好相反，闭包的判断结果，为true时会返回空
    // 可链式调用
    user.takeIf { it.name.length > 0 }?.also { println("姓名为${it.name}") } ?: println("姓名为空")
    user.takeUnless { it.name.length > 0 }?.also { println("姓名为空") } ?: println("姓名为${user.name}")

    println("---------takeIf and takeUnless end---------")

    println("---------run and with start---------")
    // run方法返回闭包最后一行的结果
    val runResult1 = run {
        println("run::${user}")
        user.javaClass.simpleName
    }
    println(runResult1)

    // 返回闭包最后一行的结果
    val withResult = with(user) {
        this
    }
    println("withResult::$withResult")


    println(with(user) {
        println("")
    })
    println("---------run and with end---------")

    println("---------repeat start---------")
    // 重复执行当前闭包
    repeat(5) {
        print("$it:")
        println(user.name)
    }
    println("---------repeat end---------")
}