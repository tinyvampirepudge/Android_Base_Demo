package com.tiny.demo.firstlinecode.drawable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tiny.demo.firstlinecode.R
import com.tinytongtong.tinyutils.ScreenUtils
import kotlinx.android.synthetic.main.activity_drawable_test3.*

/**
 * @Description: 加载xxxhdpi的图片
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-15 11:28
 * @Version TODO
 */
class DrawableTest3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_test3)

        tvDisplayMetricsInfo.text = ScreenUtils.getDisplayMetricsInfo(this)

        iv.post {
            var sb = StringBuffer("图片实际宽高：\n")
            sb.append("width: " + iv.width)
            sb.append("\n")
            sb.append("height: " + iv.height)

            tvIvInfo.text = sb.toString()
        }
    }
}
