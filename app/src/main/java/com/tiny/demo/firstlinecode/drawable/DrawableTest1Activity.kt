package com.tiny.demo.firstlinecode.drawable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tiny.demo.firstlinecode.R
import com.tinytongtong.tinyutils.ScreenUtils
import kotlinx.android.synthetic.main.activity_drawable_test1.*

/**
 * @Description: 加载当前dpi的图片
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-15 11:27
 * @Version TODO
 */
class DrawableTest1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_test1)

        tvDisplayMetricsInfo.text = ScreenUtils.getDisplayMetricsInfo(this)

        iv.post {
            var sb = StringBuffer("图片实际宽高：\n")
            sb.append("width: " + iv.width)
            sb.append("\n")
            sb.append("height: " + iv.height)

            tvIvInfo.text = sb.toString()
        }

        // 获取当前手机实际的dpi
        var srcDpi = this.resources.displayMetrics.densityDpi

        // 获取实际dpi对应的dpi等级
        var targetDpi = com.tiny.demo.firstlinecode.common.utils.ScreenUtils.getTargetDpi(srcDpi)

        // 根据dpi等级，获取 R.drawable.empty_icon 实际尺寸
        var drawableSize = getDrawableSize(targetDpi)

        // 根据实际尺寸计算缩放后的尺寸
        var resultSize = drawableSize * 1.0f / targetDpi * srcDpi

        tvExpect.text = String.format("期望结果：%.2f", resultSize)
    }

    /**
     * 根据对应的dpi等级，获取 R.drawable.empty_icon 对应的实际尺寸
     */
    fun getDrawableSize(targetDpi: Int): Int {
        var drawableSize = 0;
        when (targetDpi) {
            120, 160 -> drawableSize = 106
            240 -> drawableSize = 159
            320 -> drawableSize = 213
            480 -> drawableSize = 319
            640 -> drawableSize = 426
        }
        return drawableSize
    }
}
