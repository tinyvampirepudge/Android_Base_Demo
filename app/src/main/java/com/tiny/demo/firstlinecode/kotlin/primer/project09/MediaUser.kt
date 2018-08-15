package com.tiny.demo.firstlinecode.kotlin.primer.project09

/**
 * @Description: 音乐播放器的用户
 * 数据类，默认数据
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/15 12:15 PM
 */
data class MediaUser(var id: Int,
                     var name: String,
                     var playerViewType: PlayerViewType = PlayerViewType.VIP)