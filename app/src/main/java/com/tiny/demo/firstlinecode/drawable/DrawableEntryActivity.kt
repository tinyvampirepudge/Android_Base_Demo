package com.tiny.demo.firstlinecode.drawable

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tiny.demo.firstlinecode.R
import com.tiny.demo.firstlinecode.common.utils.ToastUtils
import com.tinytongtong.tinyutils.ScreenUtils
import kotlinx.android.synthetic.main.activity_drawable_entry.*

/**
 * @Description: Drawable相关学习
 * https://blog.csdn.net/guolin_blog/article/details/50727753
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-15 10:32
 * @Version
 */
class DrawableEntryActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_entry)

        tvDisplayMetricsInfo.text = ScreenUtils.getDisplayMetricsInfo(this)

        btn_drawable_test1.setOnClickListener {
            ToastUtils.showSingleToast(btn_drawable_test1.text.toString())
            startActivity(Intent(this, DrawableTest1Activity::class.java))
        }

        btn_drawable_test2.setOnClickListener {
            ToastUtils.showSingleToast(btn_drawable_test2.text.toString())
            startActivity(Intent(this, DrawableTest2Activity::class.java))
        }

        btn_drawable_test3.setOnClickListener {
            ToastUtils.showSingleToast(btn_drawable_test3.text.toString())
            startActivity(Intent(this, DrawableTest3Activity::class.java))
        }
    }
}
