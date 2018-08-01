package com.tiny.demo.firstlinecode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

/**
 * Created by 87959 on 2017/3/20.
 */

public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_news_content_frag, container, false);
        return view;
    }

    public void refresh(String title,String content){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView txtTitle = (TextView) view.findViewById(R.id.news_title);
        TextView txtContent = (TextView) view.findViewById(R.id.news_content);
        txtTitle.setText(title);
        txtContent.setText(content);
    }
}
