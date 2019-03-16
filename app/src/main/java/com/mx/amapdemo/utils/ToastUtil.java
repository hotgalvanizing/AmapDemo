package com.mx.amapdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mx.amapdemo.R;

public class ToastUtil {
    /**
     * Toast
     */
    private static Toast sToast;

    static TextView mTextView = null;
    static LinearLayout bg;

    /**
     * 显示长时间Toast
     *
     * @param context 上下文
     * @param msgId   内容id
     */
    public static void showToastLong(Context context, int msgId) {
        if (context == null) {
            return;
        }
        closeToast();
        showToast(context, AppResourcesUtils.getString(msgId), Toast.LENGTH_LONG);
    }

    /**
     * 显示短时间Toast
     *
     * @param context 上下文
     * @param msgId   内容id
     */
    public static void showToastShort(Context context, int msgId) {
        if (context == null) {
            return;
        }
        closeToast();
        showToast(context, AppResourcesUtils.getString(msgId), Toast.LENGTH_SHORT);
    }

    /**
     * 显示长时间Toast
     *
     * @param context 上下文
     * @param msg     内容
     */
    public static void showToastLong(Context context, String msg) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return;
        }
        closeToast();
        showToast(context, msg, Toast.LENGTH_LONG);
    }

    /**
     * 显示短时间Toast
     *
     * @param context 上下文
     * @param msg     内容
     */
    public static void showToastShort(Context context, String msg) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return;
        }
        closeToast();
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 关闭toast
     */
    public static void closeToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

    private static void showToast(Context context, String text, int duration) {
        if (sToast == null) {
            sToast = new Toast(context);
            View toastRoot = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
            mTextView = (TextView) toastRoot.findViewById(R.id.message);
            bg = toastRoot.findViewById(R.id.toast_bg);
            sToast.setView(toastRoot);
            sToast.setGravity(Gravity.TOP, 0, 300);
        }
        mTextView.setText(text);
        AppResourcesUtils.setBackgroud(bg, R.drawable.common_toast_bg);
        AppResourcesUtils.setTextColor(mTextView, R.color.toast_text_color);
        sToast.setDuration(duration);
        sToast.show();
    }


}
