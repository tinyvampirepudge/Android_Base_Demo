package com.tiny.demo.firstlinecode.kotlin.primer.project09

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 12:19 PM
 */

fun main(args: Array<String>) {
    val mediaUserBlue = MediaUser(20, "dandan", PlayerViewType.BLUE)
    KotlinSDK.getInstance().showPlayer(mediaUserBlue)

    val mediaUserGreen = MediaUser(21, "dandan1", PlayerViewType.GREEN)
    KotlinSDK.getInstance().showPlayer(mediaUserGreen)

    val mediaUserVip = MediaUser(22, "dandan2")
    KotlinSDK.getInstance().showPlayer(mediaUserVip)
}