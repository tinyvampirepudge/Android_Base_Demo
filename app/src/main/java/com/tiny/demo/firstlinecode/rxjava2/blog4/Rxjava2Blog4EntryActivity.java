package com.tiny.demo.firstlinecode.rxjava2.blog4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.rxjava2.blog4.module.elementary_1.ElementaryFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.module.map_2.MapFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.module.token_adavnced_retrywhen_5.TokenAdvancedFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.module.token_flatmap_4.TokenFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.module.zip_3.ZipFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc: 给 Android 开发者的 RxJava 详解
 * 扔物线
 * http://gank.io/post/560e15be2dca930e00da1083
 *
 * @author tiny
 * @date 2018/6/17 下午4:34
 */
public class Rxjava2Blog4EntryActivity extends AppCompatActivity {
    @BindView(android.R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private List<Fragment> fragments;
    List<Integer> titleIds = Arrays.asList(R.string.title_elementary,
            R.string.title_map,
            R.string.title_zip,
            R.string.title_token,
            R.string.title_token_advanced,
            R.string.title_cache
    );

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Blog4EntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_blog4_entry);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);

        fragments = new ArrayList<>();
        fragments.add(new ElementaryFragment());
        fragments.add(new MapFragment());
        fragments.add(new ZipFragment());
        fragments.add(new TokenFragment());
        fragments.add(new TokenAdvancedFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getString(titleIds.get(position));
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }


}
