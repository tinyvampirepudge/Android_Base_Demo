package com.tiny.demo.firstlinecode.uicomponents.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

/**
 * Desc:
 * Created by tiny on 2017/11/20.
 * Version:
 */

public class TabLayoutFragment extends Fragment {

    public TabLayoutFragment() {
    }

    public static TabLayoutFragment newInstance(Bundle args) {
        TabLayoutFragment f = new TabLayoutFragment();
        // Supply index input as an argument.
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        TextView txt = (TextView) view.findViewById(R.id.txt_fragment_tabLayout);
        Bundle bundle = getArguments();
        String value = bundle.getString("tabLayout");
        txt.setText(value);
        return view;
    }
}
