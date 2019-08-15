package com.tiny.demo.firstlinecode.kotlin.primer.project14

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tiny.demo.firstlinecode.R
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.custom.async

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
    }

    fun test1(v: View) {
        // 表示是在UI线程中启动的
        GlobalScope.launch(Dispatchers.Main) {
            println("在主线程中启动一个协程:" + Thread.currentThread().name)// 在主线程中启动一个协程:main
        }
    }

    fun test2(v: View) {
        WholeRequestJava().display(mContent)
    }

    fun test3(v: View) {
        async {
            displayContent(mContent)
        }
    }

    fun test4(v: View) {
        mContent.text = null
    }
}
