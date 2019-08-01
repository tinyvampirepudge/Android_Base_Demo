package com.tiny.demo.firstlinecode.bitmap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tiny.demo.firstlinecode.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BitmapFactoryTestActivity extends AppCompatActivity {
    private Button button;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, BitmapFactoryTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_factory_test);

        button = findViewById(R.id.btn_bitmap_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image = getResources().getString(R.string.base642);
//                System.out.println("image:" + image);
                Bitmap bitmap = base64ToBitmap(BitmapFactoryTestActivity.this, image);
                System.out.println(bitmap);
            }
        });

        // BitmapFactory.decodeResource
        ImageView iv = findViewById(R.id.img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx20190116_180256);
        iv.setImageBitmap(bitmap);

        // BitmapFactory.decodeResource --> byteArray --> BitmapFactory.decodeByteArray
        ImageView iv1 = findViewById(R.id.img1);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.wx20190116_180256);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap1.recycle();

        Bitmap bitmap11 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv1.setImageBitmap(bitmap11);

        Button button2 = findViewById(R.id.btn_bitmap_test1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeByteArrayFromFileTest();
            }
        });
        iv2 = findViewById(R.id.img2);

        Button button3 = findViewById(R.id.btn_bitmap_test2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeFileTest();
            }
        });
        iv3 = findViewById(R.id.img3);

        Button button4 = findViewById(R.id.btn_bitmap_test3);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeByteArrayBase64Test();
            }
        });
        iv4 = findViewById(R.id.img4);
    }

    private void decodeByteArrayFromFileTest() {
        // BitmapFactory.decodeResource --> byteArray --> base64.encode --> base64.decode --> file --> stream --> BitmapFactory.decodeStream
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.wx20190116_180256);
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] byteArray2 = stream2.toByteArray();
        bitmap2.recycle();

        String base64 = Base64.encodeToString(byteArray2, Base64.DEFAULT);

        byte[] bytesResult = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        File file = new File(getFilePath(getCacheDir()));

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytesResult);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        File fileStorage = new File(MainActivity.this.getCacheDir().getPath() + File.separator + "base64_20190117_105459.txt");

        FileInputStream in;
        BufferedInputStream buf;
        try {
            in = new FileInputStream(file);
            buf = new BufferedInputStream(in);
            Bitmap bitmap22 = BitmapFactory.decodeStream(buf);
            iv2.setImageBitmap(bitmap22);
            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }
    }

    public Bitmap base64ToBitmap(Context mContext, String base64Data) {
        try {
            String base64Replace = base64Data.replaceAll(" ", "+");
            byte[] bytes = Base64.decode(base64Replace, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return bitmap;
        } catch (Exception var5) {
            var5.printStackTrace();
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
            return bitmap;
        }
    }

    /**
     * 生成保存路径
     *
     * @param mediaStorageDir
     * @return
     */
    private static String getFilePath(File mediaStorageDir) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String filePath = mediaStorageDir.getPath() + File.separator;
        filePath += ("base64_" + timeStamp + ".png");
        return filePath;
    }

    /**
     * 从 data/data/包名/cache/ 下读取文件
     */
    private void decodeFileTest() {
        File fileStorage = new File(getCacheDir().getPath() + File.separator + "base64_20190124_161930.png");
        Bitmap bMap = BitmapFactory.decodeFile(fileStorage.toString());
        iv3.setImageBitmap(bMap);
    }

    private void decodeByteArrayBase64Test() {
        // BitmapFactory.decodeResource --> byteArray --> base64.encode --> base64.decode --》  --> BitmapFactory.decodeByteArray
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.wx20190116_173832);
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] byteArray2 = stream2.toByteArray();
        bitmap2.recycle();

        String base64 = Base64.encodeToString(byteArray2, Base64.DEFAULT);
        String base64Test = getResources().getString(R.string.base64);
//        base64Test = base64Test.replaceAll(" ","+");
        Log.e("decodeByteArray", "base64.equals(base64Test) --> " + base64.equals(base64Test));

        byte[] bytesResult = Base64.decode(base64, Base64.DEFAULT);
        byte[] bytesResultTest = Base64.decode(base64Test, Base64.DEFAULT);
        boolean byteEqualResult = true;
        if (bytesResult.length != bytesResultTest.length) {
            byteEqualResult = false;
        } else {
            for (int i = 0; i < bytesResult.length; i++) {
                if (bytesResult[i] != bytesResultTest[i]) {
                    byteEqualResult = false;
                    break;
                }
            }
        }
        Log.e("decodeByteArray", "byteEqualResult --> " + byteEqualResult);

        Bitmap bitmap22 = BitmapFactory.decodeByteArray(bytesResultTest, 0, bytesResultTest.length);
        iv4.setImageBitmap(bitmap22);
    }
}
