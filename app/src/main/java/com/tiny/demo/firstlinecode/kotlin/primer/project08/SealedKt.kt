package com.tiny.demo.firstlinecode.kotlin.primer.project08

/**
 * @Description: 密闭类
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 10:17 AM
 */
sealed class SuperCommand {

    fun show() {
        println("SuperCommand")
    }

    object A : SuperCommand()

    object B : SuperCommand() {
        fun showB() {
            println("SuperCommand object B")
        }
    }

    class E(var id: Int) : SuperCommand() {
        init {
            println("SuperCommand E init")
        }

        fun showE() {
            println(println("SuperCommand object E"))
        }
    }
}

fun show(superCommand: SuperCommand, int: Int) = when (superCommand) {
    SuperCommand.A -> {
        superCommand.show()
    }
    SuperCommand.B -> {
        var b = superCommand as SuperCommand.B
        b.showB()
    }
    SuperCommand.E(int) -> {
        var e: SuperCommand.E = superCommand as SuperCommand.E
        e.showE()
    }
    else -> {
        superCommand.show()
    }
}

fun main(args: Array<String>) {
    show(SuperCommand.A, 0)
    show(SuperCommand.B, 0)
    show(SuperCommand.E(10), 0)
}