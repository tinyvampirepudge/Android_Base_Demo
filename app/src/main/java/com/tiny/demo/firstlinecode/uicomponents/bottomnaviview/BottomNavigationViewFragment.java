package com.tiny.demo.firstlinecode.uicomponents.bottomnaviview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseFragment;

/**
 * Desc:
 * Created by tiny on 2017/11/20.
 * Version:
 */

public class BottomNavigationViewFragment extends BaseFragment {

    public BottomNavigationViewFragment() {
    }

    public static BottomNavigationViewFragment newInstance(Bundle args) {
        BottomNavigationViewFragment f = new BottomNavigationViewFragment();
        // Supply index input as an argument.
        f.setArguments(args);
        return f;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_bottom_navigation_view;
    }

    @Override
    protected void buildContentView(View contentView, Bundle savedInstanceState) {
        TextView txt = (TextView) contentView.findViewById(R.id.txt_bottom_navigation_view);
        Bundle bundle = getArguments();
        String value = bundle.getString("BottomNavigationView");
        txt.setText(value);
    }

    @Override
    protected void initViewData() {

    }
}
