package com.tiny.demo.firstlinecode.kotlin.primer.project06

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * desc
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 3:02 PM
 */
open class TestView : View {
    // 直接继承了父类的构造函数
    constructor(context: Context?) : super(context) {
        println(this.javaClass.simpleName + " Constructor 1")
    }

    // 间接继承了主构造函数
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        println(this.javaClass.simpleName + " Constructor 2")
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        println(this.javaClass.simpleName + " Constructor 3")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        println(this.javaClass.simpleName + " Constructor 4")
    }
}