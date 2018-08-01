package com.tiny.demo.firstlinecode.test.view.floating;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc: https://blog.csdn.net/industriously/article/details/70337940
 * NestedScrollingParent，NestedScrollingChild就是两个接口，在新的android.support.v4包中，
 * 两个接口定义了一些操作，然后通过NestedScrollingChildHelper把两者联系起来。
 *
 * @author tiny
 * @date 2018/4/1 下午4:04
 */
public class NestedScrollingActivity extends AppCompatActivity {

    @BindView(R.id.list_view)
    NestedListView mListView;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, NestedScrollingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling);
        ButterKnife.bind(this);

        String[] arr = new String[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + "";
        }
        mListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr));
    }
}
