package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader1.ImageLoader1;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader2.ImageLoader2;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version1.ImageLoader3;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2.DiskCache;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2.DoubleCache;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2.ImageCache;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2.ImageLoader31;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2.MemoryCache;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageLoaderActivity extends BaseActivity {
    public static final String INTENT_TYPE = "INTENT_TYPE";
    public static final String TYPE1 = "TYPE1";
    public static final String TYPE2 = "TYPE2";
    public static final String TYPE3 = "TYPE3";
    public static final String TYPE4 = "TYPE4";
    public static final String TYPE5 = "TYPE5";
    public static final String TYPE6 = "TYPE6";
    public static final String TYPE7 = "TYPE7";
    @BindView(R.id.txt_image_laoder_type)
    TextView txtImageLoaderType;


    private String type = TYPE1;

    @BindView(R.id.btn_download_img)
    Button btnDownloadImg;
    @BindView(R.id.img_show)
    ImageView imgShow;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_image_loader;
    }

    @Override
    protected void buildContentView() {
        if (getIntent() != null) {
            type = getIntent().getStringExtra(INTENT_TYPE);
        }

        if (TextUtils.isEmpty(type)) {
            LogUtils.e("type传递为空，取得默认值。");
            type = TYPE1;
        }

        switch (type) {
            case TYPE1:
                txtImageLoaderType.setText("ImageLoader写在一个类里面");
                break;
            case TYPE2:
                txtImageLoaderType.setText("ImageLoader 单一职责原则");
                break;
            case TYPE3:
                txtImageLoaderType.setText("ImageLoader 开闭原则");
                break;
            default:
                break;
        }
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_download_img)
    public void onViewClicked() {
        /**
         * "http://qcg-sta.oss-cn-qingdao.aliyuncs.com/lhbapp/lhb-top-banner09.png",
         * "http://qcg-sta.oss-cn-qingdao.aliyuncs.com/lhbapp/lhb-top-banner07.png",
         * "http://qcg-sta.oss-cn-qingdao.aliyuncs.com/lhbapp/lhb-top-banner08.png",
         */
        String url = "http://qcg-sta.oss-cn-qingdao.aliyuncs.com/lhbapp/lhb-top-banner12.png";
        switch (type) {
            case TYPE1:
                loadImage1(url);
                break;
            case TYPE2:
                loadImage2(url);
                break;
            case TYPE3:
                loadImage3(url);
                break;
            default:
                break;
        }
    }

    private void loadImage1(String url) {
        ImageLoader1 imageLoader1 = new ImageLoader1();
        imageLoader1.displayImage(url, imgShow);
    }

    private void loadImage2(String url) {
        ImageLoader2 imageLoader2 = new ImageLoader2();
        imageLoader2.displayImage(url, imgShow);
    }

    private void loadImage3(String url) {
        ImageLoader3 imageLoader3 = new ImageLoader3();
        imageLoader3.setUseDiskCache(true);
        imageLoader3.setDoubleCache(true);
        imageLoader3.displayImage(url, imgShow);
    }

    private void loadImage31(String url) {
        /**
         * ImageLoader31类中增加了一个setImageCache(ImageCache cache)函数，用户可以通过该函数设置缓存实现，
         * 也就是通常说的依赖注入。下面就看看用户时如何设置缓存实现的：
         */
        ImageLoader31 imageLoader31 = new ImageLoader31();
        //使用内存缓存
        imageLoader31.setImageCache(new MemoryCache());
        //使用SD卡缓存
        imageLoader31.setImageCache(new DiskCache());
        //使用双缓存
        imageLoader31.setImageCache(new DoubleCache());
        //使用自定义的图片缓存实现
        imageLoader31.setImageCache(new ImageCache() {
            @Override
            public void put(String url, Bitmap bitmap) {

            }

            @Override
            public Bitmap get(String url) {
                return null;
            }
        });

        /**
         * 上述代码中，通过setImageCache(ImageCache cache)方法注入不同的缓存实现，这样不仅能够使ImageLoader
         * 更简单、健壮，也是的ImageLoader的可拓展性、灵活性更高。MemoryCache、DiskCache、DoubleCache缓存图片的
         * 具体实现完全不一样，但是，它们的一个特点是，都实现了ImageCache接口。
         * 开闭原则：软件中的对象（类、模块、函数等）应该对于扩展是开放的，但是，对于修改是封闭的。而遵循开闭原则的重要
         * 手段应该是通过抽象。
         */
    }
}
