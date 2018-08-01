package com.tiny.demo.firstlinecode.video;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.btn_video_play)
    Button btnVideoPlay;
    @BindView(R.id.btn_video_pause)
    Button btnVideoPause;
    @BindView(R.id.btn_video_replay)
    Button btnVideoReplay;
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.activity_video)
    LinearLayout activityVideo;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void buildContentView() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPath();
        }
    }

    private void initVideoPath() {
        File file = new File(mContext.getExternalCacheDir(), "movie.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_video_play, R.id.btn_video_pause, R.id.btn_video_replay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_video_play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.btn_video_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.btn_video_replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(VideoActivity.this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
//            case :
//
//                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
