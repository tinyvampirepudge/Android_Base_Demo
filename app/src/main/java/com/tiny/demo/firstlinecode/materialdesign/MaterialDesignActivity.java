package com.tiny.demo.firstlinecode.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static com.tiny.demo.firstlinecode.R.id.nav_gallery;

public class MaterialDesignActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private FruitAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MaterialDesignActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_material_design_activiay;
    }

    @Override
    protected void buildContentView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //对toolbar进行操作
        ActionBar actionBar = getSupportActionBar();//获取actionBar的实例
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//让导航按钮自显示出来，也就是HomeAsUp按钮，默认是一个返回按钮
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);//对HomeAsUp按钮的样式进行修改。
        }

        //设置nav_view的默认选项
        navigationView.setCheckedItem(R.id.nav_camera);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_camera:
                        ToastUtils.showSingleToast("You click camera!");
                        break;
                    case nav_gallery:
                        ToastUtils.showSingleToast("You click gallery!");
                        break;
                    case R.id.nav_slideshow:
                        ToastUtils.showSingleToast("You click slideshow!");
                        break;
                    case R.id.nav_manage:
                        ToastUtils.showSingleToast("You click manage!");
                        break;
                    case R.id.nav_share:
                        ToastUtils.showSingleToast("You click share!");
                        break;
                    case R.id.nav_send:
                        ToastUtils.showSingleToast("You click send!");
                        break;

                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data Deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showSingleToast("to do undo");
                            }
                        }).show();
                //默认snackBar弹出的框会遮挡fab.
                //这里将toolbar的父布局有由原来的FrameLayout替换为CoordinatorLayout，这样snackBar弹出时，
                //fab会对应的上移，就不会遮挡住fab按钮了。
                //它的原理是CoordinatorLayout可以监听到所有子事件的各种事情，然后自动帮助我们做出
                // 最为合理的响应。
                //需要注意的一点是，snackbar的setAction中传入的参数view是fab, 而fab是CoordinatorLayout
                //的一个子控件，因此这个事件就理所应当能被监听到了。如果将这个view替换为mDrawerLayout，
                //那么snackBar弹出时fab的动作就不会被触发。
            }
        });

        //recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(getFruits());
        recyclerView.setAdapter(adapter);

        //SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //设置下拉刷新进度条的颜色。
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();

    }

    private List<Fruit> getFruits() {
        List<Fruit> fruits = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Fruit f = new Fruit("tiny --> " + i, R.mipmap.ic_launcher);
            fruits.add(f);
        }
        return fruits;
    }

    @Override
    protected void initViewData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://HomeAsUp的id
                mDrawerLayout.openDrawer(GravityCompat.START);//为了保证与xml布局里面打开方式保持一致，
                break;
            case R.id.add:
                ToastUtils.showSingleToast("You Click Add button");
                break;
            case R.id.edit:
                ToastUtils.showSingleToast("You Click Edit button");
                break;
            case R.id.menu:
                ToastUtils.showSingleToast("You Click Menu button");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
