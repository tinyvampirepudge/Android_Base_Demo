package com.tiny.demo.firstlinecode.brvah.adapter;

import android.support.annotation.Nullable;

import com.tiny.demo.firstlinecode.brvah.base.BaseDataBindingAdapter;
import com.tiny.demo.firstlinecode.brvah.entity.Movie;
import com.tiny.demo.firstlinecode.databinding.ItemMovieBinding;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/12.
 * Version:
 */

public class UpFetchAdapter extends BaseDataBindingAdapter<Movie, ItemMovieBinding> {

    public UpFetchAdapter(int layoutResId, @Nullable List<Movie> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ItemMovieBinding binding, Movie item) {
        binding.setMovie(item);
    }
}
