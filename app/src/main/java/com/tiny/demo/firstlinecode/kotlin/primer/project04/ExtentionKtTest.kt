package com.tiny.demo.firstlinecode.kotlin.primer.project04

import java.io.File
import java.nio.charset.Charset

/**
 * desc 拓展函数
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/10 11:56 PM
 */

/**
 * 定义扩展函数
 */
fun File.readTextTest(charset: Charset = Charsets.UTF_8): String = readBytes().toString(charset)

/**
 * 拓展函数的使用，跟正常函数使用一样。
 */
fun main(args: Array<String>): Unit {
    //文件的根目录是项目的根目录
    val file = File("app/src/main/java/com/tiny/demo/firstlinecode/kotlin/primer/project04/Main.kt")
    println(file.readTextTest())
}
