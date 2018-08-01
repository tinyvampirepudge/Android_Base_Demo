package com.tiny.demo.firstlinecode.service;

/**
 * Created by 87959 on 2017/5/24.
 */

public interface DownloadListener {
    //通知当前的下载进度
    void onProgress(int progress);

    //下载成功
    void onSuccess();

    //下载失败
    void onFailed();

    //下载暂停
    void onPaused();

    //下载取消
    void onCanceled();
}
