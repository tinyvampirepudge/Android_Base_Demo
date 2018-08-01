package com.tiny.demo.firstlinecode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 87959 on 2017/3/20.
 */

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news_title_frag, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    private List<News> getNews() {
        List<News> lists = new ArrayList<>();
        for (int i = 1;i<=50;i++){
            News news = new News();
            news.setTitle("This is new title "+ i);
            news.setContent(getRandomLengthContent("This is new content "+i+"."));
            lists.add(news);
        }
        return lists;
  }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length  = random.nextInt(20) + 1;
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<length;i++){
            sb.append(content);
        }
        return sb.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> mNewsLists;

        public NewsAdapter(List<News> mNewsLists) {
            this.mNewsLists = mNewsLists;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_news_item,parent,false);
            final ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsLists.get(viewHolder.getAdapterPosition());
                    if (isTwoPane){
                        //如果是双页模式，则刷新NewsContentFragment中的内容
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
                                .findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else{
                        //如果是单页模式，则直接启动NewsContentActivity
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsLists.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsLists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView newsTitleText;

            public ViewHolder(View itemView) {
                super(itemView);
                newsTitleText = (TextView) itemView.findViewById(R.id.news_title);
            }
        }
    }
}
