package com.tiny.demo.firstlinecode.uicomponents.viewpager.loop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.uicomponents.viewpager.pagetransformer.ScaleDownPageTransformer;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tiny on 2018/1/19 下午9:39
 * Version:
 */
public class LoopViewPagerActivity extends BaseActivity {

    @BindView(R.id.target_position)
    EditText mTargetPosition;
    @BindView(R.id.scroll_to_target_position)
    Button mScrollToPosition;
    @BindView(R.id.loopRecyclerViewPager)
    LoopRecyclerViewPager mRecyclerViewPager;
    @BindView(R.id.loopRecyclerViewPager_Parent)
    LoopRecyclerViewPagerParent loopRecyclerViewPagerParent;
    private Banner banner;
    private ConvenientBanner convenientBanner;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_loop_view_pager;
    }

    @Override
    protected void buildContentView() {
        banner = findViewById(R.id.banner);
        //设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.ScaleInOut);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
        banner.setImages(getImages());
        banner.setOffscreenPageLimit(2);
        banner.setPageTransformer(true, new ScaleDownPageTransformer());
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        convenientBanner = findViewById(R.id.convenientbanner);
        convenientBanner.setFocusable(false);
        convenientBanner.setFocusableInTouchMode(false);
        convenientBanner.getViewPager().setFocusable(false);
        convenientBanner.getViewPager().setFocusableInTouchMode(false);
//        int width = getWindowManager().getDefaultDisplay().getWidth();
//        int height = ScreenUtils.dip2px(mContext, 90);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
//        convenientBanner.setLayoutParams(layoutParams);

        convenientBanner.setPageTransformer(new ScaleDownPageTransformer());

        setBanner(getBannerList());

        initViewPager();
    }

    private List<LunBoImgBean> getBannerList() {
        List<LunBoImgBean> bannerList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            LunBoImgBean lunBoImgBean = new LunBoImgBean();
            lunBoImgBean.img_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516388204992&di=e237ec9bd17a9364ad88ae44efec8895&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01a7ca58df3145a801219c77b4110e.jpg%402o.jpg";
            bannerList.add(lunBoImgBean);
        }
        return bannerList;
    }

    /**
     * 设置banner
     *
     * @param bannerList
     */
    public void setBanner(final List<LunBoImgBean> bannerList) {
        List<String> netWorkImages = new ArrayList<>();
        if (bannerList.size() > 0) {
            for (int i = 0; i < bannerList.size(); i++) {
                netWorkImages.add(bannerList.get(i).img_url);
            }
            convenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new NetworkImageHolderView();
                }
            }, netWorkImages)
                    .setPageIndicator(new int[]{R.drawable.ic_gray_line, R.drawable.ic_white_line})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            if (!convenientBanner.isTurning())
                convenientBanner.startTurning(2000);
            convenientBanner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });

            //convenientBanner.setManualPageable(false);//设置不能手动影响
        } else {
            convenientBanner.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //banner holder 网络
    public class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            //网络加载
            Glide.with(mContext).load(data).into(imageView);
        }
    }

    private ArrayList<String> getImages() {
        ArrayList<String> images = new ArrayList<>();
        images.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3087155188,3927553393&fm=27&gp=0.jpg");
        images.add("http://img5.duitang.com/uploads/item/201506/14/20150614215810_ckiPE.jpeg");
        images.add("http://bizhi.cnanzhi.com/upload/bizhi/2014/1202/1417503024216.png");
        images.add("http://img4.duitang.com/uploads/item/201511/09/20151109150254_EFTmw.jpeg");
        images.add("http://img1.3lian.com/2015/w22/99/d/86.jpg");
        images.add("http://cdn.duitang.com/uploads/item/201504/09/20150409H0455_dSNH4.jpeg");
        return images;
    }

    @Override
    protected void initViewData() {

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    protected void initViewPager() {
        mScrollToPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = !TextUtils.isEmpty(mTargetPosition.getText()) ? Integer.valueOf(mTargetPosition.getText().toString()) : 0;
                mRecyclerViewPager.smoothScrollToPosition(position);
            }
        });

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerViewPager.setTriggerOffset(0.15f);
        mRecyclerViewPager.setFlingFactor(0.25f);
        mRecyclerViewPager.setLayoutManager(layout);
        mRecyclerViewPager.setAdapter(new LayoutAdapter(this, mRecyclerViewPager));
        mRecyclerViewPager.setHasFixedSize(true);
        mRecyclerViewPager.setLongClickable(true);

        mRecyclerViewPager.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
//                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
                int childCount = mRecyclerViewPager.getChildCount();
                int width = mRecyclerViewPager.getChildAt(0).getWidth();
                int padding = (mRecyclerViewPager.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        mRecyclerViewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerViewPager.getChildCount() < 3) {
                    if (mRecyclerViewPager.getChildAt(1) != null) {
                        View v1 = mRecyclerViewPager.getChildAt(1);
                        v1.setScaleY(0.9f);
                    }
                } else {
                    if (mRecyclerViewPager.getChildAt(0) != null) {
                        View v0 = mRecyclerViewPager.getChildAt(0);
                        v0.setScaleY(0.9f);
                    }
                    if (mRecyclerViewPager.getChildAt(2) != null) {
                        View v2 = mRecyclerViewPager.getChildAt(2);
                        v2.setScaleY(0.9f);
                    }
                }

            }
        });
        loopRecyclerViewPagerParent.set(mRecyclerViewPager);
        loopRecyclerViewPagerParent.start();
    }
}
