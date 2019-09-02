package com.tiny.demo.firstlinecode.concurrentprogram.chapter01

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tiny.demo.firstlinecode.R
import kotlinx.android.synthetic.main.activity_jcpachapter01_entry.*
/**
 * @Description: 第一章 并发编程的挑战
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-29 13:40
 * @Version TODO
 */
class JCPAChapter01EntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jcpachapter01_entry)

        btn_test_01.setOnClickListener({
            ConcurrencyTest.main(null)
        })
    }
}
