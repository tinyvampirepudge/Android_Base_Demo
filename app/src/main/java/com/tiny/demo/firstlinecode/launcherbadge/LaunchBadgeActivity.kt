package com.tiny.demo.firstlinecode.launcherbadge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tiny.demo.firstlinecode.R
import kotlinx.android.synthetic.main.activity_launch_badge.*

/**
 * @Description: 桌面角标适配
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/10/8 2:30 PM
 * @Version TODO
 */
class LaunchBadgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_badge)

        btn_show_badge.setOnClickListener {
            BadgeNumberManager.from(this).setBadgeNumber(10)
        }

        btn_clear_badge.setOnClickListener {
            BadgeNumberManager.from(this).setBadgeNumber(0)
        }
    }
}
