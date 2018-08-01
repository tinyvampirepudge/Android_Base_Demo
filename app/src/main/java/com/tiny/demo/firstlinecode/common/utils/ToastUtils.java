package com.tiny.demo.firstlinecode.common.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.app.FLCApplication;

/**
 * Created by tiny on 16/7/30.
 */
public class ToastUtils {

    private static Toast toast;//简单的toast
    private static View viewError;
    private static TextView txt;
    private static ImageView imgerror;
    private static Toast toastError;//错误提示的toast

    /**
     * 单例toast,测试用
     *
     * @param content
     */
    public static void showSingleToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(FLCApplication.instance(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 弹出提示的toast.
     *
     * @param content 提示内容
     * @param showImg 是否隐藏错误提示img.true表示隐藏。
     */
    public static void showErrorToast(String content, boolean showImg) {
        if (toastError == null) {
            //new toast
            toastError = new Toast(FLCApplication.instance());
            toastError.setDuration(Toast.LENGTH_SHORT);
            //给toast设置显示位置,如果不设置就是默认显示位置。
            //第一个参数代表位置，第二个是水平偏移量，负数向左偏移，正数向右偏移，
            // 竖直偏移数值，负数表示向上偏移。
//            toastError.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 200);
        }
        //new view
        if (viewError == null) {
            viewError = LayoutInflater.from(FLCApplication.instance()).inflate(R.layout.dialog_error, null);
            txt = (TextView) viewError.findViewById(R.id.text_error);
            imgerror = (ImageView) viewError.findViewById(R.id.img_error);
        }

        //set params
        if (!showImg) {
            imgerror.setVisibility(View.VISIBLE);
        } else {
            imgerror.setVisibility(View.GONE);
        }
        txt.setText(content);

        toastError.setView(viewError);
        toastError.show();
    }
}
