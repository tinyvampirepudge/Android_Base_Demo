package com.tiny.demo.firstlinecode.camera;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.ScreenUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class CameraAlbumActivity extends BaseActivity {
    private static String authority;
    public static final int TAKE_PHOTO = 1000;
    public static final int CHOOSE_PHOTO = 1001;
    public static final int PERMISSION_ALBUM = 1;

    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.img_picture)
    ImageView imgPicture;
    @BindView(R.id.activity_camera_album)
    ScrollView activityCameraAlbum;
    @BindView(R.id.btn_choose_photo)
    Button btnChoosePhoto;
    private Uri imageUri;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CameraAlbumActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_camera_album;
    }

    @Override
    protected void buildContentView() {
        authority = getString(R.string.file_provider);
    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_take_photo, R.id.img_picture, R.id.btn_choose_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_take_photo:
                //创建File对象，用于存储拍照后的照片
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //从Android7.0系统开始，直接使用本地真实路径的Uri被认为是不安全的，
                //会抛出一个FileUriExposedException异常。
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(mContext, authority, outputImage);

                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.btn_choose_photo:
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_ALBUM);
                } else {
                    openAlbum();
                }
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        showBitmap(imgPicture, bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    //从4.4开始，选择相册中的图片不再返回图片真实的Uri了，而是一个封装过的Uri，需要对这个Uri进行解析才行
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void showBitmap(ImageView imgPicture, Bitmap bitmap) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgPicture.getLayoutParams();
        layoutParams.width = ScreenUtils.INSTANCE.getScreenW(mContext);
        layoutParams.height = (int) (ScreenUtils.INSTANCE.getScreenW(mContext) * (bitmap.getHeight() * 1.0f / bitmap.getWidth() * 1.0f));
        imgPicture.setImageBitmap(bitmap);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(mContext, uri)) {
            //如果是Document类型的Uri，则通过DocumentId处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.download.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是File类型uri, 直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            showBitmap(imgPicture, bitmap);
        } else {
            Toast.makeText(CameraAlbumActivity.this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = uri.getPath();
        displayImage(imagePath);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_ALBUM:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(CameraAlbumActivity.this, "You deny the permission!", Toast.LENGTH_SHORT).show();
                }
                break;
//            case :
//
//                break;
            default:
                break;
        }

    }
}
