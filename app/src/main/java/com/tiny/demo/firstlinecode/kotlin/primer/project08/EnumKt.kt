package com.tiny.demo.firstlinecode.kotlin.primer.project08

/**
 * @Description: kotlin的枚举
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 9:28 AM
 */
enum class Command {
    E, F, G, H
}

fun execKt(command: Command) =
        when (command) {
            Command.E -> {
                println("Enum Kotlin Command E")
            }
            Command.F -> {
                println("Enum Kotlin Command F")
            }
            Command.G -> {
                println("Enum Kotlin Command G")
            }
            Command.H -> {
                println("Enum Kotlin Command H")
            }
        }

fun execJava(enumJava: EnumJava) = when (enumJava) {
    EnumJava.A -> {
        println("Enum Java A")
    }
    else -> {
        println("Enum Java else")
    }
}

fun main(args: Array<String>) {
    println("调用kotlin中的枚举")
    execKt(Command.E)
    execKt(Command.F)
    execKt(Command.G)
    execKt(Command.H)

    println()
    println("调用Java中的枚举")
    execJava(EnumJava.A)
}