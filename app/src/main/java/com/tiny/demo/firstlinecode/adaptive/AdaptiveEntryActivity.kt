package com.tiny.demo.firstlinecode.adaptive

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tiny.demo.firstlinecode.R
import kotlinx.android.synthetic.main.activity_adaptive_entry.*

/**
 * @Description: 各类适配问题
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-23 18:11
 * @Version TODO
 */
class AdaptiveEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adaptive_entry)

        btn_test1.setOnClickListener({
            startActivity(Intent(this, AdaptiveLayoutActivity::class.java))
        })

        btn_test2.setOnClickListener({
            startActivity(Intent(this, AdaptiveNavigationBarActivity::class.java))
        })
    }
}
