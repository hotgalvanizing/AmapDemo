package com.mx.amapdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AppResourcesUtils {

    private static Resources mResources;
    private static String mPackageName;
    private static Context mContext;
    private static SparseIntArray mResIdMap = new SparseIntArray();
    private static LayoutInflater mInflater;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
        mResources = mContext.getResources();
        mPackageName = mContext.getPackageName();
        mInflater = LayoutInflater.from(mContext);
    }

    public static String getString(int resId) {
        if (mResources == null) {
            return "";
        }
        String retStr;
        try {
            retStr = mResources.getString(resId);
        } catch (Resources.NotFoundException e) {
            retStr = "";
        }
        return retStr;
    }

    public static String getString(int resId, Object... format) {
        if (mResources == null) {
            return "";
        }
        String retStr;
        try {
            retStr = mResources.getString(resId, format);
        } catch (Resources.NotFoundException e) {
            retStr = "";
        }
        return retStr;
    }


    public static void setBackgroud(View view, int resId) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(getDrawable(resId));
        } else {
            view.setBackgroundDrawable(getDrawable(resId));
        }
    }

    private static Drawable getDrawable(int autoDayNightResId) {
        if (autoDayNightResId == -1) {
            return null;
        }
        Drawable d;
        try {
            d = mResources.getDrawable(autoDayNightResId);
        } catch (Throwable e) {
            d = null;
        }
        return d;
    }

    public static void setTextColor(TextView view, int resId) {
        view.setTextColor(getColor(resId));
    }

    private static int getColor(int dayNightResId) {
        if (mResources == null) {
            return 0;
        }
        int color;
        try {
            color = mResources.getColor(dayNightResId);
        } catch (Resources.NotFoundException e) {
            color = 0;
        }
        return color;
    }
}
