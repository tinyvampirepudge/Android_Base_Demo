package com.tiny.demo.firstlinecode.kotlin.primer.project09

/**
 * @Description: 播放器View
 * 实现接口
 * 动态代理
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 12:08 PM
 */

interface PlayerView {
    fun showView()

    fun getPlayButton()
}

class GreenPlayerView : PlayerView {
    override fun showView() {
        println("显示绿色播放器")
    }

    override fun getPlayButton() {
        println("显示绿色Button")
    }
}

class BluePlayerView : PlayerView {
    override fun showView() {
        println("显示蓝色播放器")
    }

    override fun getPlayButton() {
        println("显示蓝色Button")
    }

}

class MediaPlayerView(playerView: PlayerView) : PlayerView by playerView