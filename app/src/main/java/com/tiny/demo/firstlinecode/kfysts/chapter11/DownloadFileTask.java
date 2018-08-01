package com.tiny.demo.firstlinecode.kfysts.chapter11;

import android.os.AsyncTask;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.net.URL;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/4/21 下午11:17
 */

public class DownloadFileTask extends AsyncTask<URL, Integer, Long> {
    public static final String TAG = DownloadFileTask.class.getSimpleName();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        LogUtils.e(TAG, "onPreExecute");
    }

    @Override
    protected Long doInBackground(URL... urls) {
        LogUtils.e(TAG, "doInBackground");
        int count = urls.length;
        long totalSize = 0;
        for (int j = 0; j < count; j++) {
//            totalSize += Downloader.downloadFile(urls[j]);
            publishProgress((int) (j / count * 1.0f) * 100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Escape early if cancel() is called
            if (isCancelled()) {
                break;
            }
        }
        return totalSize;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LogUtils.e(TAG, "onProgressUpdate");
        //这里做更新UI的操作
        LogUtils.e(TAG, "values[0] --> " + values[0]);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        LogUtils.e(TAG, "onPostExecute");
        //执行完毕。
    }
}
