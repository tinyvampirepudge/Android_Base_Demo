package com.tiny.demo.firstlinecode.test.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.tiny.demo.firstlinecode.R
import com.tiny.demo.firstlinecode.ui.adapter.FruitAdapter
import com.tiny.demo.firstlinecode.ui.bean.Fruit
import kotlinx.android.synthetic.main.activity_recycler_view_in_constraint_layout.*
import java.util.ArrayList

/**
 * @Description: RecyclerView在ConstraintLayout中最后一个Item显示不全的问题
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-28 09:14
 * @Version
 */
class RecyclerViewInConstraintLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_in_constraint_layout)
        var results = initFruits()
        searchResultRecyclerView.layoutManager = LinearLayoutManager(this)
        searchResultRecyclerView.adapter = FruitAdapter(results)
    }

    private fun initFruits():MutableList<Fruit> {
        var fruits = mutableListOf<Fruit>()
        for (i in 0..20) {
            val fruit = Fruit("erha --> $i", R.drawable.ic_erha)
            fruits.add(fruit)
        }
        return fruits
    }
}
