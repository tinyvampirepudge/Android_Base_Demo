package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kyleduo.switchbutton.SwitchButton;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.AnimationAdapter;
import com.tiny.demo.firstlinecode.brvah.animation.CustomAnimation;
import com.tiny.demo.firstlinecode.brvah.data.DataServer;
import com.tiny.demo.firstlinecode.brvah.entity.Status;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimationUseActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView mImgBtn;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private AnimationAdapter mAnimationAdapter;
    private int mFirstPageItemCount = 3;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_animation_use;
    }

    @Override
    protected void buildContentView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        initMenu();
    }

    private void initAdapter() {
        mAnimationAdapter = new AnimationAdapter(R.layout.layout_animation, DataServer.getSampleData(100));
        mAnimationAdapter.openLoadAnimation();
        mAnimationAdapter.setNotDoAnimationCount(mFirstPageItemCount);
        mAnimationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String content = null;
                Status status = (Status) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.img:
                        content = "img:" + status.getUserAvatar();
                        Toast.makeText(AnimationUseActivity.this, content, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tweetName:
                        content = "name:" + status.getUserName();
                        Toast.makeText(AnimationUseActivity.this, content, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tweetText:
                        content = "tweetText:" + status.getUserName();
                        Toast.makeText(AnimationUseActivity.this, content, Toast.LENGTH_LONG).show();
                        // you have set clickspan .so there should not solve any click event ,just empty
                        break;

                }
            }
        });
        mRecyclerView.setAdapter(mAnimationAdapter);
    }

    private void initMenu() {
        spinner.setItems("AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "Custom");
        spinner.setOnItemSelectedListener((view, position, id, item) -> {
            switch (position) {
                case 0:
                    mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                    break;
                case 1:
                    mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                    break;
                case 2:
                    mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                    break;
                case 3:
                    mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                    break;
                case 4:
                    mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                    break;
                case 5:
                    mAnimationAdapter.openLoadAnimation(new CustomAnimation());
                    break;
                default:
                    break;
            }
            mRecyclerView.setAdapter(mAnimationAdapter);
        });
        mAnimationAdapter.isFirstOnly(false);//init firstOnly state

        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mAnimationAdapter.isFirstOnly(true);
            } else {
                mAnimationAdapter.isFirstOnly(false);
            }
            mAnimationAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.img_back, R.id.spinner, R.id.switch_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
