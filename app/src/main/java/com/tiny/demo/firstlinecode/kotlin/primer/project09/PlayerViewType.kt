package com.tiny.demo.firstlinecode.kotlin.primer.project09

/**
 * @Description: 当前已经支持的播放器皮肤颜色有绿色和蓝色两种
 * 密闭类
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 11:50 AM
 */
sealed class PlayerViewType {
    object GREEN : PlayerViewType()
    object BLUE : PlayerViewType()
    object VIP : PlayerViewType(), PlayerView {
        override fun showView() {
            println("Vip视图")
        }

        override fun getPlayButton() {
            println("Vip按钮")
        }

    }
}

fun getPlayerView(type: PlayerViewType) = when (type) {
    PlayerViewType.GREEN -> {
        GreenPlayerView()
    }
    PlayerViewType.BLUE -> {
        BluePlayerView()
    }
    is PlayerViewType.VIP -> {
        PlayerViewType.VIP
    }
}