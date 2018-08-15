package com.tiny.demo.firstlinecode.kotlin.primer.project09

/**
 * @Description: 音乐播放APP，每个而用户都可以定制自己的播放器皮肤颜色
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 12:14 PM
 */

class KotlinSDK private constructor() {
    init {

    }

    companion object {
        fun getInstance(): KotlinSDK {
            return KotlinSDKHolder.instance
        }
    }

    private object KotlinSDKHolder {
        val instance = KotlinSDK()
    }

    fun showPlayer(mediaUser: MediaUser) {
        var mediaPlayerView = MediaPlayerView(getPlayerView(mediaUser.playerViewType))
        mediaPlayerView.showView()
        mediaPlayerView.getPlayButton()
    }
}