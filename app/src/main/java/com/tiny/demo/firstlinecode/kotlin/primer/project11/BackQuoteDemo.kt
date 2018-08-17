package com.tiny.demo.firstlinecode.kotlin.primer.project11

/**
 * @Description: kotlin中的反引号
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/17 2:20 PM
 */

fun `1234`() {

}

//// 不支持
//fun ` `() {
//    println("")
//}
//
//// 不支持
//fun `  `() {
//
//}

fun main(args: Array<String>) {
    `1234`()
}

