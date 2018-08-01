package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ScreenUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter03.view.HorizontalScrollViewEx;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:    外部水平滑动，内部竖直滑动的冲突解决。
 *
 * @author tiny
 * @date 2018/3/27 下午5:43
 */
public class Chapter030501Activity extends AppCompatActivity {
    public static final String TAG = Chapter030501Activity.class.getSimpleName();
    @BindView(R.id.container)
    HorizontalScrollViewEx mListContainer;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter030501Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter030501);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        int screenWidth = ScreenUtils.getScreenW(Chapter030501Activity.this);
        int screenHeight = ScreenUtils.getScreenH(Chapter030501Activity.this);
        for (int j = 0; j < 3; j++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.layout_listview, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("page " + (j + 1));
            layout.setBackgroundColor(Color.rgb(255 / (j + 1), 255 / (j + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for (int j = 0; j < 50; j++) {
            datas.add("name " + j);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
    }

}
