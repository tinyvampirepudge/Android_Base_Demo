package com.tiny.demo.firstlinecode.kotlin.primer.project06

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tiny.demo.firstlinecode.R
import org.jetbrains.anko.toast

/**
 * @Description: 主构造函数
 *
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/8/13 2:49 PM
 */

/*
* 1、open修饰符，表示子类可以继承
* 2、父类AppCompatActivity后面有个小括号，表示我们调用了父类无参的构造方法；如果我们的Activity中没有实现构造方法的话，这个括号也是可以省略的。
* 3、如果我们要给当前类添加构造参数的话，需要在类名后面添加括号，直接写入参数，初始化的操作需要写在init代码块中。
* 4、如果在kotlin中有多个构造函数的话，你需要显式的声明它的次级构造函数。
* */
open class KotlinConstructorActivity : AppCompatActivity(), View.OnClickListener {

    init {
        println("KotlinConstructorActivity init")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_constructor)

        findViewById<View>(R.id.textView).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        toast("Hello Kotlin is clicked!")
    }
}
