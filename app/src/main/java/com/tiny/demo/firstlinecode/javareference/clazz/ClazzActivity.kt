package com.tiny.demo.firstlinecode.javareference.clazz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.tiny.demo.firstlinecode.R
import org.jetbrains.anko.startActivity

/**
 * @Description: Class相关测试
 *
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/8/15 3:15 PM
 */
class ClazzActivity : AppCompatActivity() {


    companion object {
        fun actionStart(context: Context, bundle: Bundle?) {
            val intent = Intent(context, ClazzActivity::class.java)
            intent.putExtra("extra", bundle)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clazz)

        var btnGetName = findViewById<Button>(R.id.btnGetName)
        btnGetName.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("getName()")
                println(Name::class.java.name)
                println(Name.Inner::class.java.name)
                println()
            }

        })

        var btnGetSimpleName = findViewById<Button>(R.id.btnGetSimpleName)
        btnGetSimpleName.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("getSimpleName()")
                println(Name::class.java.simpleName)
                println(Name.Inner::class.java.simpleName)
                println()
            }

        })

        var btnGetCanonicalName = findViewById<Button>(R.id.btnGetCanonicalName)
        btnGetCanonicalName.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("getCanonicalName()")
                println(Name::class.java.canonicalName)
                println(Name.Inner::class.java.canonicalName)
                println()
            }

        })
    }
}
