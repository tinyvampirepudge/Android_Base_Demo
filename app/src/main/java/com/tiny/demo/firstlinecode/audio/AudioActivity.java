package com.tiny.demo.firstlinecode.audio;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class AudioActivity extends BaseActivity {
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @BindView(R.id.btn_audio_play)
    Button benAudioPlay;
    @BindView(R.id.btn_audio_pause)
    Button benAudioPause;
    @BindView(R.id.btn_audio_stop)
    Button benAudioStop;
    @BindView(R.id.activity_audio)
    LinearLayout activityAudio;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AudioActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_audio;
    }

    @Override
    protected void buildContentView() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(mContext.getExternalCacheDir(), "xiaoxingyun.mp3");
            mediaPlayer.setDataSource(file.getPath());//指定音频文件的路径
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_audio_play, R.id.btn_audio_pause, R.id.btn_audio_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_audio_play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.btn_audio_pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.btn_audio_stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayer.release();
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
                    initMediaPlayer();
                } else {
                    Toast.makeText(AudioActivity.this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
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
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
