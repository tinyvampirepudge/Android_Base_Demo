package com.tiny.demo.firstlinecode.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.MainActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.PreferencesUtils;


public class GuidePageFragment extends Fragment {
    private int page;
    private static final String PAGE = "page";
    private static String[] titleArray = new String[]{"品牌升级", "AI实验室", "事件炒股"};
    private static String[] subTitleArray = new String[]{"品牌升级 炒股服务更上一层", "人工智能 助你收获更高收益", "优质信息 了解股价涨跌逻辑"};

    public static GuidePageFragment newInstance(int page) {
        GuidePageFragment fragment = new GuidePageFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    public GuidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt(PAGE);
        }
    }

    /**
     * 逃过引导
     */
    private void skiperGuid() {
        PreferencesUtils.setBoolean(SplashActivity.SHOW_GUIDE_PAGE, false);
        BaseActivity activity = (BaseActivity) getActivity();
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guide_page, container, false);
        TextView mSkipView = (TextView) v.findViewById(R.id.guide_page_skip);
        ImageView mBgImageView = (ImageView) v.findViewById(R.id.guide_page_imageview);
        TextView mTitle = (TextView) v.findViewById(R.id.guide_page_title);
        TextView mSubTitle = (TextView) v.findViewById(R.id.guide_page_sutitle);
        mTitle.setText(titleArray[page]);
        mSubTitle.setText(subTitleArray[page]);
        mSkipView.setOnClickListener(v1 -> skiperGuid());

        switch (page) {
            case 0:
                mBgImageView.setImageResource(R.drawable.guide_page_1);
                mSkipView.setVisibility(View.GONE);
                break;
            case 1:
                mBgImageView.setImageResource(R.drawable.guide_page_2);
                mSkipView.setVisibility(View.GONE);
                break;
            case 2:
                mBgImageView.setImageResource(R.drawable.guide_page_3);
                mSkipView.setVisibility(View.VISIBLE);
                break;

        }
        return v;
    }
}
