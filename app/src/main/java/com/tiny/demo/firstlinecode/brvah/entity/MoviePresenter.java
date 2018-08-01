package com.tiny.demo.firstlinecode.brvah.entity;

import android.view.View;

import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

/**
 * Created by luoxiongwen on 16/10/24.
 */

public class MoviePresenter {
    public void buyTicket(View view, Movie movie) {
        ToastUtils.showSingleToast("buy ticket: " + movie.name);
    }
}
