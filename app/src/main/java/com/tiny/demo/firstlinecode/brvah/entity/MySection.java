package com.tiny.demo.firstlinecode.brvah.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Desc:
 * Created by tiny on 2017/12/11.
 * Version:
 */

public class MySection extends SectionEntity<Video> {
    private boolean isMore;

    public MySection(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public MySection(Video video) {
        super(video);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
